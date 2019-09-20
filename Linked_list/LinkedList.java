package linear;
 
public class LinkedList 
{
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList list = new LinkedList (); 
		for(int i = 0; i < 25; i++) 
		{
			list.insert(random());			 //Creating a random integer and add it to the back of LinkedList
		}
		System.out.print ("LinkedList:    ");
		printList(list);                     //Show the LinkedList
		sort();							 //Using selection sort without swapping the data
		System.out.print("After Sorting: ");
		printList(list);					 //Show data again
		
	}
	
	public static int random()				//Creating a random number
	{
		int random = (int)(1+Math.random()*100);
		return random;
	}
    
	static Node head;                       //head of list
	public void insert (int data)           //Method to insert a new node
	{
		                                    //Create a new node with given data
		Node node = new Node(data);
		
		if (head == null)                   //If the Linked List is empty
		{
			head = node;
		}
		else
		{
			Node last = head;
			while (last.next != null)       //Make a traverse till the last node
			{
				last = last.next;
			}
			last.next = node;
		}

	}
	
	public static void sort()	   //Sorting function
	{
		 Node tmp = head;
	        if (head == null)
	            return;
	        while (tmp.next != null) {
	            Node curr = tmp;
	            tmp = tmp.next;
	            Node minNode = curr;
	            Node tmp1 = curr.next;
	            while (tmp1 != null) {
	                if (tmp1.data < minNode.data)
	                    minNode = tmp1;
	                tmp1 = tmp1.next;
	            }
	            if (minNode != curr) {
	                swap(curr, minNode);
	            }
	        }				
	}
	public static void swap(Node current, Node minVal) // swap nodes, we have to know node.previous & node.next & node 
	{  
        Node prev_b = current;
        while (prev_b.next.data != minVal.data)
            prev_b = prev_b.next;
   
        if (current == head) 
        {
            prev_b.next = current;
            Node node_after_b = minVal.next;
            minVal.next = current.next;
            current.next = node_after_b;
            head = minVal;
        }
    
        else 
        {
            Node prev_a  = head;
            while (prev_a.next.data != current.data)
                prev_a = prev_a.next;
            prev_b.next = current;
            Node Next_b = minVal.next;
            minVal.next = current.next;
            current.next = Next_b;
            prev_a.next = minVal;
        }
    }
	
	public static void printList(LinkedList list)  //Make a function to display LinkedList
	{
		Node node = list.head;					   //Traverse from the head
		
		while (node != null)
		{
			System.out.print(node.data + " ");
			node = node.next;
		}
		System.out.println();
	}
	
}
	

