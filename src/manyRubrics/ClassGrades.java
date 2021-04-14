package manyRubrics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassGrades {
	
	private List<Student> studentList;
	private Rubric classRubric;
	
	//Contains list of students associated with the passed in class
	
	public ClassGrades (List<Student> students, Rubric rubricForClass) {
		this.studentList = students;
		this.classRubric = rubricForClass;
	}
	
	public Map<Student, Double> calculateGrades() {
		
		Map<Student, Double> classGrades = new HashMap<Student, Double>();
		
		double studentScore = 0.0;
		
		for(int x = 0; x < studentList.size(); x++) {
			studentScore = classRubric.computeScoreForStudent(studentList.get(x));
			classGrades.put(studentList.get(x), studentScore);
		}
		
		return classGrades;
	}
}