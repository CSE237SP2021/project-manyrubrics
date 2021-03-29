package manyRubrics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RubricExtractor {
	private List<Assignment> assignments;
	private List<Rubric> rubrics;
	private Scanner scanner;
	
	public RubricExtractor(String filename) {
		assignments = new ArrayList<Assignment>();
		rubrics = new ArrayList<Rubric>();
		
		File infile = new File(filename);
		try {
			scanner = new Scanner(infile);
			// first extract the list of assignments
			if(scanner.hasNextLine()) {
				extractAssignmentList();
			}
			// then go through every remaining row and extract the weights of the assignments
			while(scanner.hasNextLine()) {
				rubrics.add(getNextRubric());
			}
		} catch (FileNotFoundException e) {
			assignments = null;
			rubrics = null;
		}
	}

	private void extractAssignmentList() {
		String assignmentString = scanner.nextLine();
		String[] assignmentNames = assignmentString.split(" ");
		for(String assignmentName : assignmentNames) {
			assignments.add(new Assignment(assignmentName));
		}
	}
	
	private Rubric getNextRubric() {
		String rubricWeights = scanner.nextLine();
		String[] weights = rubricWeights.split(" ");
		Rubric result = new Rubric(assignments);
		for(int i = 0; i < assignments.size(); ++i) {
			result.setAssignmentWeight(assignments.get(i), Integer.parseInt(weights[i]));
		}
		return result;
	}

	public List<Assignment> getAssignmentList() {
		return assignments;
	}

	public List<Rubric> getRubricList() {
		return rubrics;
	}

}
