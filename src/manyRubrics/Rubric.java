package manyRubrics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rubric {
	
	private Map<Assignment, Integer> assignmentsToWeights;
	private int maximumWeight;
	
	public Rubric(List<Assignment> assignments) {
		this.assignmentsToWeights = new HashMap<Assignment, Integer>();
		
		// most classes have a maximum score of 100, so the constructor that does not specify a different max score defaults
		// to 100;
		this.maximumWeight = 100;
		
		for(Assignment assignment : assignments) {
			this.assignmentsToWeights.put(assignment, 0);
		}
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
	
	
	public void setAssignmentWeight(Assignment assignment, int weight) {
		this.assignmentsToWeights.replace(assignment, weight);
	}
	
	public double computeScore() {
		double score = 0;
		for(Assignment assignment : this.assignmentsToWeights.keySet()) {
			double scoreContribution = assignment.getScore() * this.assignmentsToWeights.get(assignment) / this.maximumWeight;
			score += scoreContribution;
		}
		
		return score;
	}
	

}
