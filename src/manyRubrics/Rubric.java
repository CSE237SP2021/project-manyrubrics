package manyRubrics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rubric {
	
	private Map<Assignment, Integer> assignmentsToWeights;
	private int maximumWeight;
	private List<Assignment> passedInAssignments;
	private String name;
	
	public Rubric(List<Assignment> assignments) {
		this.assignmentsToWeights = new HashMap<Assignment, Integer>();
		this.passedInAssignments = assignments;
		
		// most classes have a maximum score of 100, so the constructor that does not specify a different max score defaults
		// to 100;
		this.maximumWeight = 100;

		for(Assignment assignment : assignments) {
			this.assignmentsToWeights.put(assignment, 0);
		}
	}
	
	public String name() {
		return this.name;
	}
	
	public boolean isValid() {
		int totalWeight = this.computeTotalWeight();
		
		return totalWeight == this.maximumWeight;
	}
	
	private int computeTotalWeight() {
		int totalWeight = 0;
		for(int weight : assignmentsToWeights.values()) {
			totalWeight += weight;
		}
		return totalWeight;
	}
	
	public double computeScoreForStudent(Student studentToGrade) {
		double score = 0;
		for(Assignment assignment : this.assignmentsToWeights.keySet()) {
			
			// line below with assignment.getScore() violates Law of Demeter. Should we create a 
			// Student class that maps assignments to scores rather than having assignments hold scores?
			double studentScore = studentToGrade.getScoreForAssignment(assignment);
			double weightedScore = studentScore * this.assignmentsToWeights.get(assignment);
			double scoreContribution = weightedScore / this.maximumWeight;
			score += scoreContribution;
		}
		
		return score;
	}
	
	public void setAssignmentWeight(Assignment assignment, int weight) {
		this.assignmentsToWeights.replace(assignment, weight);
	}
	
	//Calculates total rubric grade
	public int totalRubricGrade() {
		
		int totalRubricGrade = 0;
		for(Assignment assignment : passedInAssignments) {
			totalRubricGrade += assignment.getMaxAssignmentGrade();
		}
		
		return totalRubricGrade;
	}
}