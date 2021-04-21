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
	
	public StudentGradeExtractor(String filename, List<Assignment> assignmentList) throws FileNotFoundException, DataFormatException {
		this.students = new ArrayList<Student>();
		this.assignmentList = assignmentList;
		File studentFile = new File(filename);
		scanner = new Scanner(studentFile);
		if(scanner.hasNextLine()) {
			checkAssignmentList();
			while(scanner.hasNextLine()) {
				try {
					students.add(getNextStudent());
				} catch (DataFormatException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
	
	private void checkAssignmentList() throws DataFormatException {
		String assignmentString = scanner.nextLine().trim();
		String[] assignmentNames = assignmentString.split(" ");
		ArrayList<Assignment> assignments = new ArrayList<Assignment>();
		for(String assignmentName : assignmentNames) {
			assignments.add(new Assignment(assignmentName));
		}
		if(assignments.size() != assignmentList.size()) {
			throw new DataFormatException("Incorrect number of assignments in the student grade file! Try with a different file");
		} else {
			for(int i = 0; i < assignments.size(); ++i) {
				if(!assignments.get(i).equals(assignmentList.get(i))) {
					throw new DataFormatException("Assignment " + assignments.get(i) + " does not exist in the rubrics. Cannot proceed grading.");
				}
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
