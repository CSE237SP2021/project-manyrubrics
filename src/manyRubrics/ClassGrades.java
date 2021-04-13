package manyRubrics;

import java.util.List;
import java.util.Map;

public class ClassGrades {
	
	private List<Student> studentList;
	private Rubric classRubric;
	
	//Contains list of students associated with the passed in class
	private Map<Student, Double> classGrades;
	
	public ClassGrades (List<Student> students, Rubric rubricForClass) {
		this.studentList = students;
		this.classRubric = rubricForClass;
	}
	
	public void calculateGrades() {
		for(int x = 0; x < studentList.size(); x++) {
			Student currentStudent = studentList.get(x);
			double studentScore = classRubric.computeScoreForStudent(currentStudent);
			classGrades.put(currentStudent, studentScore);
		}
	}
	
	//Returns 
	public Map<Student, Double> getGrades() {
		return classGrades;
	}
}