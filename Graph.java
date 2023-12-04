
/**
 * The Graph class represents a weighted graph using an adjacency matrix where
 * each cell [i][j] in the matrix represents the weight of the edge between
 * vertices i and j.
 * Author:
 * Update Data:
 */
public class Graph {
    private int mEdgNum;  // The number of edges in the graph
    private char[] mVexs;  // An array of the vertices in the graph
    private int[][] mMatrix;  // The adjacency matrix of the graph
    public static final int INF = Integer.MAX_VALUE;  // Represents an absence of edge

    /**
     * Constructs a Graph using an array of vertices and an adjacency matrix.
     * The constructor also counts the number of edges that are not infinity (INF),
     * which implies that there is an edge between the vertices
     * @param vexs   An array of vertices to be included in the graph.
     * @param matrix The adjacency matrix representing the edges and their weights.
     */
    public Graph(char[] vexs, int[][] matrix) {
        int vlen = vexs.length;
        mVexs = new char[vlen];
        System.arraycopy(vexs, 0, mVexs, 0, mVexs.length);  // Copy vertices

        mMatrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            System.arraycopy(matrix[i], 0, mMatrix[i], 0, vlen);  // Copy adjacency matrix
        }

        mEdgNum = 0;  // Initialize edge count
        // Count the number of actual edges (where weight is not INF)
        for (int i = 0; i < vlen; i++) {
            for (int j = i + 1; j < vlen; j++) {
                if (mMatrix[i][j] != INF) {
                    mEdgNum++;
                }
            }
        }
    }

    /**
     * Gets the array of vertices in the graph.
     *
     * @return An array of vertices.
     */
    public char[] getVexs() {
        return mVexs;
    }

    /**
     * Gets the adjacency matrix of the graph.
     * @return The adjacency matrix with edge weights.
     */
    public int[][] getMatrix() {
        return mMatrix;
    }

    /**
     * Gets the number of edges in the graph.
     * @return The number of edges.
     */
    public int getEdgNum() {
        return mEdgNum;
    }
}
