package manyRubrics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rubric {
	
	private Map<Assignment, Integer> assignmentsToWeights;
	private final int maximumWeight = 100;
	
	public Rubric(List<Assignment> assignments) {
		this.assignmentsToWeights = new HashMap<Assignment, Integer>();
		
		// most classes have a maximum score of 100, so the constructor that does not specify a different max score defaults
		// to 100;
		
		for(Assignment assignment : assignments) {
			this.assignmentsToWeights.put(assignment, 0);
		}
	}
	
	public Rubric(Rubric other) {//Rubric copy = new Rubric(original);<- to call rubric copy constructor
		this.assignmentsToWeights = new HashMap<Assignment, Integer>();

		// most classes have a maximum score of 100, so the constructor that does not
		// specify a different max score defaults
		// to 100;
		

		for (Map.Entry<Assignment, Integer> assignment : other.assignmentsToWeights.entrySet()) {
			this.assignmentsToWeights.put(assignment.getKey(), assignment.getValue()); 
			
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
	
	
	
	//add assignments 
	//remove assignments 
	

}
