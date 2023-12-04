
/**
 * The class give a static method called kruskal with graph parameters from Graph class.
 * Meanwhile, this class including two static method to get end an eages from graph.
 * Author:
 * Update Data:
 */
public class MST {

    public static void kruskal(Graph graph) {
        int index = 0;
        int[] vends = new int[graph.getEdgNum()];
        Edge[] rets = new Edge[graph.getEdgNum()];
        Edge[] edges = getEdges(graph);

        GraphDefine.sortEdges(edges, graph.getEdgNum());

        for (int i = 0; i < graph.getEdgNum(); i++) {
            int p1 = GraphDefine.getPosition(graph.getVexs(), edges[i].start);
            int p2 = GraphDefine.getPosition(graph.getVexs(), edges[i].end);

            int m = getEnd(vends, p1);
            int n = getEnd(vends, p2);
            if (m != n) {
                vends[m] = n;
                rets[index++] = edges[i];
            }
        }

        int length = 0;
        for (int i = 0; i < index; i++) {
            length += rets[i].weight;
        }
        System.out.printf("Kruskal=%d: ", length);
        for (int i = 0; i < index; i++) {
            System.out.printf("(%c,%c) ", rets[i].start, rets[i].end);
        }
        System.out.printf("\n");
    }

    private static Edge[] getEdges(Graph graph) {
        int index = 0;
        Edge[] edges = new Edge[graph.getEdgNum()];

        for (int i = 0; i < graph.getVexs().length; i++) {
            for (int j = i + 1; j < graph.getVexs().length; j++) {
                if (graph.getMatrix()[i][j] != Graph.INF) {
                    edges[index++] = new Edge(graph.getVexs()[i], graph.getVexs()[j], graph.getMatrix()[i][j]);
                }
            }
        }
        return edges;
    }

    private static int getEnd(int[] vends, int i) {
        while (vends[i] != 0) {
            i = vends[i];
        }
        return i;
    }

}


