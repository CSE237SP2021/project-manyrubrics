package manyRubrics;

public class Assignment {

	private String name;
	private int maxAssignmentGrade;
	
	public Assignment(String name, int totalAssignmentGrade) {
		this.name = name;
		this.maxAssignmentGrade = totalAssignmentGrade;
	}
	
	public String name() {
		return this.name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Assignment other = (Assignment) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	//Returns the max points the assignment can be out of
	public int getMaxAssignmentGrade() {
		return this.maxAssignmentGrade;
	}
}