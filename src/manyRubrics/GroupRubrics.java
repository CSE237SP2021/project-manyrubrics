package manyRubrics;
import java.util.List;

public class GroupRubrics {

	//Name for which class these rubrics are for
	//private String className;
	private List<Rubric> classRubric;
	//private Map<String, List<Rubric>> groupedRubrics;
	
	public GroupRubrics (List<Rubric> rubricsToBeGrouped) {
		
		//this.className = name;
		this.classRubric = rubricsToBeGrouped;
		
		//this.groupedRubrics = new HashMap<String, List<Rubric>>();
		
		//Puts the rubrics for a specific class in the map groupedRubrics
		//this.groupedRubrics.put(className, classRubric);
		
	}
	
	//Takes in student and returns best grade for them
	public double bestGrade(Student selectedStudent) {
		
		double highestGrade = 0.0;
		
		for(int x = 0; x < classRubric.size(); x++) {
			if(classRubric.get(x).computeScoreForStudent(selectedStudent) > highestGrade) {
				highestGrade = classRubric.get(x).computeScoreForStudent(selectedStudent);
			}
		}
		
		return highestGrade;
	}
	
	/*
	//Returns total of after the addition of all the max assignment grades 
	public double getMaxRubricGradeForGroupedRubrics(String className) {
		
		double maxRubricGradeForGroupedRubrics = 0.0;
		
		List<Rubric> selectedClass = this.groupedRubrics.get(className);
		
		for(int x = 0; x < selectedClass.size(); x++) {
			
			if(selectedClass.get(x).totalRubricGrade() > maxRubricGradeForGroupedRubrics) {
				maxRubricGradeForGroupedRubrics = selectedClass.get(x).totalRubricGrade();
			}
		}
		
		return maxRubricGradeForGroupedRubrics;
	}
	*/
}
