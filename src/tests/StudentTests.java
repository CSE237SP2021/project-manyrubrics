package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import manyRubrics.Assignment;
import manyRubrics.Student;

class StudentTests {

	@Test
	void testStudentGetScore() {
		Assignment firstAssignment = new Assignment("first");
		Assignment secondAssignment = new Assignment("second");
		ArrayList<Assignment> assignments = new ArrayList<Assignment>();
		assignments.add(firstAssignment);
		assignments.add(secondAssignment);
		
		Student studentUnderTest = new Student("AName", assignments);
		studentUnderTest.addScoreToAssignment(firstAssignment, 93.3);
		
		double studentScore = studentUnderTest.getScoreForAssignment(firstAssignment);
		
		assertEquals(93.3, studentScore);

	}

}
