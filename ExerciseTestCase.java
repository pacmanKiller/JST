import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.*;
import java.util.ArrayList;

public abstract class ExerciseTestCase {
	private TutorExercise exercise;
	private Object[][] description;
	public TutorExercise getExercise(){
		return exercise;
	}
	public void setExercise(TutorExercise inputExercise){
		exercise = inputExercise;
	}
	public Object[][] getDescription(){
		return (Object[][]) description;
	}
	public void setDescription(Object[][] inputDescription){
		description = inputDescription;
	}
	ExerciseTestCase(Object[][] description){
		this.description = description;
	}
	public String runTestCase(){
		// The first element of the array is an array describing the conditions under which the test should be run.
		// The second element of the array is an array describing the expected results if it is correct.
		// The third element is an array describing the expected results if it has not been touched.
		// If the array only has two elements, or if the third array is null, that means the
		// initial and final results are the same. Fourth and further elements are ignored (but may be used by the subclasses).
		String result = "";
		boolean matchExpected = false;
		boolean changed = true;
		// Either there is a third element or there isn't
		// If there is and it is not null, then the test expects the end result to be different from the start
		// else, there is not a third element or it is null and the test expects the code to remain unchanged
		if(this.getDescription().length>=3&& this.getDescription()[2]!=null){
			if(this.runTest(this.description[0], this.description[1]) != null) {
				//correct abswer run not null
			} else {
				matchExpected = true;
			}
			if(this.runTest(this.description[0], this.description[2]) != null) {
				changed = true;
			} else {
				changed=false;
			}
		} else {
			if(this.runTest(this.description[0], this.description[1]) != null) {
				matchExpected = false;
				changed = true;
			} else {
				matchExpected = true;
				changed = false;
			}
		}
		if(matchExpected && changed ){
			return "COMPLETE";
		} else if(matchExpected && (!changed)){
			return "SAME";
		} else if((!matchExpected) && (!changed)){
			return "UNTOUCHED";
		}else {
			return this.parameterDescription(this.description[0]) + "," + runTest(this.description[0], this.description[1]);
		}
	}
	public Object callMethod() throws Exception{

		// While it may seem more efficient to just use test.getClass().getMethod(arg0, arg1), I found that to create an array of classes
		// will turn int into Integer which means that the code could not find classes with integers, and, presumably, all classes with
		// primitive types.

		Object test = Class.forName(exercise.getTutorName()).newInstance();

		int matchMethodIndex=0;
		boolean foundMatch = false;			
		for(int searchForMatchMethod = 0; searchForMatchMethod <test.getClass().getMethods().length; searchForMatchMethod ++){
			String name = test.getClass().getMethods() [searchForMatchMethod].getName();
			if(	name.contains("_"+exercise.getUnitNumber()+"_"+exercise.getLessonNumber()+"_"+exercise.getExerciseNumber())){
				matchMethodIndex=searchForMatchMethod;
				foundMatch = true;
			}
		}		
		try {		
			if (foundMatch){
				//invokes the matched method with the array of parameters	
				return test.getClass().getMethods()[matchMethodIndex].invoke(test, ((description[0])));
			}	
		} catch (Exception e) {
			//			StringWriter sw = new StringWriter();
			//			e.printStackTrace(new PrintWriter(sw));
			//			String exceptionString = sw.toString();

			Throwable cause = e.getCause();
			String exceptionString = cause.getClass().getSimpleName();
			String diagnosticString = "";

			if (exceptionString.equals("ArrayIndexOutOfBoundsException") || exceptionString.equals("IndexOutOfBoundsException")){
				diagnosticString = "Check the size of the array and which element that is called";
			} 
			//if (exceptionString.equals(""))

			return "an error! The method found was: " + 
			exercise.getTutorName()+exercise.getUnitNumber()+"_"+exercise.getLessonNumber()+"_"+exercise.getExerciseNumber() + ". " + "Invoking the method found caused an exception." + "\n"
			+ "The exception thrown was of type: " + exceptionString + ". " + diagnosticString;
		}
		return "Did not find matching method! The method searched for was: " + 
		exercise.getTutorName()+exercise.getUnitNumber()+"_"+exercise.getLessonNumber()+"_"+exercise.getExerciseNumber();
	}
	public String parameterDescription(Object[] parameters){
		String parameterElements = "";
		for (int paramExamine = 0; paramExamine < parameters.length; paramExamine ++ ){
			if(paramExamine ==0){
				parameterElements = "" + parameters [paramExamine];
			} else
				parameterElements += "," + parameters [paramExamine];
		}
		return "Called with parameters " + parameterElements + " ";
	}
	public abstract String runTest(Object[] parameters, Object[] expectedResult);
}
