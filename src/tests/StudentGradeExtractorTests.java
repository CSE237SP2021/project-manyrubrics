package tests;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;
import java.util.zip.DataFormatException;

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
			rubricExtractor = new RubricExtractor("rubricTestFile.txt");
			List<Assignment> assignmentList = rubricExtractor.getAssignmentList();
			StudentGradeExtractor gradeExtractor = new StudentGradeExtractor("scoreTestfile.txt", assignmentList);
			List<Student> students = gradeExtractor.getStudentList();
			
			for(Student student : students) {
				for(Assignment assignment : assignmentList) {
					assertEquals(100, student.getScoreForAssignment(assignment));
				}
			}
		} catch (FileNotFoundException e) {
			fail("a test file was not found");
		} catch (DataFormatException e) {
			fail(e.getMessage());
		}
		
	}
	
	
	@Test
	void testExtractStudentGradesIncorrectNumberOfAssignments() {
		// with an incorrect number of assignments in the student file, the gradeExtractor should just throw an
		// exception because it cannot extract any assignment grades for the students
		assertThrows(DataFormatException.class, () -> {
			RubricExtractor rubricExtractor;
			rubricExtractor = new RubricExtractor("rubricTestFile.txt");
			List<Assignment> assignmentList = rubricExtractor.getAssignmentList();
			StudentGradeExtractor gradeExtractor = new StudentGradeExtractor("incorrectAssignmentList.txt", assignmentList);

		}); 
	}
	
	/*
	 * If an individual student in the student file has the wrong number of grades, the program should just skip
	 * over that student and save the other students
	 */
	@Test
	void testExtractStudentGradesRecoversForIncorrectNumberOfStudentGrades() {
		// capture systemOut:
		ByteArrayOutputStream capturedOut = new ByteArrayOutputStream();
		PrintStream stdOut = System.out;
		System.setOut(new PrintStream(capturedOut));
		
		RubricExtractor rubricExtractor;
		try {
			rubricExtractor = new RubricExtractor("rubricTestFile.txt");
			List<Assignment> assignmentList = rubricExtractor.getAssignmentList();
			StudentGradeExtractor gradeExtractor = new StudentGradeExtractor("incorrectStudentGrades.txt", assignmentList);
			String errorMsg = capturedOut.toString();

			assertEquals(errorMsg.trim(), "Sam_McGarey had grades for the wrong number of assignments. This student will not be graded.");
			
			List<Student> students = gradeExtractor.getStudentList();
			
			for(Student student : students) {
				for(Assignment assignment : assignmentList) {
					assertEquals(100, student.getScoreForAssignment(assignment));
				}
			}
		} catch (FileNotFoundException e) {
			fail("a test file was not found");
		} catch (DataFormatException e) {
			fail(e.getMessage());
		} finally {
			//restore sysout
			System.setOut(stdOut);
		}
	}
}
