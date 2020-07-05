//Solution to NAKANJ - Minimum Knight moves !!!
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class NAKANJ {
    private static LinkedList<Integer> adj[];
    private int V;

    NAKANJ(int v) {
        V = v;
        adj = new LinkedList[v+1];
        for (int i = 0; i <= v; ++i)
            adj[i] = new LinkedList();
    }

    static class checkCell{
        int x,y,distance;
        checkCell(int x, int y, int distance){
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    static boolean isIn(int x, int y){
        if(x>=1 && x<=8 && y>=1 && y<=8) return true;
        return false;
    }

    static int[] resolvePosition(String pos){
        int[] xy = new int[2];
        xy[0] = (pos.charAt(0)-'a') +1;
        xy[1] = Integer.parseInt(""+pos.charAt(1));
        return xy;
    }

    static int getMinMoves(int[] init, int[] target){
        int dx[] = { 0,-2, -1, 1, 2, -2, -1, 1, 2 };
        int dy[] = { 0,-1, -2, -2, -1, 1, 2, 2, 1 };

        Queue<checkCell> queue = new LinkedList<checkCell>();
        queue.add(new checkCell(init[0], init[1], 0));
        int x,  y;
        boolean visit[][] = new boolean[9][9];
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                visit[i][j] = false;
            }
        }
        visit[init[0]][init[1]] = true;

        while(!queue.isEmpty()){
            checkCell t = queue.poll();

            if(t.x == target[0] && t.y == target[1])
                return t.distance;

            for(int i = 1; i<=8;i++){
                x = t.x + dx[i];
                y = t.y + dy[i];

                if (isIn(x, y) && !visit[x][y]) {
                    visit[x][y] = true;
                    queue.add(new checkCell(x, y, t.distance + 1));
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    public static void main(String[] args)throws IOException {
        FastReader in = new FastReader();
        int n = in.nextInt();
        while(n-- > 0){
            String x = in. nextLine();
            String arr[] = x.split(" ");
            String initialPos = arr[0];
            String targetPos  = arr[1];
            int[] InitialPos  = resolvePosition(initialPos);
            int[] TargetPos   = resolvePosition(targetPos);
            int ans = getMinMoves(InitialPos, TargetPos);
            System.out.println(ans);
        }
    }

    static class FastReader {
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
