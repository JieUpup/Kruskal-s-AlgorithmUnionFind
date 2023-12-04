import java.io.IOException;
import java.util.Scanner;

public class KruskalList {
    private static int INF = Integer.MAX_VALUE;
    private class ENode {
        int ivex;
        int weight;
        ENode nextEdge;
    }

    private class VNode {
        char data;
        ENode firstEdge;
    };

    private int mEdgNum;
    private VNode[] mVexs;


    /*
     *
     */
    public KruskalList() {
        System.out.printf("input vertex number: ");
        int vlen = readInt();
        System.out.printf("input edge number: ");
        int elen = readInt();
        if ( vlen < 1 || elen < 1 || (elen > (vlen*(vlen - 1)))) {
            System.out.printf("input error: invalid parameters!\n");
            return ;
        }

        mVexs = new VNode[vlen];
        for (int i = 0; i < mVexs.length; i++) {
            System.out.printf("vertex(%d): ", i);
            mVexs[i] = new VNode();
            mVexs[i].data = readChar();
            mVexs[i].firstEdge = null;
        }

        mEdgNum = elen;
        for (int i = 0; i < elen; i++) {
            System.out.printf("edge(%d):", i);
            char c1 = readChar();
            char c2 = readChar();
            int weight = readInt();

            int p1 = getPosition(c1);
            int p2 = getPosition(c2);


            ENode node1 = new ENode();
            node1.ivex = p2;
            node1.weight = weight;

            if(mVexs[p1].firstEdge == null)
                mVexs[p1].firstEdge = node1;
            else
                linkLast(mVexs[p1].firstEdge, node1);

            ENode node2 = new ENode();
            node2.ivex = p1;
            node2.weight = weight;

            if(mVexs[p2].firstEdge == null)
                mVexs[p2].firstEdge = node2;
            else
                linkLast(mVexs[p2].firstEdge, node2);
        }
    }


    public KruskalList(char[] vexs, EData[] edges) {


        int vlen = vexs.length;
        int elen = edges.length;


        mVexs = new VNode[vlen];
        for (int i = 0; i < mVexs.length; i++) {
            mVexs[i] = new VNode();
            mVexs[i].data = vexs[i];
            mVexs[i].firstEdge = null;
        }

        mEdgNum = elen;
        for (int i = 0; i < elen; i++) {

            char c1 = edges[i].start;
            char c2 = edges[i].end;
            int weight = edges[i].weight;


            int p1 = getPosition(c1);
            int p2 = getPosition(c2);

            ENode node1 = new ENode();
            node1.ivex = p2;
            node1.weight = weight;

            if(mVexs[p1].firstEdge == null)
                mVexs[p1].firstEdge = node1;
            else
                linkLast(mVexs[p1].firstEdge, node1);


            ENode node2 = new ENode();
            node2.ivex = p1;
            node2.weight = weight;

            if(mVexs[p2].firstEdge == null)
                mVexs[p2].firstEdge = node2;
            else
                linkLast(mVexs[p2].firstEdge, node2);
        }
    }


    private void linkLast(ENode list, ENode node) {
        ENode p = list;

        while(p.nextEdge!=null)
            p = p.nextEdge;
        p.nextEdge = node;
    }


    private int getPosition(char ch) {
        for(int i=0; i<mVexs.length; i++)
            if(mVexs[i].data==ch)
                return i;
        return -1;
    }

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

    private int readInt() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private void DFS(int i, boolean[] visited) {
        ENode node;
        visited[i] = true;
        System.out.printf("%c ", mVexs[i].data);
        node = mVexs[i].firstEdge;
        while (node != null) {
            if (!visited[node.ivex])
                DFS(node.ivex, visited);
            node = node.nextEdge;
        }
    }


    public void DFS() {
        boolean[] visited = new boolean[mVexs.length];

        for (int i = 0; i < mVexs.length; i++)
            visited[i] = false;

        System.out.printf("DFS: ");
        for (int i = 0; i < mVexs.length; i++) {
            if (!visited[i])
                DFS(i, visited);
        }
        System.out.printf("\n");
    }

