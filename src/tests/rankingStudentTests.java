package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import manyRubrics.Assignment;
import manyRubrics.Rubric;
import manyRubrics.Student;
import manyRubrics.RankingStudents;

public class rankingStudentTests {

	@Test
	void testRankStudent() {
		ArrayList<Assignment> assignments = new ArrayList<Assignment>();
		Assignment firstAssignment = new Assignment("first", 100);
		Assignment secondAssignment = new Assignment("second", 100);
		assignments.add(firstAssignment);
		assignments.add(secondAssignment);

		
		Rubric rubricUnderTest = new Rubric(assignments);
		rubricUnderTest.setAssignmentWeight(firstAssignment, 50);
		rubricUnderTest.setAssignmentWeight(secondAssignment, 50);

		Student student1 = new Student("AName", assignments);
		student1.addScoreToAssignment(firstAssignment, 75);
		student1.addScoreToAssignment(secondAssignment, 75);
		
		Student student2 = new Student("BName", assignments);
		student2.addScoreToAssignment(firstAssignment, 90);
		student2.addScoreToAssignment(secondAssignment, 90);
		
		Student student3 = new Student("CName", assignments);
		student3.addScoreToAssignment(firstAssignment, 90);
		student3.addScoreToAssignment(secondAssignment, 75);
		
		Student student4 = new Student("DName", assignments);
		student4.addScoreToAssignment(firstAssignment, 70);
		student4.addScoreToAssignment(secondAssignment, 70);
		
		ArrayList<Student> students = new ArrayList<Student>();
		students.add(student1);
		students.add(student2);
		students.add(student3);
		students.add(student4);
	
		RankingStudents rankingStudents= new RankingStudents(students,rubricUnderTest);
		ArrayList<Student> correctRank = new ArrayList<Student>();
		correctRank.add(student2);
		correctRank.add(student3);
		correctRank.add(student1);
		correctRank.add(student4);
		
		List<Student>rank = rankingStudents.RankStudent();
		assertEquals(correctRank,rank);
		
	}
}
