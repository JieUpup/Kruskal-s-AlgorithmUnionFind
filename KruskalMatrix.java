import java.io.IOException;
import java.util.Scanner;

public class KruskalMatrix {

    private int mEdgNum;        // Number of edges
    private char[] mVexs;       // Vertex set
    private int[][] mMatrix;    // Adjacency matrix
    private static final int INF = Integer.MAX_VALUE;   // Maximum value

    /*
     * Create a graph (input data yourself)
     */
    public KruskalMatrix() {
        System.out.printf("input vertex number: ");
        int vlen = readInt();
        System.out.printf("input edge number: ");
        int elen = readInt();
        if ( vlen < 1 || elen < 1 || (elen > (vlen*(vlen - 1)))) {
            System.out.printf("input error: invalid parameters!\n");
            return ;
        }
        mVexs = new char[vlen];
        for (int i = 0; i < mVexs.length; i++) {
            System.out.printf("vertex(%d): ", i);
            mVexs[i] = readChar();
        }

       // 1. Initialize "edges" with weight
        mEdgNum = elen;
        mMatrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                if (i==j)
                    mMatrix[i][j] = 0;
                else
                    mMatrix[i][j] = INF;
            }
        }
        // 2. Initialize "edges" with weight: Based on user input
        for (int i = 0; i < elen; i++) {

            System.out.printf("edge(%d):", i);
            char c1 = readChar();       // Read "start vertex"
            char c2 = readChar();       // Read "end vertex"
            int weight = readInt();     // Read "weight"

            int p1 = getPosition(c1);
            int p2 = getPosition(c2);
            if (p1==-1 || p2==-1) {
                System.out.printf("input error: invalid edge!\n");
                return ;
            }

            mMatrix[p1][p2] = weight;
            mMatrix[p2][p1] = weight;
        }
    }

    /*
     * Create a graph (using provided matrix)
     *
     * Parameters:
     *     veArray  -- Vertex array
     *     matrix-- Matrix (data)
     */
    public KruskalMatrix(char[] veArray, int[][] matrix) {


       //Initialize "number of vertices" and "number of edges"
        int veLen = veArray.length;

        // Initialize "vertices"
        mVexs = new char[veLen];
        for (int i = 0; i < mVexs.length; i++)
            mVexs[i] = veArray[i];

        // Initialize "edges"
        mMatrix = new int[veLen][veLen];
        for (int i = 0; i < veLen; i++)
            for (int j = 0; j < veLen; j++)
                mMatrix[i][j] = matrix[i][j];

        // Count "edges"
        mEdgNum = 0;
        for (int i = 0; i < veLen; i++)
            for (int j = i+1; j < veLen; j++)
                if (mMatrix[i][j]!=INF)
                    mEdgNum++;
    }

    /*
     * Return the position of character ch
     */

    private int getPosition(char ch) {
        for(int i=0; i<mVexs.length; i++)
            if(mVexs[i]==ch)
                return i;
        return -1;
    }

    /*
     * Read an input character
     */
    private char readChar() {
        char ch='0';

        do {
            try {
                ch = (char)System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while(!((ch>='a'&&ch<='z') || (ch>='A'&&ch<='Z')));

        return ch;
    }

    /*
     * Read an input character
     */
    private int readInt() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    /*
     * Return the index of the first adjacent vertex of vertex v, return -1 if failed
     */
    private int firstVertex(int v) {

        if (v<0 || v>(mVexs.length-1))
            return -1;

        for (int i = 0; i < mVexs.length; i++)
            if (mMatrix[v][i]!=0 && mMatrix[v][i]!=INF)
                return i;

        return -1;
    }

    /*
     * Return the index of the next adjacent vertex of vertex v relative to w, return -1 if failed
     */
    private int nextVertex(int v, int w) {

        if (v<0 || v>(mVexs.length-1) || w<0 || w>(mVexs.length-1))
            return -1;

        for (int i = w + 1; i < mVexs.length; i++)
            if (mMatrix[v][i]!=0 && mMatrix[v][i]!=INF)
                return i;

        return -1;
    }

    /*
     * Depth-first search traversal of the graph, recursive implementation
     */
    private void DFS(int i, boolean[] visited) {

        visited[i] = true;
        System.out.printf("%c ", mVexs[i]);
        // Traverse all adjacent vertices of this vertex. If not visited, continue
        for (int w = firstVertex(i); w >= 0; w = nextVertex(i, w)) {
            if (!visited[w])
                DFS(w, visited);
        }
    }

    /*
     * Depth-first search traversal of the graph
     */
    public void DFS() {
        boolean[] visited = new boolean[mVexs.length];       // Vertex visit flag

        // Initialize all vertices as not visited
        for (int i = 0; i < mVexs.length; i++)
            visited[i] = false;

        System.out.printf("DFS: ");
        for (int i = 0; i < mVexs.length; i++) {
            if (!visited[i])
                DFS(i, visited);
        }
        System.out.printf("\n");
    }

    /*
     * Breadth-first search (similar to tree level order traversal)
     */
    public void BFS() {
        int head = 0;
        int rear = 0;
        int[] queue = new int[mVexs.length];            // Auxiliary queue
        boolean[] visited = new boolean[mVexs.length];  // Vertex visit flag

        for (int i = 0; i < mVexs.length; i++)
            visited[i] = false;

        System.out.printf("BFS: ");
        for (int i = 0; i < mVexs.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                System.out.printf("%c ", mVexs[i]);
                queue[rear++] = i;  // Enqueue
            }

            while (head != rear) {
                int j = queue[head++];  // Dequeue
                for (int k = firstVertex(j); k >= 0; k = nextVertex(j, k)) { //k is the unvisited adjacent vertex
                    if (!visited[k]) {
                        visited[k] = true;
                        System.out.printf("%c ", mVexs[k]);
                        queue[rear++] = k;
                    }
                }
            }
        }
        System.out.printf("\n");
    }



    /*
     *  Print matrix queue graph
     */
    public void print() {
        System.out.printf("Martix Graph:\n");
        for (int i = 0; i < mVexs.length; i++) {
            for (int j = 0; j < mVexs.length; j++)
                System.out.printf("%10d ", mMatrix[i][j]);
            System.out.printf("\n");
        }
    }

    /*
     * prim MST
     *   start -- from the start of the smallest
     */
    public void prim(int start) {
        int num = mVexs.length;         // Number of vertices
        int index = 0;                  // Index for the Prim's minimum tree, i.e., the index in the prims array
        char[] prims = new char[num];   // Result array for Prim's minimum tree
        int[] weights = new int[num];   // Weights of the edges between vertices

        // The first number in Prim's minimum spanning tree is "the start-th vertex in the graph"
        prims[index++] = mVexs[start];

        // Initialize the "vertex weight array"
        // Set each vertex's weight to the weight from "the start-th vertex" to "that vertex"
        for (int i = 0; i < num; i++)
            weights[i] = mMatrix[start][i];
        // Set the weight of the start-th vertex to 0
        // This can be understood as "the distance from the start-th vertex to itself is 0"
        weights[start] = 0;

        for (int i = 0; i < num; i++) {
            // Since it starts from 'start', no need to process the start-th vertex again
            if (start == i)
                continue;

            int j = 0;
            int k = 0;
            int min = INF;
            // Among the vertices not yet added to the minimum spanning tree, find the one with the smallest weight
            while (j < num) {
                // If weights[j] = 0, it means "the j-th node has been processed" (or has been added to the minimum spanning tree)
                if (weights[j] != 0 && weights[j] < min) {
                    min = weights[j];
                    k = j;
                }
                j++;
            }

            // After the above process, the vertex with the smallest weight among those not yet added to the minimum spanning tree is the k-th vertex
            // Add the k-th vertex to the result array of the minimum spanning tree
            prims[index++] = mVexs[k];
            // Mark the "weight of the k-th vertex" as 0, meaning the k-th vertex has been processed (or added to the minimum tree result)
            weights[k] = 0;
            // After the k-th vertex has been added to the result array of the minimum spanning tree, update the weights of the other vertices
            for (j = 0; j < num; j++) {
                // Update when the j-th node has not been processed and needs to be updated
                if (weights[j] != 0 && mMatrix[k][j] < weights[j])
                    weights[j] = mMatrix[k][j];
            }
        }

        // Calculate the weight of the minimum spanning tree
        int sum = 0;
        for (int i = 1; i < index; i++) {
            int min = INF;
            // Get the position of prims[i] in mMatrix
            int n = getPosition(prims[i]);
            // Among vexs[0...i], find the vertex with the smallest weight to j
            for (int j = 0; j < i; j++) {
                int m = getPosition(prims[j]);
                if (mMatrix[m][n] < min)
                    min = mMatrix[m][n];
            }
            sum += min;
        }
        // Print the minimum spanning tree
        System.out.printf("PRIM(%c)=%d: ", mVexs[start], sum);
        for (int i = 0; i < index; i++)
            System.out.printf("%c ", prims[i]);
        System.out.printf("\n");
    }

    /*
     * Kruskal's MST
     */
    public void kruskal() {
        int index = 0;                      // Index for the rets array
        int[] vends = new int[mEdgNum];     // Used to save the end vertices in the existing minimum spanning tree
        EData[] rets = new EData[mEdgNum];  // Result array, storing the edges of Kruskal's minimum spanning tree
        EData[] edges;                      // All edges of the graph

        // Get "all the edges of the graph"
        edges = getEdges();
        // Sort the edges by "weight" size (from smallest to largest)
        sortEdges(edges, mEdgNum);


        for (int i = 0; i < mEdgNum; i++) {
            int p1 = getPosition(edges[i].start); // Get the sequence number of the "start point" of edge i
            int p2 = getPosition(edges[i].end);   // Get the sequence number of the "end point" of edge i

            int m = getEnd(vends, p1);            // Get the end of p1 in the "existing minimum spanning tree"
            int n = getEnd(vends, p2);            // Get the end of p2 in the "existing minimum spanning tree"
            // If m != n, it means that "edge i" does not form a cycle with the vertices already added to the minimum spanning tree
            if (m != n) {
                vends[m] = n;                     // Set the end of m in the "existing minimum spanning tree" as n
                rets[index++] = edges[i];         // Save the result
            }
        }

        // Sum up and print the information of "Kruskal's minimum spanning tree"
        int length = 0;
        for (int i = 0; i < index; i++)
            length += rets[i].weight;
        System.out.printf("Kruskal=%d: ", length);
        for (int i = 0; i < index; i++)
            System.out.printf("(%c,%c) ", rets[i].start, rets[i].end);
        System.out.printf("\n");
    }

    private EData[] getEdges() {
        int index=0;
        EData[] edges;

        edges = new EData[mEdgNum];
        for (int i=0; i < mVexs.length; i++) {
            for (int j=i+1; j < mVexs.length; j++) {
                if (mMatrix[i][j]!=INF) {
                    edges[index++] = new EData(mVexs[i], mVexs[j], mMatrix[i][j]);
                }
            }
        }

        return edges;
    }

    /*
     * sort the edges by the wight
     */
    private void sortEdges(EData[] edges, int elen) {

        for (int i=0; i<elen; i++) {
            for (int j=i+1; j<elen; j++) {

                if (edges[i].weight > edges[j].weight) {
                    // exchange i and j, swap
                    EData tmp = edges[i];
                    edges[i] = edges[j];
                    edges[j] = tmp;
                }
            }
        }
    }

    private int getEnd(int[] vends, int i) {
        while (vends[i] != 0)
            i = vends[i];
        return i;
    }
    private static class EData {
        char start;
        char end;
        int weight;

        public EData(char start, char end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    };


    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int matrix[][] = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {   0,  12, INF, INF, INF,  16,  14},
                /*B*/ {  12,   0,  10, INF, INF,   7, INF},
                /*C*/ { INF,  10,   0,   3,   5,   6, INF},
                /*D*/ { INF, INF,   3,   0,   4, INF, INF},
                /*E*/ { INF, INF,   5,   4,   0,   2,   8},
                /*F*/ {  16,   7,   6, INF,   2,   0,   9},
                /*G*/ {  14, INF, INF, INF,   8,   9,   0}};
        KruskalMatrix pG;
        //pG = new MatrixUDG();
        pG = new KruskalMatrix(vexs, matrix);

        //pG.print();
        //pG.DFS();
        //pG.BFS();
        pG.prim(0);
        pG.kruskal();
    }
}