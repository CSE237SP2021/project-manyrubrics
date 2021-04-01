package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.jupiter.api.Test;

import manyRubrics.Assignment;
import manyRubrics.RubricExtractor;
import manyRubrics.Student;
import manyRubrics.StudentGradeExtractor;

class StudentGradeExtractorTests {

	@Test
	void testExtractStudentGradesGoodFile() {
		RubricExtractor rubricExtractor;
		try {
			rubricExtractor = new RubricExtractor("testfile.txt");
			List<Assignment> assignmentList = rubricExtractor.getAssignmentList();
			StudentGradeExtractor gradeExtractor = new StudentGradeExtractor("scoreTestfile.txt", assignmentList);
			List<Student> students = gradeExtractor.getStudentList();
			
			for(Student student : students) {
				for(Assignment assignment : assignmentList) {
					assertEquals(100, student.getScoreForAssignment(assignment), 0.01);
				}
			}
		} catch (FileNotFoundException e) {
			fail("a test file was not found");
		}
		
	}
	
	@Test
	void testExtractStudentGradesIncorrectNumberOfAssignments() {
		fail("not yet implemented");
	}

}
