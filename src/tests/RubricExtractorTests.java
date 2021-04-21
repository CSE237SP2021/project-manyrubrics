package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;
import java.util.zip.DataFormatException;

import org.junit.jupiter.api.Test;

import manyRubrics.Assignment;
import manyRubrics.Rubric;
import manyRubrics.RubricExtractor;
import manyRubrics.Student;

class RubricExtractorTests {
	
	@Test
	void testRubricExtractorReadsAssignments() {
		RubricExtractor extractor;
		List<Assignment> assignments;
		try {
			extractor = new RubricExtractor("rubricTestfile.txt");
			assignments = extractor.getAssignmentList();
			for(Assignment assignment : assignments) {
				assertTrue(assignment.name().equals("testAssignment"));
			}

		} catch (FileNotFoundException e) {
			fail("test file not found");
		} catch (DataFormatException e) {
			fail(e.getMessage());
		}
		

	}

	
	@Test
	void testRubricExtractorReadsAllRubrics() {
		RubricExtractor extractor;
		try {
			extractor = new RubricExtractor("rubricTestfile.txt");
			Student student = new Student("test", extractor.getAssignmentList());
			for(Assignment assignment : extractor.getAssignmentList()) {
				student.addScoreToAssignment(assignment, 100);
			}
			
			for(Rubric rubric : extractor.getRubricList()) {
				assertEquals(100.0, rubric.computeScoreForStudent(student), 0.01);
			}
		} catch (FileNotFoundException e) {
			fail("test file not found");
		} catch (DataFormatException e) {
			fail(e.getMessage());
		}
		
	}
	
	@Test
	void testRubricExtractorFailsWithNoAssignmentList(){
		Exception e = assertThrows(DataFormatException.class, () -> {
			RubricExtractor extractor = new RubricExtractor("noAssignments.txt");
		});
		assertEquals(e.getMessage(), "The rubric file must contain a list of space-separated assignments that are not interpretable as weights. add one and try again");
	}
	
	@Test
	void testRubricExtractorFailsWithNoRubricsProvided(){
		Exception e = assertThrows(DataFormatException.class, () -> {
			RubricExtractor extractor = new RubricExtractor("noRubrics.txt");
		});
		assertEquals(e.getMessage(), "No valid rubrics were read from the file. Please include at least one valid rubric and try again");
	}
	
	@Test
	void testRubricExtractorRecoversWithIncorrectNumberOfWeights(){
		// capture systemOut:
		ByteArrayOutputStream capturedOut = new ByteArrayOutputStream();
		PrintStream stdOut = System.out;
		System.setOut(new PrintStream(capturedOut));
		
		
		RubricExtractor extractor;
		try {
			extractor = new RubricExtractor("rubricTooFewTestfile.txt");
			
			assertEquals(capturedOut.toString().trim(), "The rubric: 50 does not have a weight for every assignment, or has too many weights. This rubric will not be used to grade the students");
			assertEquals(extractor.getRubricList().size(), 1);
		} catch (FileNotFoundException e) {
			fail("test file not found");
		} catch (DataFormatException e) {
			fail(e.getMessage());
		} finally {
			//restore sysout
			System.setOut(stdOut);
		}
	}
	
	@Test
	void testRubricExtractorRecoversWithMalformedAssignmentWeight(){
		// capture systemOut:
		ByteArrayOutputStream capturedOut = new ByteArrayOutputStream();
		PrintStream stdOut = System.out;
		System.setOut(new PrintStream(capturedOut));
		
		RubricExtractor extractor;
		try {
			extractor = new RubricExtractor("rubricMalformedInputTestfile.txt");
			
			assertEquals(capturedOut.toString().trim(), "Assignment weight fred in rubric 30 fred cannot be converted to an appropriate value. This rubric will not be used to grade the students");
			assertEquals(extractor.getRubricList().size(), 1);
		} catch (FileNotFoundException e) {
			fail("test file not found");
		} catch (DataFormatException e) {
			fail(e.getMessage());
		} finally {
			//restore sysout
			System.setOut(stdOut);
		}
	}

}
