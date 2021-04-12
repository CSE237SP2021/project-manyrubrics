package manyRubrics;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Driver {
	
	final static int programName = 0;
	final static int inputMode = 1;
	final static int rubricFile = 2;
	final static int studentFile = 3;
	final static int fileModeArgs = 4;
	final static String manualMode = "-m";
	final static String fileMode = "-f";
	
	public static void addAssignment(List<Assignment> assignments){
		
	}
	
	public static void addStudent(List<Student> students){
		
	}
	
	public static void addRubric(List<Rubric> rubrics){
		
	}
	
	public static void listAssignments(List<Assignment> assignments){
		if(!assignments.isEmpty()) {
			for(int i = 0; i < assignments.size(); i++) {
				System.out.println(assignments.get(i).name());
			}
		}else {
			System.out.println("There are no Assignments");
		}
	}
	
	public static void listStudents(List<Student> students){
		if(!students.isEmpty()) {
			for(int i = 0; i < students.size(); i++) {
				System.out.println(students.get(i).name());
			}
		}else {
			System.out.println("There are no Students");
		}
	}
	
	public static void listRubrics(List<Rubric> rubrics){
		if(!rubrics.isEmpty()) {
			for(int i = 0; i < rubrics.size(); i++) {
	            System.out.println(rubrics.get(i).name());
	        }
		}else {
			System.out.println("There are no Rubrics");
		}
	}
	
	public static void deleteAssignment(List<Assignment> assignments){
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        input.close();
        if(!assignments.isEmpty()) {
        	for(int i = 0; i < assignments.size(); i++) {
				if(assignments.get(i).name()==name) {
					assignments.remove(i);
					System.out.println(name + " Successfully Deleted");
					return;
				}
			}
        	System.out.println(name + " is not an assignment");
        }else {
			System.out.println("There are no Assignments");       	
        }
	}
	
	public static void deleteStudent(List<Student> students){
		Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        input.close();
        if(!students.isEmpty()) {
        	for(int i = 0; i < students.size(); i++) {
				if(students.get(i).name()==name) {
					students.remove(i);
					System.out.println(name + " Successfully Deleted");
					return;
				}
			}
        	System.out.println(name + " is not a student");
        }else {
			System.out.println("There are no Students");       	
        }
	}
	 
	public static void deleteRubric(List<Rubric> rubrics){
		Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        input.close();
        if(!rubrics.isEmpty()) {
        	for(int i = 0; i < rubrics.size(); i++) {
				if(rubrics.get(i).name()==name) {
					rubrics.remove(i);
					System.out.println(name + " Successfully Deleted");
					return;
				}
			}
        	System.out.println(name + " is not a rubric");
        }else {
			System.out.println("There are no Rubrics");       	
        }    
	}
	
	public static void calculateGrade(){
		
	}
	
	public static void manualMode(){
		//Manual entry mode in while loop
		boolean exit = false;
        Scanner input = new Scanner(System.in);
        List<Assignment> assignments = new ArrayList<Assignment>();;
        List<Rubric> rubrics = new ArrayList<Rubric>();;
        List<Student> students = new ArrayList<Student>();;
        
		int choice;
		while(!exit) {
			System.out.println("1. Add an Assignment");
			System.out.println("2. Add a Student");
			System.out.println("3. Add a Rubric");
			System.out.println("4. List Assignments");
			System.out.println("5. List Students");
			System.out.println("6. List Rubrics");
			System.out.println("7. Delete an Assignment");
			System.out.println("8. Delete a Student");
			System.out.println("9. Delete a Rubric");
			System.out.println("10. Calculate Grade");
			System.out.println("0. Exit");
			choice = input.nextInt();
			switch(choice) {
			case 1:
				//Add Assignment
				break;
			case 2:
				//Add Student
				break;
			case 3:
				//Add Rubric
				break;
			case 4:
				//List Assignment
				break;
			case 5:
				//List Student
				break;
			case 6:
				//List Rubric
				break;
			case 7:
				//Delete Assignment
				break;
			case 8:
				//Delete Student
				break;
			case 9:
				//Delete Rubric
				break;
			case 10:
				//Calculate Grade
				break;
			case 0:
				exit = true;
				break;
			default:
				break;
			}
		}
		input.close();
	}
	
	public static void fileMode(String[] args){
		try{
			RubricExtractor rubricExtractor = new RubricExtractor(args[rubricFile]);
			List<Assignment> assignmentList = rubricExtractor.getAssignmentList();
			StudentGradeExtractor gradeExtractor = new StudentGradeExtractor(args[studentFile], assignmentList);
		}catch(FileNotFoundException e) {
			
		}
	}
	
	public static void fileModeUsage(String programName) {
		System.out.println(programName + "-f <rubricFile> <studentFile> Usage:");
		System.out.println("<rubricFile>: Text file holding rubric information");
		System.out.println("<studentFile>: Text file holding student information");
	}
	
	public static void progamUsage(String programName){
		System.out.println(programName + " Usage:");
		System.out.println(programName + " -m: Runs program in manual entry mode");
		System.out.println(programName + " -f: Displays usage for file entry mode");
		System.out.println(programName + " -f <rubricFile> <studentFile>: "
				+ "Runs program in file entry mode with <rubricFile> as rubric file and <studentFile> student file");
	}
	
	public static void main(String[] args) {
		int numArgs = args.length-1;
		switch(numArgs) {
		case programName:
			progamUsage(args[programName]);
			break;
		case inputMode:
			if(args[inputMode]==manualMode) {
				manualMode();
			}else if(args[inputMode]==fileMode) {
				fileModeUsage(args[programName]);
			}else {
				progamUsage(args[programName]);
			}
			break;
		case fileModeArgs:
			fileMode(args);
			break;
		default:
			progamUsage(args[programName]);
			break;
		}
	}
}
