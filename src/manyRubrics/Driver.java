package manyRubrics;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

public class Driver {
	
	final static int inputModeArgs = 1;
	final static int rubricFile = 1;
	final static int studentFile = 2;
	final static int fileModeArgs = 3;
	final static String manualMode = "-m";
	final static String fileMode = "-f";
	
	public static void setScores(Student student) {
        Scanner input = new Scanner(System.in);
        double score;
		for (Entry<Assignment, Double> entry : student.assignments().entrySet()) {
			System.out.println("Enter " + student.name() + "'s Score for assignment: " + entry.getKey().name());
			score = input.nextDouble();
			input.nextLine();
			student.addScoreToAssignment(entry.getKey(), score);
		}
		input.close();
	}

	public static void setWeights(Rubric rubric) {
		Scanner input = new Scanner(System.in);
		int weight;
		for (Entry<Assignment, Integer> entry : rubric.assignments().entrySet()) {
			System.out.println("Enter " + rubric.name() + "'s Weight for assignment: " + entry.getKey().name());
			weight = input.nextInt();
			input.nextLine();
			rubric.setAssignmentWeight(entry.getKey(), weight);
		}
		input.close();
	}
	
	public static void assignmentEntry(List<Assignment> assignments) {
		Scanner input = new Scanner(System.in);
		String name, choice;
		int maxScore;
		boolean quit = false;
		while(!quit){
			System.out.println("Assignment Name to Add: ");
	        name = input.nextLine();
	        System.out.println("Max score for " + name);
	        maxScore = input.nextInt();
	        input.nextLine();
	        Assignment assignment = new Assignment(name, maxScore);
	        assignments.add(assignment);
	        System.out.println("Enter 'c' to add another Assignment Or 'q' to stop)");
	        choice = input.nextLine();
	        if(choice.equals("q")) {
	        	quit = true;
	        }
		}
		input.close();
	}
	
	public static void addStudent(List<Student> students, List<Assignment> assignments){
        Scanner input = new Scanner(System.in);
        System.out.println("Student Name to Add: ");
        String name = input.nextLine();
        input.close();
        Student student = new Student(name, assignments);
        setScores(student);
        students.add(student);
	}
	
	public static void addRubric(List<Rubric> rubrics, List<Assignment> assignments){
		Scanner input = new Scanner(System.in);
        System.out.println("Rubric Name to Add: ");
        String name = input.nextLine();
        input.close();
        Rubric rubric = new Rubric(name, assignments);
        setWeights(rubric);
        rubrics.add(rubric);
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
	
	public static void removeStudent(List<Student> students){
		Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        input.close();
        if(!students.isEmpty()) {
        	for(int i = 0; i < students.size(); i++) {
				if(students.get(i).name().equals(name)) {
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
	 
	public static void removeRubric(List<Rubric> rubrics){
		Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        input.close();
        if(!rubrics.isEmpty()) {
        	for(int i = 0; i < rubrics.size(); i++) {
				if(rubrics.get(i).name().equals(name)) {
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
	
	public static void calculateGrade(List<Rubric> rubrics, List<Student> students, List<Assignment> assignments){
		Scanner input = new Scanner(System.in);
        System.out.println("Student Name to Grade: ");
        String studentName = input.nextLine();
        System.out.println("Rubric Name to Use: ");
        String rubricName = input.nextLine();
        input.close();
        double grade = -1;
        Student student = new Student("", assignments);
        if(!students.isEmpty()) {
        	for(int i = 0; i < students.size(); i++) {
				if(students.get(i).name()==studentName) {
					student = students.get(i);
				}
			}
        	if(student.name()=="") {
        		System.out.println(studentName + " is not a student");
        		return;
        	}
        }else {
			System.out.println("There are no Students");
			return;
        }
        if(!rubrics.isEmpty()) {
        	for(int i = 0; i < rubrics.size(); i++) {
				if(rubrics.get(i).name()==rubricName) {
					grade = rubrics.get(i).computeScoreForStudent(student);
				}
			}
        	if(grade == -1) {
        		System.out.println(rubricName + " is not a rubric");
        		return;
        	}
        }else {
			System.out.println("There are no Rubrics"); 
			return;
        }
        System.out.println(studentName + " Has a Grade of: " + grade);
	}
	
	
	public static void manualMode() {
        int choice = 0;
		boolean quit = false;

		List<Assignment> assignments = new ArrayList<Assignment>();
        List<Rubric> rubrics = new ArrayList<Rubric>();
        List<Student> students = new ArrayList<Student>();
        
		System.out.println("Before proceeding to rubric creation and grading, please input all asignments");
		assignmentEntry(assignments);
        Scanner input = new Scanner(System.in);
		while(!quit) {
			System.out.println("1. Add a Student");
			System.out.println("2. List Students");
			System.out.println("3. Remove a Student");
			
			System.out.println("4. Add a Rubric");
			System.out.println("5. List Rubrics");
			System.out.println("6. Remove a Rubric");
			
			System.out.println("7. Calculate Grade");
			System.out.println("0. Exit");
			choice = input.nextInt();
			input.nextLine();			
			if(choice == 0) {
				quit = true;
			} else {
				manualChoice(choice, rubrics, students, assignments);
			}
		}
		input.close();
	}

	public static void manualChoice(int choice, List<Rubric> rubrics, List<Student> students, List<Assignment> assignments) {
		System.out.println(choice);
		switch(choice) {
			case 1:
				addStudent(students, assignments);				
				break;
			case 2:
				listStudents(students);
				break;
			case 3:
				removeStudent(students);
				break;
			case 4:
				addRubric(rubrics, assignments);
				break;
			case 5:
				listRubrics(rubrics);
				break;
			case 6:
				removeRubric(rubrics);
				break;
			case 7:
				calculateGrade(rubrics,students,assignments);
				break;
			default:
				break;
		}
	}
	
	
	public static void fileMode(String[] args){
		try{
			RubricExtractor rubricExtractor = new RubricExtractor(args[rubricFile]);
			List<Assignment> assignmentList = rubricExtractor.getAssignmentList();
			StudentGradeExtractor gradeExtractor = new StudentGradeExtractor(args[studentFile], assignmentList);
		}catch(FileNotFoundException e) {
			
		}
	}
	
	public static void fileModeUsage() {
		System.out.println("-f <rubricFile> <studentFile> Usage:");
		System.out.println("<rubricFile>: Text file holding rubric information");
		System.out.println("<studentFile>: Text file holding student information");
	}
	
	
	public static void progamUsage(){
		System.out.println("Usage:");
		System.out.println("-m: Runs program in manual entry mode");
		System.out.println("-f: Displays usage for file entry mode");
		System.out.println("-f <rubricFile> <studentFile>: "
				+ "Runs program in file entry mode with <rubricFile> as rubric file and <studentFile> student file");
	}
	
	
	public static void inputMode(String[] args) {
		System.out.println(args[inputModeArgs-1]);
		if(args[inputModeArgs-1].equals(manualMode)) {
			manualMode();
		}else if(args[inputModeArgs-1].equals(fileMode)) {
			fileModeUsage();
		}else {
			progamUsage();
		}
	}
	
	public static void main(String[] args) {
		int numArgs = args.length;
		System.out.println(numArgs);
		switch(numArgs) {
		case inputModeArgs:
			inputMode(args);
			break;
		case fileModeArgs:
			fileMode(args);
			break;
		}
	}
}
