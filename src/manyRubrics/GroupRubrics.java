package manyRubrics;
import java.util.List;

public class GroupRubrics {


	private List<Rubric> classRubric;

	public GroupRubrics (List<Rubric> rubricsToBeGrouped) {
		this.classRubric = rubricsToBeGrouped;
	}
	

	public double bestGrade(Student selectedStudent) {
		
		double highestGrade = 0.0;
		
		for(int x = 0; x < classRubric.size(); x++) {
			if(classRubric.get(x).computeScoreForStudent(selectedStudent) > highestGrade) {
				highestGrade = classRubric.get(x).computeScoreForStudent(selectedStudent);
			}
		}
		
		return highestGrade;
	}
	
}