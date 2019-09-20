package linear;


public class Node {                         //A class for creating a node
	
		int  data;
		Node next;
		
		//Constructor
		Node(int data)
		{
			this.data = data;
			this.next = null;               //it fine if remove this line, cause default the value for the object is null
		}

}
