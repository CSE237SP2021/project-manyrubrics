package manyRubrics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {
	
	private String studentName;
	private Map<Assignment, Double> assigmentsToScores;
	
	public Student(String name, List<Assignment> assignments) {
		this.studentName = name;
		this.assigmentsToScores = new HashMap<Assignment, Double>();
		
		for(Assignment assignment : assignments) {
			this.assigmentsToScores.put(assignment, 0.0);
		}
	}
	
	public void setScoreToAssignment(Assignment assignmentWithScore, double score) {
		this.assigmentsToScores.replace(assignmentWithScore, score);
	}
	
	public double getScoreForAssignment(Assignment assignmentToRetrieveScoreFor) {
		return this.assigmentsToScores.get(assignmentToRetrieveScoreFor);
	}
	

}
