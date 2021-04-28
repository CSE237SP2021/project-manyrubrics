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
	static Scanner input = new Scanner(System.in);
	
	public static void setScores(Student student) {
		double score;
		for (Entry<Assignment, Double> entry : student.assignments().entrySet()) {
			System.out.println("Enter " + student.name() + "'s Score for assignment: " + entry.getKey().name());
			score = input.nextDouble();
			input.nextLine();
			student.addScoreToAssignment(entry.getKey(), score);
		}
	}

	public static void setWeights(Rubric rubric) {
		int weight;
		for (Entry<Assignment, Integer> entry : rubric.assignments().entrySet()) {
			System.out.println("Enter " + rubric.name() + "'s Weight for assignment: " + entry.getKey().name());
			weight = input.nextInt();
			input.nextLine();
			rubric.setAssignmentWeight(entry.getKey(), weight);
		}
	}
	
	public static void assignmentEntry(List<Assignment> assignments) {
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
	        System.out.println("\nEnter 'stop' to stop entry or anything else to add another Assignment");
	        choice = input.nextLine();
	        if(choice.equals("stop")) {
	        	quit = true;
	        }
		}
	}
	
	public static void addStudent(List<Student> students, List<Assignment> assignments){
        System.out.println("Student Name to Add: ");
        String name = input.nextLine();
        Student student = new Student(name, assignments);
        setScores(student);
        students.add(student);
	}
	
	public static void addRubric(List<Rubric> rubrics, List<Assignment> assignments){
        System.out.println("Rubric Name to Add: ");
        String name = input.nextLine();
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
	
	public static int findStudent(List<Student> students){
		if(!students.isEmpty()) {
			System.out.println("Enter name of student");
			String studentName = input.nextLine();
			for(int i = 0; i < students.size(); i++) {
				if(students.get(i).name().equals(studentName)) {
					return i;
				}
			}
			System.out.println(studentName + " is not a student");
			return -1;
		}else {
			System.out.println("There are no Students");   
			return -1;
		}
	}
	
	public static void removeStudent(List<Student> students){
        int student = findStudent(students);
        if(student!=-1){
        	students.remove(student);
        	System.out.println("Student successfully removed");
        }
	}
	 
	public static int findRubric(List<Rubric> rubrics){
		if(!rubrics.isEmpty()) {
			System.out.println("Enter name of rubric");
			String rubricName = input.nextLine();
			for(int i = 0; i < rubrics.size(); i++) {
				if(rubrics.get(i).name().equals(rubricName)) {
					return i;
				}
			}
			System.out.println(rubricName + " is not a rubric");
			return -1;
		}else {
			System.out.println("There are no rubrics");   
			return -1;
		}
	}
	
	public static void removeRubric(List<Rubric> rubrics){
		int rubric = findRubric(rubrics);
        if(rubric!=-1){
        	rubrics.remove(rubric);
        	System.out.println("Rubric successfully removed");
        }
	}
	
	public static void calculateGrade(List<Rubric> rubrics, List<Student> students, List<Assignment> assignments){
		int student = findStudent(students);
		int rubric = findRubric(rubrics);  
        if(student != -1 && rubric != -1 ) {
        	double grade = rubrics.get(rubric).computeScoreForStudent(students.get(student));
        	System.out.println(students.get(student).name() + " Has a Grade of: " + grade);
        }
	}
	
	public static void rankStudents(List<Rubric> rubrics, List<Student> listOfStudents) {
		System.out.println("Rubric Name to Use: ");
		String rubricName = input.nextLine();
		//List<Student>listCopy = new ArrayList<Student>();
		double grade = -1;
		if (!rubrics.isEmpty()) {
			for (int i = 0; i < rubrics.size(); i++) {
				if (rubrics.get(i).name().equals(rubricName)) {
					RankingStudents rankStudents = new RankingStudents(listOfStudents,rubrics.get(i));
					listOfStudents = rankStudents.RankStudent();
					
				}
			}
		}
		else {
			System.out.println("There are no Rubrics");
			return;
		}
		String name ="";
		for(int i =0; i<listOfStudents.size();i++) {
			if(i==listOfStudents.size()-1) {
				 name += listOfStudents.get(i).name();
			}
			else {
				 name += listOfStudents.get(i).name() + ", ";
			}
			
		}
		System.out.println("The order of the ranked students: " + name);
		System.out.println("");
	}
    
	public static void manualMode() {
        int choice = 0;
		boolean quit = false;

		List<Assignment> assignments = new ArrayList<Assignment>();
        List<Rubric> rubrics = new ArrayList<Rubric>();
        List<Student> students = new ArrayList<Student>();
        
		System.out.println("Before proceeding to rubric creation and grading, please input all asignments");
		assignmentEntry(assignments);
		while(!quit) {
			System.out.println("1. Add a Student");
			System.out.println("2. List Students");
			System.out.println("3. Remove a Student");
			
			System.out.println("4. Add a Rubric");
			System.out.println("5. List Rubrics");
			System.out.println("6. Remove a Rubric");
			
			System.out.println("7. Calculate Grade");
			System.out.println("8. Rank Students by Grade");
			System.out.println("0. Exit");
			choice = input.nextInt();
			input.nextLine();			
			if(choice == 0) {
				quit = true;
			} else {
				manualChoice(choice, rubrics, students, assignments);
			}
		}
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
			case 8: 
				rankStudents(rubrics, students);
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
		input.close();
	}
}
