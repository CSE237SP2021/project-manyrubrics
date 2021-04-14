package manyRubrics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class StudentGradeExtractor {
	private List<Assignment> assignmentList;
	private List<Student> students;
	private Scanner scanner;
	
	public StudentGradeExtractor(String filename, List<Assignment> assignmentList) throws FileNotFoundException {
		this.students = new ArrayList<Student>();
		this.assignmentList = assignmentList;
		File studentFile = new File(filename);
		scanner = new Scanner(studentFile);
		while(scanner.hasNextLine()) {
			try {
				students.add(getNextStudent());
			} catch (DataFormatException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	private Student getNextStudent() throws DataFormatException {
		String studentString = scanner.nextLine();
		String[] separatedStudentString = studentString.split(" ");
		String studentName = separatedStudentString[0];
		
		if(separatedStudentString.length != assignmentList.size() + 1) {
			throw new DataFormatException(studentName + " had grades for the wrong number of assignments. This student will not be graded.");
		} else {
			
			Student newStudent = new Student(studentName, this.assignmentList);
			for(int i = 0; i < this.assignmentList.size(); ++i) {
				
				Assignment assignmentToBeGraded = this.assignmentList.get(i);
				double studentScore = Double.parseDouble(separatedStudentString[i+1]);
				
				newStudent.addScoreToAssignment(assignmentToBeGraded, studentScore);
			}
			return newStudent;
		}

	}
	
	public List<Student> getStudentList() {
		return this.students;
	}

}
