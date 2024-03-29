package Dijstra;

public class DijkstraAM{

    static class Graph{
        int vertices;
        int matrix[][];

        public Graph(int vertex) {
            this.vertices = vertex;
            matrix = new int[vertex][vertex];
        }

        public void addEdge(int source, int destination, int weight) {
            //add edge
            matrix[source][destination]=weight;

            //add back edge for undirected graph
            matrix[destination][source] = weight;
        }

        //get the vertex with minimum distance which is not included in SPT
        int getMinimumVertex(boolean [] mst, int [] key){
            int minKey = Integer.MAX_VALUE;
            int vertex = -1;
            for (int i = 0; i <vertices ; i++) {
                if(mst[i]==false && minKey>key[i]){
                    minKey = key[i];
                    vertex = i;
                }
            }
            return vertex;
        }

        public void dijkstra_GetMinDistances(int sourceVertex){
            boolean[] spt = new boolean[vertices];
            int [] distance = new int[vertices];
            int INFINITY = Integer.MAX_VALUE;

            //Initialize all the distance to infinity
            for (int i = 0; i <vertices ; i++) {
                distance[i] = INFINITY;
            }

            //start from the vertex 0
            distance[sourceVertex] = 0;

            //create SPT
            for (int i = 0; i <vertices ; i++) {

                //get the vertex with the minimum distance
                int vertex_U = getMinimumVertex(spt, distance);

                //include this vertex in SPT
                spt[vertex_U] = true;

                //iterate through all the adjacent vertices of above vertex and update the keys
                for (int vertex_V = 0; vertex_V <vertices ; vertex_V++) {
                    //check of the edge between vertex_U and vertex_V
                    if(matrix[vertex_U][vertex_V]>0){
                        //check if this vertex 'vertex_V' already in spt and
                        // if distance[vertex_V]!=Infinity

                        if(spt[vertex_V]==false && matrix[vertex_U][vertex_V]!=INFINITY){
                            //check if distance needs an update or not
                            //means check total weight from source to vertex_V is less than
                            //the current distance value, if yes then update the distance
                            int newKey =  matrix[vertex_U][vertex_V] + distance[vertex_U];
                            if(newKey<distance[vertex_V])
                                distance[vertex_V] = newKey;
                        }
                    }
                }
            }
            //print shortest path tree
            printDijkstra(sourceVertex, distance);
        }

        public void printDijkstra(int sourceVertex, int [] key){
            System.out.println("Dijkstra Algorithm: (Adjacency Matrix)");
            for (int i = 0; i <vertices ; i++) {
                System.out.println("From: " + sourceVertex + " to vertex " +   + i +
                        " shortest distance is : " + key[i]);
            }
        }
    }

    public static void main(String[] args) {
        int vertices = 10;
        Graph graph = new Graph(vertices);
        int sourceVertex = 2;
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 4, 3);
        graph.addEdge(1, 4, 4);
        graph.addEdge(1, 5, 2);
        graph.addEdge(2, 5, 6);
        graph.addEdge(3, 9, 7);
        graph.addEdge(4, 5, 5);
        graph.addEdge(4, 6, 10);
        graph.addEdge(4, 7, 6);
        graph.addEdge(4, 9, 3);
        graph.addEdge(5, 7, 9);
        graph.addEdge(5, 8, 7);
        graph.addEdge(6, 7, 1);
        graph.addEdge(7, 8, 2);
        graph.addEdge(7, 9, 8); 
        graph.dijkstra_GetMinDistances(sourceVertex);
    }
}
