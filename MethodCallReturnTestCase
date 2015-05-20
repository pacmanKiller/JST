


public class MethodCallReturnTestCase extends ExerciseTestCase {

	public MethodCallReturnTestCase(Object[][] description) {
		super(description);
	}

	public String runTest(Object[] parameters, Object[] expectedResult) {
		Object result = null;
		try {			
			Object [][] oldDes = this.getDescription();
			Object [][] newDes = new Object [][] {parameters,expectedResult};
			this.setDescription(newDes);
			result = this.callMethod();
			this.setDescription(oldDes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (expectedResult[0].equals(result))
			return null;
		return (" expected " + (expectedResult[0]) + " but found " + (result) +".");
	}
}
