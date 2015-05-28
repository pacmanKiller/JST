import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;



public class TutorExercise implements Comparable <TutorExercise> {
	private String tutorName;
	private int unitNumber;
	private int lessonNumber;
	private int exerciseNumber;
	private String testDataType;
	private Object[][][] description;

	public String getTutorName(){
		return tutorName;
	}
	public int getUnitNumber(){
		return unitNumber;
	}
	public int getLessonNumber(){
		return lessonNumber;
	}
	public int getExerciseNumber(){
		return exerciseNumber;
	}
	public String getTestDataType(){
		return testDataType;
	}
	public Object[][][] getDescription(){
		return description;
	}
	public ExerciseTestCase[] allTestCases() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		// The first description 0 should contain the number of tests
		// The second description, 1, should contain the number of correct answers to the test
		// If there is an uneven number of tests to correct answers , then the test just shouldn't be run, since it is pointless.
		// The test wouldn't have an answer or the answer wouldn't have a test
		int lowLength = 0;
		if(description[0].length<description[1].length){
			lowLength = description[0].length;
		} else {
			lowLength = description[1].length;
		}
		
		ExerciseTestCase[] allTest = new ExerciseTestCase[lowLength]; 
		
		for (int addExerciseCount = 0; addExerciseCount  < lowLength; addExerciseCount  ++){
			Object [][] sendArray;
			if(this.getDescription().length>2){
			    sendArray = new Object [][] {(Object[]) ((this.description[0])[addExerciseCount ]),
			    (Object[]) ((this.description[1])[addExerciseCount ]),((this.description[2])[addExerciseCount ])} ;
			} else {
				sendArray = new Object [][] {(Object[]) ((this.description[0])[addExerciseCount ]),(Object[]) ((this.description[1])[addExerciseCount ])};
			}			
		        Class testClass = Class.forName(testDataType);

		        Constructor newCon = testClass.getConstructor(Object[][].class);
		        ExerciseTestCase test = (ExerciseTestCase) newCon.newInstance(sendArray); 
		        test.setExercise(this);
		        allTest[addExerciseCount] = test;
		}
		return allTest;
	}

	public TutorExercise (String tutorN, int unitNum, int lessonNum, int exerciseNum, String testDataT, Object [][][] descrip){
		tutorName = tutorN;
		unitNumber = unitNum;
		lessonNumber = lessonNum;
		exerciseNumber = exerciseNum;
		testDataType = testDataT;
		description = descrip;
	}
	
	public int compareTo(TutorExercise other) {

		int tutNameComp = this.tutorName.compareTo(other.tutorName);
		int uniComp = Integer.toString(unitNumber).compareTo(Integer.toString(other.unitNumber));
		int lessonComp = Integer.toString(lessonNumber).compareTo(Integer.toString(other.lessonNumber));
		int exerComp = Integer.toString(exerciseNumber).compareTo(Integer.toString(other.exerciseNumber));
		
		if(tutNameComp!=0)
			return tutNameComp;
		if(uniComp!=0)
			return uniComp;
		if(lessonComp!=0)
			return lessonComp;
		if(exerComp!=0)
			return exerComp;
		return 0;
		
	}

	public String runAllTestCases() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
		String result = "";
		ArrayList <String> resultCheck = new ArrayList();
		
//		This method should run all the test cases for the exercise. 
//		It returns “COMPLETE” if all are correct. It returns “UNTOUCHED” if the results match the initial condition of the tutor. 
//		Otherwise it returns a string describing the test case that was incorrect. 
//		Return COMPLETE only if all test cases are COMPLETE or SAME. Return UNTOUCHED only if all test cases are UNTOUCHED or SAME. 
//		Otherwise return the description of the first test that returns an incorrect result.	
		int numOfTestCase = allTestCases().length;
		ExerciseTestCase [] allTestToRun = allTestCases();
		
		for (int run = 0; run < numOfTestCase; run ++){
		        ExerciseTestCase currentTest = allTestToRun[run];
		        String testResult=currentTest.runTestCase();
		        resultCheck.add(testResult);
		}
				
		//Now we can have result-s like completeuntouchedsame so we have to deal with them
		boolean anyComplete = false;
		boolean anySame = false;
		boolean anyUntouched = false;
		boolean anyError = false;
		for(int a = 0; a < resultCheck.size()&&(!anyError);a++){
			if(resultCheck.get(a).equals("COMPLETE")){
				anyComplete = true;
			} else 	if(resultCheck.get(a).equals("SAME")){
				anySame = true;
			} else 	if(resultCheck.get(a).equals("UNTOUCHED")){
				anyUntouched = true;
			} else {				
				anyError = true;
				result = resultCheck.get(a);
			}
		}
		//No errors check
		if(!anyError){
			if(anyUntouched){
				result = "UNTOUCHED";
			} else {
				result = "COMPLETE";
			}
		}
		return result;
	}
}
