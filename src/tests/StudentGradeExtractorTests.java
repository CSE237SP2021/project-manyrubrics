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
	

	/*
	 * along the happy path of execution, the studentGradeExtractor should be able to pull out grades for each
	 * student, so we test that all students get the expected grade (which for this test is 100)
	 */
	@Test
	void testExtractStudentGradesGoodFile() {
		RubricExtractor rubricExtractor;
		try {
			rubricExtractor = new RubricExtractor("testfiles/rubricTestFile.txt");
			List<Assignment> assignmentList = rubricExtractor.getAssignmentList();
			StudentGradeExtractor gradeExtractor = new StudentGradeExtractor("testfiles/scoreTestfile.txt", assignmentList);
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
	
	/*
	 * with an incorrect number of assignments in the student file, the gradeExtractor should just throw an
     * exception because it cannot extract any assignment grades for the students
	 */
	@Test
	void testExtractStudentGradesIncorrectNumberOfAssignments() {
		
		assertThrows(DataFormatException.class, () -> {
			RubricExtractor rubricExtractor;
			rubricExtractor = new RubricExtractor("testfiles/rubricTestFile.txt");
			List<Assignment> assignmentList = rubricExtractor.getAssignmentList();
			StudentGradeExtractor gradeExtractor = new StudentGradeExtractor("testfiles/incorrectAssignmentList.txt", assignmentList);

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
			rubricExtractor = new RubricExtractor("testfiles/rubricTestFile.txt");
			List<Assignment> assignmentList = rubricExtractor.getAssignmentList();
			StudentGradeExtractor gradeExtractor = new StudentGradeExtractor("testfiles/incorrectStudentGrades.txt", assignmentList);
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
	
	/*
	 * if an individual student's grade is listed in a way that cannot be interpreted as a number,
	 * the program should skip over that student and print an error message, but continue running
	 */
	@Test
	void testExtractStudentGradesRecoversForMalformedStudentGrade() {
		// capture systemOut:
		ByteArrayOutputStream capturedOut = new ByteArrayOutputStream();
		PrintStream stdOut = System.out;
		System.setOut(new PrintStream(capturedOut));
		
		RubricExtractor rubricExtractor;
		try {
			rubricExtractor = new RubricExtractor("testfiles/rubricTestFile.txt");
			List<Assignment> assignmentList = rubricExtractor.getAssignmentList();
			StudentGradeExtractor gradeExtractor = new StudentGradeExtractor("testfiles/malformedStudentGrade.txt", assignmentList);
			String errorMsg = capturedOut.toString();

			assertEquals(errorMsg.trim(), "score: fred for student: Sam_McGarey could not be interpreted as a valid score. This student will not be graded");
			
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
