//Solution to the A Bug's Life

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Graph {
    private static LinkedList<Integer> adj[];
    private int V;

    Graph(int v) {
        V = v;
        adj = new LinkedList[v+1];
        for (int i = 0; i <= v; ++i)
            adj[i] = new LinkedList();
    }

    public static boolean isBipartiteUtil(int colorArr[], int src) {
        colorArr[src] = 1;
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(src);
        while (!queue.isEmpty()) {
            int check = queue.poll();
            if (adj[check].contains(check)) return false;
            Iterator<Integer> i = adj[check].listIterator();
            while (i.hasNext())
            {
                int n = i.next();
                if( colorArr[n] == -1){
                    colorArr[n] = 1 - colorArr[check];
                    queue.add(n);
                }
                else if (colorArr[n] == colorArr[check]) {
                    return false;
                }
            }

        }
        return true;
    }

    public static boolean isBipartite(int V){
        int colorArr[] = new int[V+1];
        for (int i = 0; i < V+1; ++i)
            colorArr[i] = -1;
        for (int i = 1; i < V+1; i++)
            if (colorArr[i] == -1)
                if (isBipartiteUtil(colorArr, i) == false)
                    return false;
        return true;
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    public static void main(String args[])throws IOException{
        try{
            FastReader in = new FastReader();
            int n = in.nextInt();
            int c = 0;
            while(n-->0){
                int N = in.nextInt();
                int conn = in.nextInt();
                Graph g = new Graph(N);
                for (int i = 0; i < conn; i++) {
                    int u = in.nextInt();
                    int v = in.nextInt();
                    g.addEdge(u,v);
                }
               boolean ans = isBipartite(N);
                System.out.printf ("Scenario #%d:\n", ++c);
                if(ans){
                    System.out.println("No suspicious bugs found!");
                }else{
                    System.out.println("Suspicious bugs found!");
                }

            }
        }catch(Exception e){
            return;
        }
    }
    static class FastReader
    {
        BufferedReader br;
        StringTokenizer st;

        public FastReader()
        {
            br = new BufferedReader(new
                    InputStreamReader(System.in));
        }

        String next()
        {
            while (st == null || !st.hasMoreElements())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException  e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt()
        {
            return Integer.parseInt(next());
        }

        long nextLong()
        {
            return Long.parseLong(next());
        }

        double nextDouble()
        {
            return Double.parseDouble(next());
        }

        String nextLine()
        {
            String str = "";
            try
            {
                str = br.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return str;
        }
    }
}
