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
				return null;
			}
		}
		
		return result;
	}

	public String reportExerciseResults(ArrayList<TutorExercise> exerciseList2, String [] results){
		String finString = "";
		int firstNonCompleteRes = 0;
		int firstUntouchedRes = results.length-1;
		
		//Checking for first answer that is not Complete nor Same
		while(firstNonCompleteRes<results.length-1&&(results [firstNonCompleteRes].equals("COMPLETE")||results [firstNonCompleteRes].equals("SAME"))){
			firstNonCompleteRes++;
		}
		
		if(results[0].equals("COMPLETE")||results[0].equals("SAME")){
			if(firstNonCompleteRes>1){
				finString += ("You have completed exercises " + exerciseToString(exerciseList2.get(0))+ 
				exerciseToString(exerciseList2.get(firstNonCompleteRes-1))+ ".\n");
			} else {
				finString += ("You have completed exercise " + exerciseList2.get(0).getUnitNumber()+"." + 
					exerciseList2.get(0).getLessonNumber()+"."+ exerciseList2.get(0).getExerciseNumber()+ "\n");		
			}
			//Either only one exercise completed at the start or none at all, time to check
		} 
		while(results [firstNonCompleteRes].equals("UNTOUCHED")&&firstNonCompleteRes!=0){
			firstUntouchedRes--;				
		}
		
		//Adding untouched statement
		if(firstUntouchedRes!=results.length-1){
			finString += ("Exercises " + exerciseToString(exerciseList2.get(firstUntouchedRes)) + " through " + 
					exerciseToString(exerciseList2.get(results.length-1)) + " have not been touched. \n");
			
			// check to see if only one untouched
		} else if (results[firstUntouchedRes].equals("UNTOUCHED")){
			finString+=("Exercise " + exerciseToString(exerciseList2.get(firstUntouchedRes)) + " has not been touched. \n");
		}
		
		// From the last complete to the first noncomplete, we will look for errors
		int errorCheck = firstNonCompleteRes;
		while( errorCheck < firstUntouchedRes){
			if(!results[errorCheck].equals("COMPLETE")&&!results[errorCheck].equals("UNTOUCHED"))
				finString +=("Problem with exercise " + exerciseToString(exerciseList2.get(errorCheck)) +": \n" + results[ errorCheck]+"\n");
			errorCheck++;
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
