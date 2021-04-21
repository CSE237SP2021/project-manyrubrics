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
	
	/*
	 * The rubric extractor should be able to read the list of assignments first
	 */
	@Test
	void testRubricExtractorReadsAssignments() {
		RubricExtractor extractor;
		List<Assignment> assignments;
		try {
			extractor = new RubricExtractor("testfiles/rubricTestfile.txt");
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


	/*
	 * after reading the assignment list, the rubric extractor should be able to read in all the rubrics in the
	 * testfile
	 */
	@Test
	void testRubricExtractorReadsAllRubrics() {
		RubricExtractor extractor;
		try {
			extractor = new RubricExtractor("testfiles/rubricTestfile.txt");
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
	
	/*
	 * the rubricExtractor should fail if no assignment list is provided
	 */
	@Test
	void testRubricExtractorFailsWithNoAssignmentList(){
		Exception e = assertThrows(DataFormatException.class, () -> {
			RubricExtractor extractor = new RubricExtractor("testfiles/noAssignments.txt");
		});
		assertEquals(e.getMessage(), "The rubric file must contain a list of whitespace separated assignments that are not interpretable as weights. add one and try again");
	}
	
	/*
	 * the rubricExtractor should fail if there are no rubrics provided
	 */
	@Test
	void testRubricExtractorFailsWithNoRubricsProvided(){
		Exception e = assertThrows(DataFormatException.class, () -> {
			RubricExtractor extractor = new RubricExtractor("testfiles/noRubrics.txt");
		});
		assertEquals(e.getMessage(), "No valid rubrics were read from the file. Please include at least one valid rubric and try again");
	}
	
	/*
	 * the rubric extractor should skip over any rubrics with the wrong number of weights, but should recover
	 * by ignoring that rubric and continuing to extract other rubrics
	 */
	@Test
	void testRubricExtractorRecoversWithIncorrectNumberOfWeights(){
		// capture systemOut:
		ByteArrayOutputStream capturedOut = new ByteArrayOutputStream();
		PrintStream stdOut = System.out;
		System.setOut(new PrintStream(capturedOut));
		
		
		RubricExtractor extractor;
		try {
			extractor = new RubricExtractor("testfiles/rubricTooFewTestfile.txt");
			
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
	
	/*
	 * the rubricExtractor should skip over any rubrics with assignment weights that cannot be interpreted as
	 * a number, but should recover by ignoring that rubric and continuing to extract other rubrics
	 */
	@Test
	void testRubricExtractorRecoversWithMalformedAssignmentWeight(){
		// capture systemOut:
		ByteArrayOutputStream capturedOut = new ByteArrayOutputStream();
		PrintStream stdOut = System.out;
		System.setOut(new PrintStream(capturedOut));
		
		RubricExtractor extractor;
		try {
			extractor = new RubricExtractor("testfiles/rubricMalformedInputTestfile.txt");
			
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
