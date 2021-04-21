package manyRubrics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class RubricExtractor {
	private List<Assignment> assignments;
	private List<Rubric> rubrics;
	private Scanner scanner;
	private String rubricFile;
	
	public RubricExtractor(String filename) throws FileNotFoundException, DataFormatException {
		rubricFile = filename;
		assignments = new ArrayList<Assignment>();
		rubrics = new ArrayList<Rubric>();
		
		File infile = new File(filename);
		scanner = new Scanner(infile);
		// first extract the list of assignments
		if(scanner.hasNextLine()) {
			extractAssignmentList();
		} else {
			throw new DataFormatException("The rubric file must contain a list of space-separated assignments that are not interpretable as weights. add one and try again");
		}
		// then go through every remaining row and extract the weights of the assignments
		while(scanner.hasNextLine()) {
			try {
				rubrics.add(getNextRubric());				
			} catch(DataFormatException e) {
				System.out.println(e.getMessage());
			} catch(NumberFormatException e) {
				System.out.println(e.getMessage());
			}
		}
		if(rubrics.size() == 0) {
			throw new DataFormatException("No valid rubrics were read from the file. Please include at least one valid rubric and try again");
		}
	}

	private void extractAssignmentList() throws DataFormatException {
		String assignmentString = scanner.nextLine();
		String[] assignmentNames = assignmentString.split(" ");
		for(String assignmentName : assignmentNames) {
			if(assignmentName.matches("^\\d+$")) {
				throw new DataFormatException("The rubric file must contain a list of space-separated assignments that are not interpretable as weights. add one and try again");
			}
			assignments.add(new Assignment(assignmentName));
		}
	}
	
	private Rubric getNextRubric() throws DataFormatException, NumberFormatException {
		String rubricWeights = scanner.nextLine();
		String[] weights = rubricWeights.split("\\s+");
		if(weights.length != assignments.size()) {
			throw new DataFormatException("The rubric: " + rubricWeights + " does not have a weight for every assignment, or has too many weights. This rubric will not be used to grade the students");
		}
		Rubric result = new Rubric(assignments);
		for(int i = 0; i < assignments.size(); ++i) {
			try {
				int weight = Integer.parseInt(weights[i]);
				result.setAssignmentWeight(assignments.get(i), weight);			
			} catch (NumberFormatException e) { // rethrow a new exception with a application specific error message
				throw new NumberFormatException("Assignment weight " + weights[i] + " in rubric " + rubricWeights + " cannot be converted to an appropriate value. This rubric will not be used to grade the students");
			}
		}
		if(!result.isValid()) {
			System.out.println("The rubric: " + rubricWeights + " Has a maximum weight different from 100.\nDo you still want to use this rubric to grade the students? (Y/N):");
			Scanner readUserInput = new Scanner(System.in);
			boolean request = true;
			boolean stopPrompt = false;
			do {
				while(!readUserInput.hasNext()) {
					
				}
				String read = readUserInput.nextLine();
				read = read.trim();
				if(read.equals("Y")) {
					request = true;
					stopPrompt = true;
				} else if(read.equals("N")) {
					request = false;
					stopPrompt = true;
				}
			} while(!stopPrompt);
			
			if(request == false) {
				readUserInput.close();
				throw new DataFormatException("Per user request, rubric: " + rubricWeights + " will not be considered in grading the students");
			}
			readUserInput.close();
		}
		return result;
	}

	public List<Assignment> getAssignmentList() {
		return assignments;
	}

	public List<Rubric> getRubricList() {
		return rubrics;
	}
	
	public String getRubricFile() {
		return rubricFile;
	}

}
