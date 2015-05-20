import java.util.ArrayList;



public class Recorder {	
	static ArrayList sentToRecord = new ArrayList();
	static Object toReturn = null;
	static Object [] toReturnList = null;
	static int numTimesCalled = 0;
	public static Object record(Object ob){
		sentToRecord.add(ob);
		if(toReturnList!=null&&numTimesCalled<toReturnList.length){
			numTimesCalled++;
			return toReturnList[numTimesCalled-1];
		}
		if(toReturn!=null)
			return toReturn;
		return null;
	}
	public static void reset(){		
		sentToRecord = new ArrayList();
		toReturnList = null;
		numTimesCalled = 0;
		toReturn = null;
	}
	public static void reset(Object ob){
		sentToRecord = new ArrayList();
		toReturnList = null;
		numTimesCalled = 0;
		toReturn = ob;
	}
	public static void reset(Object[] list){
		sentToRecord = new ArrayList();
		toReturnList = list;
		numTimesCalled = 0;
		toReturn = null;
	}
	public static Object[] getCalls(){
		return sentToRecord.toArray();
	}
}
