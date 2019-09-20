package Topo;

import java.awt.List;
import java.util.LinkedList;
import java.util.Stack;

import javax.sql.rowset.FilteredRowSet;

public class TopologicalSort {
	
    static class Graph {
        int vertices; 
        int nVerts;
        int v = 0;
        static int num;
        static int Cycle[];
        static String List[];
        LinkedList<Integer>[] adjList;

        Graph(int vertices) {
        	nVerts = 0;
        	num =0;
            this.vertices = vertices;
            List= new String[vertices];
            Cycle= new int[vertices];
            adjList = new LinkedList[vertices];
            for (int i = 0; i < vertices; i++) {
                adjList[i] = new LinkedList<>();
            }
        }
        public void addEgde(int source, int destination) {
            adjList[source].addFirst(destination); 
            System.out.print(List[source] + List[destination]+" ");      	
        }
        public void addVertex(String lab) {
			List[nVerts++] = lab;
		}
        public void topologicalSorting() {
            boolean[] visited = new boolean[vertices];
            Stack<Integer> stack = new Stack<>();
            //visit from each node if not already visited
            
            Cycle[num++] = 0;
            for (int i = 0; i < vertices; i++) {
                if (!visited[i]) {
                    topologicalSortUtil(i, visited, stack);
                }
            }
            System.out.print("\nTopological Sort:   ");
            int size = stack.size();
            Cycle[num++] = 3;
            for (int i = 0; i <size ; i++) {
                System.out.print(List[stack.pop()] + " ");
            }
        }

        public void topologicalSortUtil(int start, boolean[] visited, Stack<Integer> stack) {
            visited[start] = true;
            for (int i = 0; i < adjList[start].size(); i++) {
                int vertex = adjList[start].get(i);
                if (!visited[vertex])
                    topologicalSortUtil(vertex, visited, stack);
            }
            stack.push(start);
        }
        
        public boolean isCycle() {
            boolean visited[] = new boolean[vertices];
            boolean recursiveArr[] = new boolean[vertices];

            //do DFS from each node
            Cycle[num++] = 4;
            for (int i = 0; i < vertices; i++) {
                if (isCycleUtil(i, visited, recursiveArr))
                    return true;
            }
            return false;
        }

        public boolean isCycleUtil(int vertex, boolean[] visited, boolean[] recursiveArr) {
            visited[vertex] = true;
            recursiveArr[vertex] = true;

            //recursive call to all the adjacent vertices
            for (int i = 0; i < adjList[vertex].size(); i++) {
                //if not already visited
                int adjVertex = adjList[vertex].get(i);
                if (!visited[adjVertex] && isCycleUtil(adjVertex, visited, recursiveArr)) {
                    return true;
                } else if (recursiveArr[adjVertex])
                    return true;
            }
            //if reached here means cycle has not found in DFS from this vertex
            //reset
            recursiveArr[vertex] = false;
            return false;
        }
        public void showCycle() {
        	Cycle[num++] = 0;
        	for(int i = 0; i < num; i++) {
        		 System.out.print(List[Cycle[i]]);
        		 if(i<num-1) {
        			 System.out.print("-->");
        		 }
        	}
        }
    }
    public static void main(String[] args) {
        int vertices = 13;
        Graph graph = new Graph(vertices);
        graph.addVertex("A");
		graph.addVertex("B");
		graph.addVertex("C");
		graph.addVertex("D");
		graph.addVertex("E");
		graph.addVertex("F");
		graph.addVertex("G");
		graph.addVertex("H");
		graph.addVertex("I");
		graph.addVertex("J");
		graph.addVertex("K");
		graph.addVertex("L");
		graph.addVertex("M");
		System.out.print("The edges are:      ");
        graph.addEgde(0, 3);
		graph.addEgde(1, 3);
		graph.addEgde(2, 0);
		graph.addEgde(2, 1);
		graph.addEgde(3, 7);
		graph.addEgde(3, 6);
		graph.addEgde(4, 3);
		graph.addEgde(4, 5);
		graph.addEgde(5, 9);
		graph.addEgde(5, 10);
		graph.addEgde(6, 8);
		graph.addEgde(7, 8);
		graph.addEgde(7, 9);
		graph.addEgde(8, 11);
		graph.addEgde(9, 11);
		graph.addEgde(9, 12);
		graph.addEgde(10, 9);
        graph.topologicalSorting();
        System.out.print("\n***********************************************");
        System.out.print("\n*********** Change edge ED----> ");
        graph.addEgde(3, 4);
        System.out.print("***********");
        System.out.print("\n***********************************************");
        boolean result = graph.isCycle();
	    System.out.println("\nIs there any Cycle present: " + result);
        if(result) {
        	System.out.print("The Cycle is:      ");
        	graph.showCycle();
        }    
    }
}