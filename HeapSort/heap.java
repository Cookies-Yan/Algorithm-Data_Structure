package Heap;

public class heap {
	

	public static void main(String[] args) {
		
		int[] array = new int[21];  
		CreateArray(array);
		System.out.print("My array is :               ");
		ShowArray(array);
		
		for(int i = (array.length-1)/2; i > 0; i--) {
			MaxHeap(array, i, array.length-1);
		}
		System.out.println();
		System.out.print("After transfor to MaxHeap : ");
		ShowArray(array);
		System.out.println();
		System.out.print("After Heapsort :            ");
		HeapSort(array);
		ShowArray(array);
		
		// TODO Auto-generated method stub

	}
	
	public static void MaxHeap(int[] array,int i, int n) {
		
		int LeftChild = 2 * i;
		int RightChild = 2 * i +1;
		int Max;
		
		if(LeftChild <= n && array[LeftChild] > array[i]) {
			 Max = LeftChild;
		}
		else { Max = i;}
		if(RightChild <= n && array[RightChild] > array[Max]) {
			Max = RightChild;
		}
		if(Max != i) {
			swap(array, Max,i);
			MaxHeap(array, Max, n);
		}

	}
	public static void HeapSort(int[] array) {
		
		int HeapSize = array.length-1;
		for(int i =  array.length-1; i >= 2; i--) {	
			
			swap(array, 1, i);
 			HeapSize -= 1;
			MaxHeap(array, 1, HeapSize);
		}
	}
	
	public static void swap(int[] array, int i, int j) {
		int tmp;
		tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
			
	}
	
	public static int random() {
		
		int random = (int)(1+Math.random()*100);
		return random;
	}
	
	public static void CreateArray(int Array[]) {
		

		Array[0] = Array.length - 1;
		
		for (int i = 1; i < Array.length; i++) {
			Array[i] = random();
		}
	}
	
	public static void ShowArray(int Array[]) {
		
		for(int i=0; i < Array.length; i++) {
			System.out.print(Array[i] + "  ");
		}
	}
	
}


