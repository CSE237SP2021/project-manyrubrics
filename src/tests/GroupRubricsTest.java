package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import manyRubrics.Assignment;
import manyRubrics.GroupRubrics;
import manyRubrics.Rubric;
import manyRubrics.Student;

class GroupRubricsTest {

	@Test
	void testMaxRubricGrade() {
		
		ArrayList<Assignment> assignments = new ArrayList<Assignment>();
		
		Assignment firstAssignment = new Assignment("first", 100);
		Assignment secondAssignment = new Assignment("second", 100);
		assignments.add(firstAssignment);
		assignments.add(secondAssignment);
		
		Student student = new Student("Test Student", assignments);
		student.addScoreToAssignment(firstAssignment, 100);
		student.addScoreToAssignment(secondAssignment, 60);

		Rubric firstRubric = new Rubric(assignments);
		firstRubric.setAssignmentWeight(firstAssignment, 50);
		firstRubric.setAssignmentWeight(secondAssignment, 50);
		
		//80.0
		double firstRubricScore = firstRubric.computeScoreForStudent(student); 
		
		Rubric secondRubric = new Rubric(assignments);
		secondRubric.setAssignmentWeight(firstAssignment, 70);
		secondRubric.setAssignmentWeight(secondAssignment, 30);
		
		//88.0
		double secondRubricScore = secondRubric.computeScoreForStudent(student);
		
		ArrayList<Rubric> needGrouping = new ArrayList<Rubric>();
		needGrouping.add(firstRubric);
		needGrouping.add(secondRubric);
		
		GroupRubrics groupedRubrics = new GroupRubrics(needGrouping);
		
		double studentHighestGrade = groupedRubrics.bestGrade(student);
		
		assertEquals(88, studentHighestGrade);
	}
}