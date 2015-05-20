import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;



public class ArrayReturnTestCase extends ExerciseTestCase {

	public ArrayReturnTestCase(Object[][] description) {
		super(description);
	}

	@Override
	public String runTest(Object[] parameters, Object[] expectedResult) {
			Object [][] oldDes = this.getDescription();
			Object [][] newDes = new Object [][] {parameters,expectedResult};
			this.setDescription(newDes);
			Object fromCallMethod = null;
			try{
				fromCallMethod = this.callMethod();
			} catch (Exception e){
				StringWriter sw = new StringWriter();
				e.printStackTrace(new PrintWriter(sw));
				String exceptionString = sw.toString();
				return "The test threw an exception while trying to call the method. " + exceptionString;
			}
			this.setDescription(oldDes);
			Object [] objectArray = new Object[]{};
			try{
				objectArray = (Object []) fromCallMethod;
			} catch (Exception e){
				StringWriter sw = new StringWriter();
				e.printStackTrace(new PrintWriter(sw));
				String exceptionString = sw.toString();

				return "The test's return value was not an array! " + exceptionString;
			}
			
				if (objectArray.length!=expectedResult.length){
					return "The length of the passed array and the expected array is not equal!" + "\n" + 
							"Expected an array length of " + expectedResult.length + " but found an array length of " + objectArray.length + " instead.";
				} else {
					boolean badResult = false;
					for (int compareElements = 0; compareElements != expectedResult.length; compareElements++){
						if(objectArray [compareElements].equals(expectedResult [compareElements])){
							// Matched, do nothing
						} else {
							badResult = true;
						}
					}
					if(badResult) {			
						return "Expected " + expectedResult.toString() + ", but found " + objectArray.toString() + ". "; 
					}
					return null;
					
				}
	}
}
