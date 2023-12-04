public class GraphTraversal {
    public static void DFS(Graph graph, int i, boolean[] visited) {
        char[] mVexs = graph.getVexs();
        int[][] mMatrix = graph.getMatrix();

        visited[i] = true;
        System.out.printf("%c ", mVexs[i]);
        for (int w = firstVertex(mMatrix, i); w >= 0; w = nextVertex(mMatrix, i, w)) {
            if (!visited[w]) {
                DFS(graph, w, visited);
            }
        }
    }

    public static void BFS(Graph graph) {
        char[] mVexs = graph.getVexs();
        int[][] mMatrix = graph.getMatrix();

        int head = 0;
        int rear = 0;
        int[] queue = new int[mVexs.length];
        boolean[] visited = new boolean[mVexs.length];

        for (int i = 0; i < mVexs.length; i++) {
            visited[i] = false;
        }

        System.out.printf("BFS: ");
        for (int i = 0; i < mVexs.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                System.out.printf("%c ", mVexs[i]);
                queue[rear++] = i;
            }

            while (head != rear) {
                int j = queue[head++];
                for (int k = firstVertex(mMatrix, j); k >= 0; k = nextVertex(mMatrix, j, k)) {
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

    private static int firstVertex(int[][] mMatrix, int v) {
        for (int i = 0; i < mMatrix[v].length; i++) {
            if (mMatrix[v][i] != 0 && mMatrix[v][i] != Graph.INF) {
                return i;
            }
        }
        return -1;
    }

    private static int nextVertex(int[][] mMatrix, int v, int w) {
        for (int i = w + 1; i < mMatrix[v].length; i++) {
            if (mMatrix[v][i] != 0 && mMatrix[v][i] != Graph.INF) {
                return i;
            }
        }
        return -1;
    }
}

