/**
 * The class use main method to test our Kruskal algorithm with the matrix.
 * The KruskalMatrixTest class demonstrates the implementation for finding the Minimum Spanning Tree (MST) in a given weighted graph.
 * This class specifically uses a matrix representation for the graph.
 * The main method initializes a graph with a set of vertices labeled 'A' through 'G'
 * Graph.INF' indicating no direct edge between vertices. The Kruskal's algorithm is then applied to this graph to find the MST.
 * The algorithm is executed through the 'MST.kruskal' method, which takes the graph as its input and processes it to find the minimum spanning tree, showcasing
 *  Kruskal's algorithm efficiency in dealing with sparse graphs.
 * Author:
 * Update Data:
 */

public class KruskalMatrixTest {
    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                {0, 12, Graph.INF, Graph.INF, Graph.INF, 16, 14},
                {12, 0, 10, Graph.INF, Graph.INF, 7, Graph.INF},
                {Graph.INF, 10, 0, 3, 5, 6, Graph.INF},
                {Graph.INF, Graph.INF, 3, 0, 4, Graph.INF, Graph.INF},
                {Graph.INF, Graph.INF, 5, 4, 0, 2, 8},
                {16, 7, 6, Graph.INF, 2, 0, 9},
                {14, Graph.INF, Graph.INF, Graph.INF, 8, 9, 0}
        };

        Graph graph = new Graph(vexs, matrix);
        MST.kruskal(graph);
    }
}

