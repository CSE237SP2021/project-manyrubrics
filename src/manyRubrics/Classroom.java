package manyRubrics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Classroom {
	
	private String className;
	private List<Student> studentList;
	private Rubric classRubric;
	
	private Map<Student, Double> studentGrades = new HashMap<Student, Double>();
	private Map<String, Map<Student, Double>> classGrades = new HashMap<String, Map<Student, Double>>();
	
	public Classroom (String nameOfClass, List<Student> students, Rubric rubricForClass) {
		
		this.className = nameOfClass;
		this.studentList = students;
		this.classRubric = rubricForClass;
		
	}
	
	//Calculates the grades for a specified class and returns a map holding those grades
	public Map<Student, Double> calculateGrades() {
		
		double studentScore = 0.0;
		
		for(int x = 0; x < studentList.size(); x++) {
			studentScore = classRubric.computeScoreForStudent(studentList.get(x));
			studentGrades.put(studentList.get(x), studentScore);
		}
		
		classGrades.put(className, studentGrades);
		
		return studentGrades;
	}
	
	/*
	public void updateMapWithClassGrades() {
		
		classGrades.put(className, studentGrades);
		
	}
	*/
	
	//Gets the grades for a specified class
	public Map<Student, Double> getClassGrades(String desiredClass) {
		
		for (Map.Entry<String, Map<Student, Double>> entry : classGrades.entrySet()) {
			
			String key = entry.getKey();
			Map<Student, Double> value = entry.getValue();
			
			if (key.equals(desiredClass)) {
				return value;
			}
			
		}
		
		return null;
		
	}
}
