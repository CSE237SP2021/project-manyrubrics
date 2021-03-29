package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import manyRubrics.Assignment;
import manyRubrics.GroupRubrics;
import manyRubrics.Rubric;

class GroupRubricsTest {

	@Test
	void testMaxRubricGrade() {
		
		ArrayList<Assignment> firstClassAssignments = new ArrayList<Assignment>();
		Assignment firstAssignment = new Assignment("first", 100);
		Assignment secondAssignment = new Assignment("second", 100);
		firstClassAssignments.add(firstAssignment);
		firstClassAssignments.add(secondAssignment);
		
		Rubric firstClassRubrics = new Rubric(firstClassAssignments);
		
		ArrayList<Assignment> secondClassAssignments = new ArrayList<Assignment>();
		Assignment assignmentOne = new Assignment("first", 100);
		Assignment assignmentTwo = new Assignment("second", 100);
		secondClassAssignments.add(assignmentOne);
		secondClassAssignments.add(assignmentTwo);
		
		Rubric secondClassRubrics = new Rubric(secondClassAssignments);
		
		ArrayList<Rubric> needGrouping = new ArrayList<Rubric>();
		needGrouping.add(firstClassRubrics);
		needGrouping.add(secondClassRubrics);
		
		String className = "CSE237";
		GroupRubrics groupedRubrics = new GroupRubrics(className, needGrouping);
		
		double testMaxRubricGrade = groupedRubrics.getMaxRubricGradeForGroupedRubrics(className);
		
		assertEquals(200.0, testMaxRubricGrade);
	}
}