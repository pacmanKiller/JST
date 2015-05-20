import java.util.ArrayList;


public class notes {
	public static void main (String [ ] args ) throws Exception{
		
		// Here's how the Arrays work, since I had a lot of trouble with them:
		// There are three arrays, usually.
		// The first array contains another array with the parameters to use to run the test
		// The second array contains another array with the a list of expected results
		// The third array contains another array with the results if the code was not touched
		
		notesTestJavaTutorTesterAdd testAdd = new notesTestJavaTutorTesterAdd();
		testAdd.AddTutorExercise("JST", 1, 2, 1, "MethodCallReturnTestCase", new Object [][][] {{{1},{1},{1},{1}},{{9},{9},{9},{9}}, {{5},{5},{5},{5}}});
		testAdd.AddTutorExercise("JST", 1, 2, 2, "MethodCallReturnTestCase", new Object [][][] {{{1}},{{1545}}, {{2}}});
		testAdd.AddTutorExercise("JST", 1, 2, 3, "MethodCallReturnTestCase", new Object [][][] {{{1,3},{1,3},{1,3},{1,2}},{{4},{4},{4},{3}}, {{2},{2},{2},{2}}});
		testAdd.AddTutorExercise("JST", 3, 2, 1, "MethodCallingTestCase", new Object [][][] {{{12}},{{1,2,3}}, {{2,3}}});

		System.out.print("notes' answer: \n" + testAdd.reportExerciseResults(testAdd.getExerciseList(), testAdd.runExercises(testAdd.getExerciseList())));
		
		// Some of the JST methods have been modified.
		// Good luck, have fun! - Karist
		
	}
}
