import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;



public class ArrayReturnTestCase extends ExerciseTestCase {

	public ArrayReturnTestCase(Object[][] description) {
		super(description);
	}
	private String helpStringGenerator(String str){
		String exceptionString = str;
		String helpString = "";
		
		if (exceptionString.equals("ArrayIndexOutOfBoundsException") || exceptionString.equals("IndexOutOfBoundsException")){
			helpString = "Check the size of the array and which element that is called.";
		}
		if (exceptionString.equals("NullPointerException")){
			helpString = "Check if an array was created";
		}
		if (exceptionString.equals("IllegalArgumentException")){
			helpString = "Check the parameters of the method being called";
		}
		return helpString;
	}
	@Override
	public String runTest(Object[] parameters, Object[] expectedResult) {
			Object [][] oldDes = this.getDescription();
			Object [][] newDes = new Object [][] {parameters,expectedResult};
			Object [] objectArray = new Object[]{};
			this.setDescription(newDes);
			//Object fromCallMethod = null;
			try{
				objectArray = (Object []) this.callMethod();

			} catch (Exception e){
				StringWriter sw = new StringWriter();
				e.printStackTrace(new PrintWriter(sw));
				String exceptionString = sw.toString();
				return "but invoking the method found caused an exception. The exception found was of type: " +
				e.getClass().getSimpleName() + ". "
				+ helpStringGenerator(e.getClass().getSimpleName());
			}
			this.setDescription(oldDes);
			
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
