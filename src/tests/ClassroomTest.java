package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.Test;

import manyRubrics.Assignment;
import manyRubrics.Classroom;
import manyRubrics.Rubric;
import manyRubrics.Student;

class ClassroomTest {

	@Test
	void testCalculateGrades() {
		
		ArrayList<Assignment> assignments = new ArrayList<Assignment>();
		
		Assignment firstAssignment = new Assignment("first", 100);
		Assignment secondAssignment = new Assignment("second", 100);
		
		assignments.add(firstAssignment);
		assignments.add(secondAssignment);
		
		//Grade = 80
		Student studentOne = new Student("firstStudent", assignments);
		studentOne.addScoreToAssignment(firstAssignment, 100);
		studentOne.addScoreToAssignment(secondAssignment, 60);
		
		//Grade = 100
		Student studentTwo = new Student("secondStudent", assignments);
		studentTwo.addScoreToAssignment(firstAssignment, 100);
		studentTwo.addScoreToAssignment(secondAssignment, 100);
		
		//Grade = 90
		Student studentThree = new Student("thirdStudent", assignments);
		studentThree.addScoreToAssignment(firstAssignment, 100);
		studentThree.addScoreToAssignment(secondAssignment, 80);

		ArrayList<Student> studentList = new ArrayList<Student>();
		studentList.add(studentOne);
		studentList.add(studentTwo);
		studentList.add(studentThree);
		
		Rubric rubricUnderTest = new Rubric(assignments);
		rubricUnderTest.setAssignmentWeight(firstAssignment, 50);
		rubricUnderTest.setAssignmentWeight(secondAssignment, 50);
		
		String firstClass = "First Class";
		
		Classroom testClassGrades = new Classroom(firstClass, studentList, rubricUnderTest);
		
		Map<Student, Double> testMap = testClassGrades.calculateGrades();
		assertEquals(80, testMap.get(studentOne));
		assertEquals(100, testMap.get(studentTwo));
		assertEquals(90, testMap.get(studentThree));
	}
	
	@Test
	void testGetClassGrades() {
		
		ArrayList<Assignment> assignments = new ArrayList<Assignment>();
		
		Assignment firstAssignment = new Assignment("first", 100);
		Assignment secondAssignment = new Assignment("second", 100);
		
		assignments.add(firstAssignment);
		assignments.add(secondAssignment);
		
		//Grade = 80
		Student studentOne = new Student("firstStudent", assignments);
		studentOne.addScoreToAssignment(firstAssignment, 100);
		studentOne.addScoreToAssignment(secondAssignment, 60);
		
		//Grade = 100
		Student studentTwo = new Student("secondStudent", assignments);
		studentTwo.addScoreToAssignment(firstAssignment, 100);
		studentTwo.addScoreToAssignment(secondAssignment, 100);
		
		//Grade = 90
		Student studentThree = new Student("thirdStudent", assignments);
		studentThree.addScoreToAssignment(firstAssignment, 100);
		studentThree.addScoreToAssignment(secondAssignment, 80);

		ArrayList<Student> studentList = new ArrayList<Student>();
		studentList.add(studentOne);
		studentList.add(studentTwo);
		studentList.add(studentThree);
		
		Rubric rubricUnderTest = new Rubric(assignments);
		rubricUnderTest.setAssignmentWeight(firstAssignment, 50);
		rubricUnderTest.setAssignmentWeight(secondAssignment, 50);
		
		String first = "First Class";
		
		Classroom firstClassroom = new Classroom(first, studentList, rubricUnderTest);
		
		Map<Student, Double> firstClassGrades = firstClassroom.getClassGrades(first);
		
		assertEquals(80, firstClassGrades.get(studentOne));
		assertEquals(100, firstClassGrades.get(studentTwo));
		assertEquals(90, firstClassGrades.get(studentThree));
		
	}
}
