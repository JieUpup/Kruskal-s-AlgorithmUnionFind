import java.util.*;

public class UnionFindAlgo {
    public class Edge implements Comparable<Edge> {
        public int src, dest, weight;

        public int compareTo(Edge compareEdge) {
            return this.weight - compareEdge.weight;
        }
    }
    public class Subset {
        public int parent, rank;
    }
    public int vertices, edges;
    Edge[] edge;

    public UnionFindAlgo(int v, int e) {
        vertices = v;
        edges = e;
        edge = new Edge[edges];
        for (int i = 0; i < e; ++i) {
            edge[i] = new Edge();// we consider this one is the new parent,
        }
    }

    public int find(Subset subsets[], int i) {
        if (subsets[i].parent != i) {
            subsets[i].parent = find(subsets, subsets[i].parent);
        }
        return subsets[i].parent;
    }

    public void union(Subset subsets[], int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets[xroot].rank < subsets[yroot].rank) {
            subsets[xroot].parent = yroot;
        } else if (subsets[xroot].rank > subsets[yroot].rank) {
            subsets[yroot].parent = xroot;
        } else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }
    public void kruskalMST() {
        Edge[] result = new Edge[vertices];
        int e = 0;
        int i = 0;
        int totalWeight = 0;  // Variable to store total weight of MST

        for (i = 0; i < vertices; ++i) {
            result[i] = new Edge();
        }

        Arrays.sort(edge);

        Subset[] subsets = new Subset[vertices];
        for (i = 0; i < vertices; ++i) {
            subsets[i] = new Subset();
            subsets[i].parent = i;
            subsets[i].rank = 0;
        }

        i = 0;
        while (e < vertices - 1 && i < edges) {
            Edge next_edge = edge[i++];

            int x = find(subsets, next_edge.src);
            int y = find(subsets, next_edge.dest);

            if (x != y) {
                result[e++] = next_edge;
                union(subsets, x, y);
                totalWeight += next_edge.weight;  // Add weight to total
            }
        }

        if (e != vertices - 1) {
            System.out.println("The graph does not contain a spanning tree");
            return;
        }

        System.out.println("Following are the edges in the constructed MST");
        for (i = 0; i < e; ++i) {
            System.out.println(result[i].src + " -- " + result[i].dest + " == " + result[i].weight);
        }
        System.out.println("Total weight of MST is " + totalWeight);
    }


    public static void main(String[] args) {
        int V = 4;  // Number of vertices in graph
        int E = 5;  // Number of edges in graph
        UnionFindAlgo graph = new UnionFindAlgo(V, E);

        // add edge 0-1
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;
        graph.edge[0].weight = 10;

        // add edge 0-2
        graph.edge[1].src = 0;
        graph.edge[1].dest = 2;
        graph.edge[1].weight = 6;

        // add edge 0-3
        graph.edge[2].src = 0;
        graph.edge[2].dest = 3;
        graph.edge[2].weight = 5;

        // add edge 1-3
        graph.edge[3].src = 1;
        graph.edge[3].dest = 3;
        graph.edge[3].weight = 15;

        // add edge 2-3
        graph.edge[4].src = 2;
        graph.edge[4].dest = 3;
        graph.edge[4].weight = 4;

        // Function call
        graph.kruskalMST();
    }
}

