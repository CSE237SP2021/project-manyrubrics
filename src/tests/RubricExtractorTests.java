package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import manyRubrics.Assignment;
import manyRubrics.Rubric;
import manyRubrics.RubricExtractor;
import manyRubrics.Student;

class RubricExtractorTests {
	
	@Test
	void testScannerReadsAssignments() {
		RubricExtractor extractor = new RubricExtractor("testfile.txt");
		List<Assignment> assignments = extractor.getAssignmentList();
		
		for(Assignment assignment : assignments) {
			assertTrue(assignment.name().equals("testAssignment"));
		}
	}

	
	@Test
	void testScannerReadsAllRubrics() {
		RubricExtractor extractor = new RubricExtractor("testfile.txt");
		
		Student student = new Student("test", extractor.getAssignmentList());
		for(Assignment assignment : extractor.getAssignmentList()) {
			student.addScoreToAssignment(assignment, 100);
		}
		
		for(Rubric rubric : extractor.getRubricList()) {
			assertEquals(100.0, rubric.computeScoreForStudent(student), 0.01);
		}
	}

}
