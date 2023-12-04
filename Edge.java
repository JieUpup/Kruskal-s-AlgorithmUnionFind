/**
 * The Edge class models a weighted edge in a graph. It encapsulates the
 * information needed to represent an edge, which includes the start vertex,
 * the end vertex, and the weight of the edge.
 * Author:
 * Update Data:
 */
public class Edge {
    char start;  // The start vertex of the edge
    char end;    // The end vertex of the edge
    int weight;  // The weight associated with the edge

    /**
     * Constructs an Edge with specified start and end vertices and a weight.
     *
     * @param start  The start vertex of the edge.
     * @param end    The end vertex of the edge.
     * @param weight The weight of the edge.
     */
    public Edge(char start, char end, int weight) {
        this.start = start;   // Assigns the start vertex of the edge
        this.end = end;       // Assigns the end vertex of the edge
        this.weight = weight; // Assigns the weight of the edge
    }
}
