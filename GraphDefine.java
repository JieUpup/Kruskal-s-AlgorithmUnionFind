/**
 * This utility class provides static methods related to graph operations.
 * It contains methods for getting the position of a vertex within an array
 * and for sorting edges of a graph based on their weights.
 */
public class GraphDefine {

    /**
     * Gets the position of a vertex in the array of vertices.
     *
     * @param mVexs The array of vertices.
     * @param ch The vertex to find.
     * @return The index of the vertex in the array, or -1 if not found.
     */
    public static int getPosition(char[] mVexs, char ch) {
        for (int i = 0; i < mVexs.length; i++) {
            if (mVexs[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Sorts an array of edges in non-decreasing order based on their weights.
     * It uses a simple bubble sort algorithm, which is not the most efficient
     * method for large datasets but suffices for educational purposes and
     * smaller graphs.
     *
     * @param edges The array of edges to sort.
     * @param elen The number of edges in the array.
     */
    public static void sortEdges(Edge[] edges, int elen) {
        for (int i = 0; i < elen; i++) {
            for (int j = i + 1; j < elen; j++) {
                if (edges[i].weight > edges[j].weight) {
                    Edge tmp = edges[i];
                    edges[i] = edges[j];
                    edges[j] = tmp;
                }
            }
        }
    }
}
