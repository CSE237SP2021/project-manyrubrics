package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import manyRubrics.Assignment;
import manyRubrics.Rubric;

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
		firstAssignment.setScore(100);
		
		Assignment secondAssignment = new Assignment("second");
		secondAssignment.setScore(60);
		assignments.add(firstAssignment);
		assignments.add(secondAssignment);
		
		Rubric rubricUnderTest = new Rubric(assignments);
		rubricUnderTest.setAssignmentWeight(firstAssignment, 50);
		rubricUnderTest.setAssignmentWeight(secondAssignment, 50);
		
		double score = rubricUnderTest.computeScore();
		
		assertEquals(80.0, score);

	}

}
