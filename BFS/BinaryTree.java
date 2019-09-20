package BinaryTree;

import java.util.Scanner;

public class BinaryTree {
	
	
	public static void main(String[] args) {
		
		BinaryTree theTree = new BinaryTree();
		BinaryTree.addNode(9);
		BinaryTree.addNode(15);
		BinaryTree.addNode(10);
		BinaryTree.addNode(2);
		BinaryTree.addNode(4);
		BinaryTree.addNode(18);
		BinaryTree.addNode(7);
		BinaryTree.addNode(13);
		BinaryTree.addNode(42);
		BinaryTree.addNode(35);
		BinaryTree.addNode(27);
		BinaryTree.addNode(16);
		BinaryTree.addNode(67);
		BinaryTree.addNode(39);
		BinaryTree.addNode(55);
		BinaryTree.addNode(20);
		
		System.out.print("Showing the nodes of tree are :  ");
		inOrderTraverseTree(theTree.root);
		System.out.println();
		System.out.print("Showing the Leaves of tree are : ");
		FindLeaf(theTree.root);
		System.out.println();
		System.out.print("Nodes which have subtree are :   ");
		subtreeNode(theTree.root);
		System.out.println();
		System.out.print("Please enter the leaf you want to delete: ");
		Scanner key = new Scanner(System.in);
		int leaf = key.nextInt();
		delete(leaf);
		System.out.print("**After deleting a leaf :        ");
		inOrderTraverseTree(theTree.root);
		System.out.println();
		System.out.print("Please enter the node you want to delete: ");
		Scanner key1 = new Scanner(System.in);
		int node = key1.nextInt();
		delete(node);
		System.out.print("**After deleting an internal node:");
		inOrderTraverseTree(theTree.root);
		
		
	}
	
	static Node root;
	
	public static void addNode(int key) {
		
		Node newNode = new Node(key);
		
			if (root == null) {
				
				 root = newNode;
				 
			}
			else {
				
				Node currentNode = root;
				Node parent;
				
				while(true) {
					
					parent  = currentNode;
					
					if(key < currentNode.key) {
						
						currentNode = currentNode.leftChild;
						
						if (currentNode == null) {
							
							parent.leftChild = newNode;
							return;
							
						}
						
					} else {
						
						currentNode = currentNode.rightChild;
						
						if (currentNode == null) {
							
							parent.rightChild = newNode;
							return;
							
						}
					}
				}
			}
	}
	public static void inOrderTraverseTree(Node currentNode) {
		
		if(currentNode != null) {
			
			inOrderTraverseTree(currentNode.leftChild);
			System.out.print(currentNode.key + " ");
			inOrderTraverseTree(currentNode.rightChild);
			
		}
	}
	
	public static boolean delete(int key) {
		
		Node currentNode = root;
		Node parent = root;
		
		boolean isItAleftChild  = true;
		
		while(currentNode.key != key) {  //Find the node, certain it sets at the left or right of its parent
			
			parent = currentNode;
			
			if (key < currentNode.key) {
				
				isItAleftChild = true;
				currentNode = currentNode.leftChild;
				
			} else {
				
				isItAleftChild = false;
				currentNode = currentNode.rightChild;
				
			}  
			if  (currentNode == null) 	
				return false;	
		}
		if(currentNode.leftChild == null && currentNode.rightChild == null) {
			
			if(currentNode == root) {
				
				root = null;
				
			} else if (isItAleftChild) {
				
				parent.leftChild = null;
				
			} else {
				
				parent.rightChild = null;
				
			}
		}
		else if (currentNode.rightChild == null) {
			
			if(currentNode == root)
				root = currentNode.leftChild;
			
			else if(isItAleftChild)
				parent.leftChild = currentNode.leftChild;
			
			else parent.rightChild = currentNode.leftChild;
		}
		
		else if (currentNode.leftChild == null) {
			
			if(currentNode == root)
				root = root.rightChild;
			
			else if(isItAleftChild) 
				parent.leftChild = currentNode.rightChild;
			
			else parent.rightChild = currentNode.rightChild;
		}
		else {
			
			Node replacement = getReplacementNode(currentNode);
			
			if (currentNode == root)
				root = replacement;
			else if(isItAleftChild)
				parent.leftChild = replacement;
			else 
				parent.rightChild = replacement;
			
			replacement.leftChild = currentNode.leftChild;
			
		}
		
		return true;
		
	}
	
	public static Node getReplacementNode(Node replacedNode) { //Find the minimum in right
		
		Node replacementParent = replacedNode;
		Node replacement = replacedNode;
		
		Node currentNode = replacedNode.rightChild;
		
		while(currentNode != null) {
			
			replacementParent = replacement;
			
			replacement = currentNode;
			
			currentNode = currentNode.leftChild;
			
		}
		if(replacement != replacedNode.rightChild) {
			
			replacementParent.leftChild = replacement.rightChild;
			replacement.rightChild = replacedNode.rightChild;
			
		}
		
		return replacement;
	}
	
	public static void FindLeaf(Node currentNode) {
		
		if(currentNode == null) {return;}
		
		else if(currentNode.leftChild != null || currentNode.rightChild != null) {
			
			FindLeaf(currentNode.leftChild);
			FindLeaf(currentNode.rightChild);
			
		} else {
			
			System.out.print(currentNode.key + " ");
			
		}
		
	}
	public static void subtreeNode(Node currentNode) {
			
		if(currentNode == null) {return;}
		else if (currentNode.leftChild == null && currentNode.rightChild == null) {return;}
		else if(currentNode.leftChild != null || currentNode.rightChild != null) {
			
			subtreeNode(currentNode.leftChild);
			System.out.print(currentNode.key + " ");
			subtreeNode(currentNode.rightChild);
			
		}
		
	}

}
