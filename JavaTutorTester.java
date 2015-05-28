import java.lang.reflect.*;
import java.util.ArrayList;

public abstract class JavaTutorTester {
	private ArrayList<TutorExercise> exerciseList = new ArrayList(0);

	public ArrayList<TutorExercise> getExerciseList(){
		return exerciseList;
	}

	public abstract void addAllExercises() ;

	public void AddTutorExercise(String tutor, int unit, int lesson, int exercise, String testType, Object[][][] testData){
		exerciseList.add(new TutorExercise(tutor, unit, lesson, exercise, testType, testData));
	}
	public String[] runExercises(ArrayList<TutorExercise> exerciseList2){
		String [] result = new String [exerciseList2.size()];
		for (int a = 0; a< exerciseList2.size(); a++){
			try{

				String addToR = exerciseList2.get(a).runAllTestCases();				
				result [a] = addToR;
			} catch (Exception e){
				e.printStackTrace();
				return null;
			}
		}

		return result;
	}

	public String reportExerciseResults(ArrayList<TutorExercise> exerciseList2, String [] results){
		String finString = "";
		int firstNonCompleteRes = 0;
		//initially results.length-1
		int firstUntouchedRes = results.length;

		//Checking for first answer that is neither Complete nor Same
		while(firstNonCompleteRes<results.length && (results [firstNonCompleteRes].equals("COMPLETE")||results [firstNonCompleteRes].equals("SAME"))){
			firstNonCompleteRes++;
		}

		if(results[0].equals("COMPLETE")||results[0].equals("SAME")){
			if(firstNonCompleteRes>1){
				finString += ("You have completed exercises " + exerciseToString(exerciseList2.get(0))+ " through "+
						exerciseToString(exerciseList2.get(firstNonCompleteRes-1))+ ".\n");
			} else {
				finString += ("You have completed exercise " + exerciseList2.get(0).getUnitNumber()+"." + 
						exerciseList2.get(0).getLessonNumber()+"."+ exerciseList2.get(0).getExerciseNumber()+ "\n");		
			}
			//Either only one exercise completed at the start or none at all, time to check
		} 
		int firstNonCompleteResForLater=firstNonCompleteRes;
		while(results[firstUntouchedRes-1].equals("UNTOUCHED")||results [firstUntouchedRes-1].equals("SAME")&&firstUntouchedRes!=firstNonCompleteRes){
			firstUntouchedRes--;

		}
		
		// From the last complete to the first noncomplete, we will look for errors
		//maybe should be firstNonCompleteRes-1
		int errorCheck = firstNonCompleteRes;
		while( errorCheck < firstUntouchedRes){
			if(!results[errorCheck].equals("COMPLETE")&&!results[errorCheck].equals("UNTOUCHED"))
				finString +=("Problem with exercise " + exerciseToString(exerciseList2.get(errorCheck)) +": \n" + results[ errorCheck]+"\n");
			errorCheck++;
		}
		
		//Adding untouched statement
		//possibly a less than instead of not equal to
		if(firstUntouchedRes<results.length-1){
			finString += ("Exercises " + exerciseToString(exerciseList2.get(firstUntouchedRes)) + " through " + 
					exerciseToString(exerciseList2.get(results.length-1)) + " have not been touched. \n");

			// check to see if only one untouched
			//maybe should check if the arguments of the if are equal 
		} else if (firstUntouchedRes==results.length-1){
			finString+=("Exercise " + exerciseToString(exerciseList2.get(firstUntouchedRes)) + " has not been touched. \n");
		}


		return finString;		
	}
	public void runAllTests(){	
		System.out.println(reportExerciseResults(exerciseList,runExercises(exerciseList)));
	}

	public String exerciseToString (TutorExercise exercise){
		return "" + exercise.getUnitNumber() +"." + exercise.getLessonNumber() +"." + exercise.getExerciseNumber();
	}

	JavaTutorTester(){
		addAllExercises();
		//Sort the exercises here. If they're not sorted, the code won't work as it should.
	}
}
