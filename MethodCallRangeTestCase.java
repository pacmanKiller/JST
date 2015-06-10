

public class MethodCallRangeTestCase extends ExerciseTestCase {

	public MethodCallRangeTestCase(Object[][] description) {
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
	public String runTest(Object[] parameters, Object[] expectedResult) {

		int low=0;
		int high=0;
		try {
			for(int repeater=0; repeater< 5000; repeater++){
				int result = 0;

				Object [][] oldDes = this.getDescription();
				Object [][] newDes = new Object [][] {parameters,expectedResult};
				this.setDescription(newDes);
				result = (int) this.callMethod();
				this.setDescription(oldDes);

				if(repeater==0){
					//First time through loop, takes first number to give to low and high for set up
					high = result;
					low = result;
				}
				if (result > high){
					high = result;
				} 
				if (result < low){
					low = result;
				}
			}
		} catch (Exception e) {
			return "but invoking the method found caused an exception. The exception found was of type: " +
			e.getClass().getSimpleName() + ". "
			+ helpStringGenerator(e.getClass().getSimpleName());
		}
		if(low!= (int) expectedResult [0]){
			return " expected lowest number to be " + expectedResult [0] + " but found values as low as " + low + ".";
		}
		if(high!= (int) expectedResult [1]){
			return " expected highest number to be " + expectedResult [1] + " but found values as high as " + high + ".";
		}
		return null;
	}
}