    public void BFS() {
        int head = 0;
        int rear = 0;
        int[] queue = new int[mVexs.length];
        boolean[] visited = new boolean[mVexs.length];

        for (int i = 0; i < mVexs.length; i++)
            visited[i] = false;

        System.out.printf("BFS: ");
        for (int i = 0; i < mVexs.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                System.out.printf("%c ", mVexs[i].data);
                queue[rear++] = i;  //
            }

            while (head != rear) {
                int j = queue[head++];  //
                ENode node = mVexs[j].firstEdge;
                while (node != null) {
                    int k = node.ivex;
                    if (!visited[k])
                    {
                        visited[k] = true;
                        System.out.printf("%c ", mVexs[k].data);
                        queue[rear++] = k;
                    }
                    node = node.nextEdge;
                }
            }
        }
        System.out.printf("\n");
    }



    public void print() {
        System.out.printf("List Graph:\n");
        for (int i = 0; i < mVexs.length; i++) {
            System.out.printf("%d(%c): ", i, mVexs[i].data);
            ENode node = mVexs[i].firstEdge;
            while (node != null) {
                System.out.printf("%d(%c) ", node.ivex, mVexs[node.ivex].data);
                node = node.nextEdge;
            }
            System.out.printf("\n");
        }
    }
    
    private int getWeight(int start, int end) {

        if (start == end)
            return 0;

        ENode node = mVexs[start].firstEdge;
        while (node != null) {
            if (end == node.ivex)
                return node.weight;
            node = node.nextEdge;
        }

        return INF;
    }


    public void prim(int start) {
        int min,i,j,k,m,n,tmp,sum;
        int num = mVexs.length;
        int index=0;
        char[] prims = new char[num];
        int[] weights = new int[num];

        prims[index++] = mVexs[start].data;


        for (i = 0; i < num; i++ )
            weights[i] = getWeight(start, i);

        for (i = 0; i < num; i++) {
            if(start == i)
                continue;

            j = 0;
            k = 0;
            min = INF;

            while (j < num) {
                if (weights[j] != 0 && weights[j] < min) {
                    min = weights[j];
                    k = j;
                }
                j++;
            }


            prims[index++] = mVexs[k].data;

            weights[k] = 0;

            for (j = 0 ; j < num; j++) {

                tmp = getWeight(k, j);

                if (weights[j] != 0 && tmp < weights[j])
                    weights[j] = tmp;
            }
        }


        sum = 0;
        for (i = 1; i < index; i++) {
            min = INF;

            n = getPosition(prims[i]);

            for (j = 0; j < i; j++) {
                m = getPosition(prims[j]);
                tmp = getWeight(m, n);
                if (tmp < min)
                    min = tmp;
            }
            sum += min;
        }

        System.out.printf("PRIM(%c)=%d: ", mVexs[start].data, sum);
        for (i = 0; i < index; i++)
            System.out.printf("%c ", prims[i]);
        System.out.printf("\n");
    }

    public void kruskal() {
        int index = 0;
        int[] vends = new int[mEdgNum];
        EData[] rets = new EData[mEdgNum];
        EData[] edges;

        edges = getEdges();

        sortEdges(edges, mEdgNum);

        for (int i=0; i<mEdgNum; i++) {
            int p1 = getPosition(edges[i].start);
            int p2 = getPosition(edges[i].end);

            int m = getEnd(vends, p1);
            int n = getEnd(vends, p2);
            if (m != n) {
                vends[m] = n;
                rets[index++] = edges[i];
            }
        }


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

            ENode node = mVexs[i].firstEdge;
            while (node != null) {
                if (node.ivex > i) {
                    edges[index++] = new EData(mVexs[i].data, mVexs[node.ivex].data, node.weight);
                }
                node = node.nextEdge;
            }
        }

        return edges;
    }

    private void sortEdges(EData[] edges, int elen) {

        for (int i=0; i<elen; i++) {
            for (int j=i+1; j<elen; j++) {

                if (edges[i].weight > edges[j].weight) {
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
        EData[] edges = {
                new EData('A', 'B', 12),
                new EData('A', 'F', 16),
                new EData('A', 'G', 14),
                new EData('B', 'C', 10),
                new EData('B', 'F',  7),
                new EData('C', 'D',  3),
                new EData('C', 'E',  5),
                new EData('C', 'F',  6),
                new EData('D', 'E',  4),
                new EData('E', 'F',  2),
                new EData('E', 'G',  8),
                new EData('F', 'G',  9),
        };
        KruskalList pG;


        //pG = new ListUDG();
        // use current graph
        pG = new KruskalList(vexs, edges);

        //pG.print();
        //pG.DFS();
        //pG.BFS();
        //pG.prim(0);

        pG.kruskal();
    }
}
