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
	
	@Test
	void testAssignmentEquals() {
		Assignment assignmentUnderTest = new Assignment("Test Assignment");
		Assignment otherAssignmentUnderTest = new Assignment("Test Assignment");
		boolean isEqual = assignmentUnderTest.equals(otherAssignmentUnderTest);
		
		assertTrue(isEqual);
	}
	
	@Test
	void testAssignmentNotEquals() {
		Assignment assignmentUnderTest = new Assignment("Test Assignment");
		Assignment otherAssignmentUnderTest = new Assignment("Other Assignment");
		boolean isNotEqual = assignmentUnderTest.equals(otherAssignmentUnderTest);
		
		assertFalse(isNotEqual);
	}

}
