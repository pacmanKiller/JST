
public class notesTestJavaTutorTesterAdd extends JavaTutorTester {

	public void addAllExercises() {
		this.AddTutorExercise("JST", 1, 1, 1, "MethodCallReturnTestCase", new Object [][][] {{{1}}, {{5}}, {{3}}});
		this.AddTutorExercise("JST", 1, 1, 1, "MethodCallReturnTestCase", new Object [][][] {{{2}}, {{6}}, {{4}}});
		this.AddTutorExercise("JST", 1, 1, 1, "MethodCallReturnTestCase", new Object [][][] {{{5}}, {{9}}, {{7}}});
		this.AddTutorExercise("JST", 1, 1, 2, "MethodCallReturnTestCase", new Object [][][] {{{1}}, {{5}}, {{3}}});
		this.AddTutorExercise("JST", 1, 1, 2, "MethodCallReturnTestCase", new Object [][][] {{{2}}, {{10}}, {{6}}});
		this.AddTutorExercise("JST", 1, 1, 2, "MethodCallReturnTestCase", new Object [][][] {{{5}}, {{25}}, {{15}}});
		this.AddTutorExercise("JST", 1, 1, 3, "MethodCallReturnTestCase", new Object [][][] {{{6}}, {{3}}, {{1}}});
		this.AddTutorExercise("JST", 1, 1, 3, "MethodCallReturnTestCase", new Object [][][] {{{12}}, {{6}}, {{2}}});
		this.AddTutorExercise("JST", 1, 1, 3, "MethodCallReturnTestCase", new Object [][][] {{{24}}, {{12}}, {{4}}});
		this.AddTutorExercise("JST", 1, 1, 4, "MethodCallReturnTestCase", new Object [][][] {{{6}}, {{-1}}, {{6}}});
		this.AddTutorExercise("JST", 1, 1, 4, "MethodCallReturnTestCase", new Object [][][] {{{12}}, {{5}}, {{12}}});
		this.AddTutorExercise("JST", 1, 1, 4, "MethodCallReturnTestCase", new Object [][][] {{{24}}, {{17}}, {{24}}});
		this.AddTutorExercise("JST", 1, 1, 5, "MethodCallReturnTestCase", new Object [][][] {{{8}}, {{2}}, {{0}}});
		this.AddTutorExercise("JST", 1, 1, 5, "MethodCallReturnTestCase", new Object [][][] {{{10}}, {{1}}, {{0}}});
		this.AddTutorExercise("JST", 1, 1, 5, "MethodCallReturnTestCase", new Object [][][] {{{23}}, {{2}}, {{1}}});
		
	}
	public static void main(String[] args) throws Exception{
		JavaTutorTester testAdd = new notesTestJavaTutorTesterAdd();
		System.out.println(testAdd.getExerciseList());
		testAdd.runAllTests();
//		System.out.print("notes' answer: \n" + testAdd.reportExerciseResults(testAdd.getExerciseList(), testAdd.runExercises(testAdd.getExerciseList())));
	}

}
