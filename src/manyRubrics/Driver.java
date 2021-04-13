package manyRubrics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

public class Driver {
	
	final static int noInput = 0;
	final static int justMode = 1;
	final static int fileModeArgs = 3;
	
	final static int programMode = 0;
	final static int rubricFile = 1;
	final static int studentFile = 2;
	
	final static String manualMode = "-m";
	final static String fileMode = "-f";
	
	public static void main(String[] args) {
		int numArgs = args.length;
		switch(numArgs) {
		case noInput:		
			progamUsage("Driver");
			break;
		case justMode:
			if(args[programMode]==manualMode) {
				manualMode();
			}else if(args[programMode]==fileMode) {
				fileModeUsage("Driver");
			}else {
				progamUsage("Driver");
			}
			break;
		case fileModeArgs:
			fileMode(args);				
			break;
		default:
			progamUsage("Driver");
			break;
		}
	}
	
	public static void manualMode(){
		//Manual entry mode in while loop
		boolean exit = false;
        Scanner input = new Scanner(System.in);
        List<Assignment> assignments = new ArrayList<Assignment>();
        List<Rubric> rubrics = new ArrayList<Rubric>();
        List<Student> students = new ArrayList<Student>();
        
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
				addAssignment(rubrics,students,assignments);
				break;
			case 2:
				addStudent(students, assignments);
				break;
			case 3:
				addRubric(rubrics, assignments);
				break;
			case 4:
				listAssignments(assignments);
				break;
			case 5:
				listStudents(students);
				break;
			case 6:
				listRubrics(rubrics);
				break;
			case 7:
				deleteAssignment(rubrics,students,assignments);
				break;
			case 8:
				deleteStudent(students);
				break;
			case 9:
				deleteRubric(rubrics);
				break;
			case 10:
				calculateGrade(rubrics,students,assignments);
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
			writeToFile(gradeExtractor.getStudentList(), rubricExtractor.getRubricList());
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeToFile(List<Student> students, List<Rubric> rubrics) throws IOException {
		GroupRubrics grader = new GroupRubrics(rubrics);
		File outfile = new File("Final_Grades.txt");
		StringBuilder finalGrades = new StringBuilder();
		for(Student student : students) {
			finalGrades.append(student.name());
			finalGrades.append(": ");
			finalGrades.append(grader.bestGrade(student));
			finalGrades.append('\n');
		}
		FileWriter writer = new FileWriter(outfile);
		writer.write(finalGrades.toString());
		writer.close();
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
	
	public static void setScores(Student student) {
        Scanner input = new Scanner(System.in);
        double score;
		for (Entry<Assignment, Double> entry : student.assignments().entrySet()) {
			System.out.println("Enter " + student.name() + "'s Score for assignment: " + entry.getKey().name());
			score = input.nextDouble();
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
			rubric.setAssignmentWeight(entry.getKey(), weight);
		}
		input.close();
	}
	
	public static void addAssignment(List<Rubric> rubrics, List<Student> students, List<Assignment> assignments){
		Scanner input = new Scanner(System.in);
        System.out.println("Assignment Name to Add: ");
        String name = input.nextLine();
        System.out.println("Max score for Assignment: ");
        int maxScore = input.nextInt();
        input.close();
        Assignment assignment = new Assignment(name, maxScore);
        assignments.add(assignment);
        //Update Students and Rubrics

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
	
	public static void deleteAssignment(List<Rubric> rubrics, List<Student> students, List<Assignment> assignments){
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        input.close();
        if(!assignments.isEmpty()) {
        	for(int i = 0; i < assignments.size(); i++) {
				if(assignments.get(i).name()==name) {
					assignments.remove(i);
					System.out.println(name + " Successfully Deleted");
					//Update Students and Rubrics

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
	
	
}
