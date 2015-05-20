

public class MethodCallRangeTestCase extends ExerciseTestCase {

	public MethodCallRangeTestCase(Object[][] description) {
		super(description);
	}

	public String runTest(Object[] parameters, Object[] expectedResult) {

		int low=0;
		int high=0;
		for(int repeater=0; repeater< 5000; repeater++){
			int result = 0;
			try {
				Object [][] oldDes = this.getDescription();
				Object [][] newDes = new Object [][] {parameters,expectedResult};
				this.setDescription(newDes);
				result = (int) this.callMethod();
				this.setDescription(oldDes);
			} catch (Exception e) {
				e.printStackTrace();
			}
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
		if(low!= (int) expectedResult [0]){
			return " expected lowest number to be " + expectedResult [0] + " but found values as low as " + low + ".";
		}
		if(high!= (int) expectedResult [1]){
			return " expected highest number to be " + expectedResult [1] + " but found values as high as " + high + ".";
		}
		return null;
	}
}

