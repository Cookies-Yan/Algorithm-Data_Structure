package Hash;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class HashFunction {
	
	static int arraySize;
	static int coll;
	int entries;
	double loadFactor = 0.5;
	static String[] value;
	String[] theArray;
	static String[] CoolTank;
	
	public static void main(String[] args) {
		HashFunction.arrayCreate();
		HashFunction theFunc = new HashFunction(31);
		theFunc.hashFunction(value,theFunc.theArray);
		System.out.println("The size of hashtble after rehash is: " + theFunc.theArray.length);
		System.out.println("Collision times are : " + coll);
		
    }
	
	public void hashFunction(String[] stringsForArray, String[] theArray) {
		int q = 0;
		for (int n = 0; n < stringsForArray.length; n++) {
			if(stringsForArray[n] != "-1") {
				String newElementVal = stringsForArray[n];
				int value = Integer.parseInt(newElementVal);
				int index = getIndex(value,q);
				while(theArray[index] != "-1") {
					coll++;
					q++;
					index = getIndex(value, q);
				}
				theArray[index] = newElementVal;
				entries++;			
				if(entries >= arraySize * loadFactor) {
 					entries = 0;
					increaseArraySize(theArray);
				}
				
			}
		}
		
	}
	
	public HashFunction(int size) {
		arraySize = size;
		theArray = new String[size];
		fillArrayWithNeg1();
	}
	public void fillArrayWithNeg1() {

		Arrays.fill(theArray, "-1");

	}
	
	
	public static class RandomString {
		
		static String getAlphaNumericString(int n) {
			 String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvxyz" + " ";
			 StringBuilder sb = new StringBuilder(n);
			 for (int i = 0; i < n; i++) { 
		            int index= (int)(AlphaNumericString.length()* Math.random()); 
		            sb.append(AlphaNumericString.charAt(index)); 
		        } 
		  
		        return sb.toString();
		}

	}
	public static void arrayCreate() {
		Random ran = new Random();
		RandomString ss = new RandomString();
		CoolTank = new String[20];
		value = new String[20];
		
		for(int i = 0; i < 20; i++) {
			int x = ran.nextInt(7) + 3;
			CoolTank[i] = RandomString.getAlphaNumericString(x);
		}
		for(int i = 0; i < CoolTank.length; i++) {
			String a = CoolTank[i];
			String res = "";
			int store = 0;
			for(int j = 0; j < a.length() ; j++){        
		        char character = a.charAt(j); 
		        int ascii = (int) character; 
		        store += ascii;
			}
			res = Integer.toString(store);
			value[i] = res;
			
		}
	}
	public static int getIndex(int value, int a) {
		int index = (value + (a*a)) % arraySize;
		return index;
	}
	public boolean isPrime(int number) {
		if (number % 2 == 0)
			return false;
		
		for (int i = 3; i * i <= number; i += 2) {
			if (number % i == 0)
				return false;
		}
		return true;

	}
	public int getNextPrime(int minNumberToCheck) {

		for (int i = minNumberToCheck; true; i++) {
			if (isPrime(i))
				return i;

		}

	}
	public void increaseArraySize(String[] array) {
		int minArraySize = arraySize * 2;
		int newArraySize = getNextPrime(minArraySize);
		theArray = new String[newArraySize];
//		moveOldArray(newArraySize);

	}

	public void moveOldArray(int newArraySize) {

//		String[] cleanArray = removeEmptySpacesInArray(theArray);
		theArray = new String[newArraySize];
		arraySize = newArraySize;

		fillArrayWithNeg1();
//		theArray = new String[newArraySize];
//		Arrays.fill(theArray, "-1");

//		hashFunction(value,theArray);

	}

	public String[] removeEmptySpacesInArray(String[] arrayToClean) {

		ArrayList<String> stringList = new ArrayList<String>();
		for (String theString : arrayToClean)
			if (!theString.equals("-1") && !theString.equals(""))
				stringList.add(theString);
		return stringList.toArray(new String[stringList.size()]);

	}

}
	

