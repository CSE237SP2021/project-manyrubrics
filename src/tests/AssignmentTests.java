package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import manyRubrics.Assignment;

class AssignmentTests {

	@Test
	void testAssignmentName() {
		Assignment assignmentUnderTest = new Assignment("Test Assignment");
		assertEquals(assignmentUnderTest.name(), "Test Assignment");
	}

}
