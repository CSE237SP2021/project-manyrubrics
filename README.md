# Many Rubrics

By <b>Angelica Harris</b>, <b>Brandon Li</b>, <b>Sam McGarey</b>, and <b>Zach McMillen</b>

Our project is a Java Command Line Application which allows teachers and students to automatically generate final grades for classes given a student's grades in each assignment. Our project allows teachers to have multiple rubrics for a class, and takes a student's best grade based on each of the rubrics.

## Iteration 1

### What user stories were completed this iteration?
- A student should be able to keep track of their score on each assignment
- A teacher should be able to copy a past rubric
- A teacher should be able to edit a past rubric

### What user stories do you intend to complete next iteration?
- A teacher can produce grades for different classes
- A student should be able to test what grade they will receive in a class by putting in their scores
- A teacher should be able to get a file with their students' final grades

### Is there anything that you implemented but doesn't currently work?
- User input through the command line is partially implemented but doesn't full work yet

### What commands are needed to compile and run your code from the command line?
- Since our user input through the command line isn't fully functioning yet, our project can't currently run. We are looking to have this working by Iteration 2. The best way to test our code is to run our unit tests.

## Iteration 2

### What user stories were completed this iteration?
- A teacher should be able to get a file with their students' final grades
- A teacher should be able to calculate the max grade for a student
- A student should be able to test what grade they will receive in a class by putting in their scores.
- A teacher should be able to group rubrics together
- The program should provide usage info when run incorrectly

### What user stories do you intend to complete next iteration?
- A teacher can produce grades for different classes
- A student should be able to test what grade they will receive in a class by putting in their scores
- A teacher should be able to create new assignments
- Is there anything that you implemented but doesn't currently work?

### We have an unimplemented unit test for our user interface class because we are uncertain if we need any unit tests for the menu
- The code for the manual mode has been written but currently our menu does not support the manual mode yet, since we have not had time to test it (see above)

### What commands are needed to compile and run your code from the command line?
- simply run ./run.sh
- Running this will run the program, you can use -f testfile.txt scoreTestfile.txt to see an example output
you can also create your own rubric files and student files. A rubric file will have a list of whitespace separated assignment names as the first line, followed by a list of integer weights for those assignments on each line, one line per rubric, summing to 100. A student file will have each line as a (1 word) student name, followed by their grade on each assignment in the same order as the rubric file.
- To run Issue #4 (A student should be able to test what grade they will receive in a class by putting in their scores): once you run the script, first add an assignment by typing '1' (we suggest adding at least 2), then add it to a rubric choice '3' (give the rubric a name), finally, type '11'and give the name of the rubric; this will output the predicted grade of the student.

## Iteration 3

### What user stories were completed this iteration?
- A user can rank the students by their grades
- A teacher can produce grades for different classes
- Program is more robust to bad user input
- Unnecessary unit tests were removed
- Large unit tests were reformatted

### What user stories do you intend to complete next iteration?
- N/A

### Is there anything that you implemented but doesn't currently work?
- Potential edge cases could have been added to make the UI more effective

### What commands are needed to compile and run your code from the command line?
- To run the program, right click on the "DriverTest.java", select "Run As" -> select "Run Configurations" -> set "Arguments"-> set program arguments inside of textbox to "-m"-> Press apply and close. Next, go to the top green run arrow at the top of your eclipse, select the drop down black arrow -> click "Driver"JClass. The program will start in your console.   
- Alternatively, from the command line you can type ./run.sh followed by the command line arguments you want to use for the program to use our script.
- The usage of ./run.sh is as follows: type
    ./run.sh -m
To use the program in manual mode, or type
    ./run.sh -f &lt;rubricFile&gt; &lt;scoreFile&gt;
To run the program in file mode, which produces an output file called Final_Grades.txt.
The formatting of the rubric files is: the first line is a list of one word assignment names, and all the following lines are list of integers, one for each assignment that sum to 100.
The formatting of the score files is: the first line is the same list of assignments used in the rubric file, and all the following lines are a one word student name followed by a list of integers that represent the student scores on each assignment. To see some example rubric and score files you can view the files in the testfiles folder, although be warned most of these files are poorly formatted (as they are used in unit tests that test for behavior under bad formatting).

- To run "8.RankStudents" inside the manual mode: Step 1, create assignment(s). Two, create multiple students. Three, add an assignment(s) to your rubric (the one(s) completed in step 1). Finally, type '8' and type in the rubric that you created in step 3. 
