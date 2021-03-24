package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import manyRubrics.Assignment;
import manyRubrics.Rubric;
import manyRubrics.Student;

class RubricTests {

	@Test
	void testValidRubric() {
		ArrayList<Assignment> assignments = new ArrayList<Assignment>();
		Assignment firstAssignment = new Assignment("first");
		Assignment secondAssignment = new Assignment("second");
		assignments.add(firstAssignment);
		assignments.add(secondAssignment);

		
		Rubric rubricUnderTest = new Rubric(assignments);
		rubricUnderTest.setAssignmentWeight(firstAssignment, 50);
		rubricUnderTest.setAssignmentWeight(secondAssignment, 50);

		boolean isValid = rubricUnderTest.isValid();
		
		assertTrue(isValid);

	}
	
	@Test
	void testInvalidRubric() {
		ArrayList<Assignment> assignments = new ArrayList<Assignment>();
		Assignment firstAssignment = new Assignment("first");
		Assignment secondAssignment = new Assignment("second");
		assignments.add(firstAssignment);
		assignments.add(secondAssignment);
		
		
		Rubric rubricUnderTest = new Rubric(assignments);
		rubricUnderTest.setAssignmentWeight(firstAssignment, 10);
		rubricUnderTest.setAssignmentWeight(secondAssignment, 10);

		boolean isInvalid = rubricUnderTest.isValid();
		
		assertFalse(isInvalid);

	}
	
	@Test
	void testRubricComputeScores() {
		ArrayList<Assignment> assignments = new ArrayList<Assignment>();
		
		Assignment firstAssignment = new Assignment("first");
		
		Assignment secondAssignment = new Assignment("second");
		assignments.add(firstAssignment);
		assignments.add(secondAssignment);
		
		Student student = new Student("Test Student", assignments);
		student.addScoreToAssignment(firstAssignment, 100);
		student.addScoreToAssignment(secondAssignment, 60);

		
		Rubric rubricUnderTest = new Rubric(assignments);
		rubricUnderTest.setAssignmentWeight(firstAssignment, 50);
		rubricUnderTest.setAssignmentWeight(secondAssignment, 50);
		
		double score = rubricUnderTest.computeScoreForStudent(student);
		
		assertEquals(80.0, score);

	}

}
