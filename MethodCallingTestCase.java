

public class MethodCallingTestCase extends ExerciseTestCase {

	public MethodCallingTestCase(Object[][] description) {
		super(description);
	}

	public String runTest(Object[] parameters, Object[] expectedResult) {
		String mismatch = "";
		String badResultString = null;
		String expectedResultString = null;

		try{
			
			callMethod();		
		} catch (Exception e) {
			//Fix this here, it should probably
			System.out.println(e.getStackTrace());
		}
			if(Recorder.getCalls().length==expectedResult.length){
				for (int a =0; a < Recorder.getCalls().length; a ++){
					if(!(Recorder.getCalls()[a].equals(expectedResult[a]))){
						if(badResultString==null){
							//First mismatch, time to add brackets for the error list
							badResultString = "{" + Recorder.getCalls()[a];
							expectedResultString = "{" + expectedResult[a];
						} else {
							badResultString += ", " + Recorder.getCalls()[a];
							expectedResultString += ", " + expectedResult[a];
						}
					}
				}
				
				if(badResultString == null)
					return null;
				
				badResultString += "}";
				expectedResultString += "}";
				mismatch += expectedResultString + " but found " + badResultString +".";

				return mismatch;
			} else {
				// The length didn't match: Ms.Gerb would like this return to not mention array length, since that's something the
				// user wouldn't know about. Maybe just remove the part of the string referring to the sizes of the array
				return "Record was not called the same number of times as expected!\n"
						+ "Expected Recorder to be called " + expectedResult.length + " times but instead it was called " + Recorder.getCalls().length
						+ " times.";
			}
		
		return null;
	}

	public String parameterDescription(Object[] parameters){
		String called = "";
		for (int getCalls = 0; getCalls < Recorder.getCalls().length; getCalls ++ ){
			if(getCalls ==0){
				called = "" + parameters [getCalls];
			} else 
				called += "," + parameters [getCalls];
		}
		return "The record list was called with the following: " + called + " ";
	}
}
