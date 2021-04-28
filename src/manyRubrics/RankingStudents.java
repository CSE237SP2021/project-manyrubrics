package manyRubrics;

import java.util.Collections;
import java.util.List;

public class RankingStudents {

	//list of students 
	//sort students based on calculated grades
	private List<Student>listOfStudents;
	private Rubric rubric;
	
	public RankingStudents(List<Student> listOfStudents,Rubric rubric) {
		this.listOfStudents =  listOfStudents;
		this.rubric = rubric; 
	}
	
	public List<Student> RankStudent(){
		Collections.sort(listOfStudents, (Student student1, Student student2)->{
			double score1 = rubric.computeScoreForStudent(student1);
			double score2 = rubric.computeScoreForStudent(student2);
			return score1<score2 ? 1:(score1>score2 ?-1:0);
		});
		return listOfStudents;
	}
}