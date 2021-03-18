package manyRubrics;

public class Assignment {

	private String name;
	private double score;
	
	public Assignment(String name) {
		this.name = name;
		this.score = 0;
	}
	
	public String name() {
		return this.name;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
}
