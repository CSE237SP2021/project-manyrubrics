package manyRubrics;

import java.io.FileNotFoundException;
import java.util.List;

public class Driver {
	
	final static int programName = 0;
	final static int rubricFile = 1;
	final static int studentFile = 2;
	final static int expectedNumberOfArg = 3;


	public static void main(String[] args) {
		if(args.length == expectedNumberOfArg) {
			try {
				RubricExtractor rubricExtractor = new RubricExtractor(args[rubricFile]);
				List<Assignment> assignmentList = rubricExtractor.getAssignmentList();
				StudentGradeExtractor gradeExtractor = new StudentGradeExtractor(args[studentFile], assignmentList);
			} catch(FileNotFoundException e) {
				
			}
		} else {
			System.out.println("Incorrect Number of Command Line Arguments");
		}
	}
	
}
