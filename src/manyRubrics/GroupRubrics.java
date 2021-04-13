package manyRubrics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupRubrics {

	//Name for which class these rubrics are for
	private ArrayList<Rubric> classRubric;
	
	public GroupRubrics (ArrayList<Rubric> rubricsToBeGrouped) {
		
		this.classRubric = rubricsToBeGrouped;
		
	}
	
	//Takes in student and returns best grade for them
	public double bestGrade(Student selectedStudent) {
		
		double highestGrade = 0.0;
		
		for(int x = 0; x < classRubric.size(); x++) {
			
			Rubric currentRubric = classRubric.get(x);
			if(currentRubric.computeScoreForStudent(selectedStudent) > highestGrade) {
				highestGrade = currentRubric.computeScoreForStudent(selectedStudent);
			}
		}
		
		return highestGrade;
	}
	
}
