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
		student.setScoreToAssignment(firstAssignment, 100);
		student.setScoreToAssignment(secondAssignment, 60);

		Rubric rubricUnderTest = new Rubric(assignments);
		rubricUnderTest.setAssignmentWeight(firstAssignment, 50);
		rubricUnderTest.setAssignmentWeight(secondAssignment, 50);

		double score = rubricUnderTest.computeScoreForStudent(student);

		assertEquals(80.0, score);

	}

	@Test
	void testRubricCopyConstructor() {
		ArrayList<Assignment> assignments = new ArrayList<Assignment>();
		Assignment firstAssignment = new Assignment("first");

		Assignment secondAssignment = new Assignment("second");
		assignments.add(firstAssignment);
		assignments.add(secondAssignment);

		Student student = new Student("Test Student", assignments);
		student.setScoreToAssignment(firstAssignment, 100);
		student.setScoreToAssignment(secondAssignment, 60);

		Rubric rubricUnderTest = new Rubric(assignments);
		rubricUnderTest.setAssignmentWeight(firstAssignment, 50);
		rubricUnderTest.setAssignmentWeight(secondAssignment, 50);
		
		Rubric copy = new Rubric(rubricUnderTest);
		double score = copy.computeScoreForStudent(student);
		

		assertEquals(80.0, score);
		
	}
	
	@Test 
	void TestAddAssignment() {
		ArrayList<Assignment> rubricAssignments = new ArrayList<Assignment>();
		ArrayList<Assignment> studentAssignments = new ArrayList<Assignment>();
		Assignment firstAssignment = new Assignment("first");
		Assignment secondAssignment = new Assignment("second");
		Assignment ThirdAssignment = new Assignment("third");
		
		rubricAssignments.add(firstAssignment);
		rubricAssignments.add(secondAssignment);
		studentAssignments.add(firstAssignment);
		studentAssignments.add(secondAssignment);
		studentAssignments.add(ThirdAssignment);
		
		Student student = new Student("Test Student", studentAssignments);
		student.setScoreToAssignment(firstAssignment, 100);
		student.setScoreToAssignment(secondAssignment, 60);
		student.setScoreToAssignment(ThirdAssignment, 75);

		Rubric rubricUnderTest = new Rubric(rubricAssignments);
		rubricUnderTest.setAssignmentWeight(firstAssignment, 50);
		rubricUnderTest.setAssignmentWeight(secondAssignment, 25);
		
		
		rubricUnderTest.addAssignment(ThirdAssignment, 25);
		double score = rubricUnderTest.computeScoreForStudent(student);
		

		assertEquals(83.75, score);
		
		
	}
	
	@Test 
	void TestRemoveAssignment() {
		ArrayList<Assignment> rubricAssignments = new ArrayList<Assignment>();
		ArrayList<Assignment> studentAssignments = new ArrayList<Assignment>();
		Assignment firstAssignment = new Assignment("first");
		Assignment secondAssignment = new Assignment("second");
		Assignment ThirdAssignment = new Assignment("third");
		
		rubricAssignments.add(firstAssignment);
		rubricAssignments.add(secondAssignment);
		rubricAssignments.add(ThirdAssignment);
		studentAssignments.add(firstAssignment);
		studentAssignments.add(secondAssignment);
		studentAssignments.add(ThirdAssignment);
		
		Student student = new Student("Test Student", studentAssignments);
		student.setScoreToAssignment(firstAssignment, 100);
		student.setScoreToAssignment(secondAssignment, 60);
		student.setScoreToAssignment(ThirdAssignment, 75);

		Rubric rubricUnderTest = new Rubric(rubricAssignments);
		rubricUnderTest.setAssignmentWeight(firstAssignment, 50);
		rubricUnderTest.setAssignmentWeight(secondAssignment, 25);
		rubricUnderTest.setAssignmentWeight(ThirdAssignment, 25);
		
		assertTrue(rubricUnderTest.isValid());
		rubricUnderTest.removeAssignment(ThirdAssignment);
		assertFalse(rubricUnderTest.isValid());
		rubricUnderTest.setAssignmentWeight(secondAssignment, 50);
		assertTrue(rubricUnderTest.isValid());
		double score = rubricUnderTest.computeScoreForStudent(student);
		

		assertEquals(80, score);
	}

}
