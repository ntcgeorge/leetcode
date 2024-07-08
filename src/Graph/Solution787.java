import java.io.PrintWriter;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Math;

/**
 * https://leetcode.cn/problems/cheapest-flights-within-k-stops/submissions/533149962/
 * time: 2hrs
 * note: too hard
 */
public class Solution787 {

    public static void main(String[] args) {
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        int n = fr.nextInt();
        for(int i=0; i < n; i++) {
            Solution787 sol = new Solution787();
            int m = fr.nextInt();
            int sz = fr.nextInt();
            int[][] flights = new int[sz][];
            for(int j=0; j < sz; j++) {
                flights[j] = fr.readIntLine(",");
            }
            int src = fr.nextInt();
            int dst = fr.nextInt();
            int k = fr.nextInt();
            int ans = sol.findCheapestPrice(m, flights, src, dst, k);
            out.println(ans);
        }
        out.close();
    }

    
    class Edge {
        int weight;
        int from;
        int to;
        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        
        int other(int w) {
            if(this.from == w) return this.to;
            if(this.to == w) return this.from;
            return -1;
        }

        int from() {
            return this.from;
        }

        int to() {
            return this.to;
        }
    }

    int memo[][];
    ArrayList<Edge>[] indegree;
    Queue<Integer> pq;
    boolean[] marked;
    int src;
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        this.src = src;
        memo = new int[n][k+2]; // memo[i][j] = the shortest distance for i in j steps
        indegree = (ArrayList<Edge>[]) new ArrayList[n];
        marked = new boolean[n];
        for(int i=0; i < n; i++) {
            indegree[i] = new ArrayList<Edge>();
        }
        for(int[] flight : flights) {
            Edge edge = new Edge(flight[0], flight[1], flight[2]);
            indegree[flight[1]].add(edge);
        }
        for(int[] row : memo) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        int ans = dp(dst, k+1);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int dp(int i, int j) {
        if(i == src) return 0;
        if(j == 0) return -1;

        if(memo[i][j] != Integer.MAX_VALUE) return memo[i][j];

        for(Edge e : indegree[i]) {
            int sub = dp(e.from(), j-1);
            if(sub == -1) continue;
            memo[i][j] = Math.min(sub + e.weight, memo[i][j]);
        }
        if(memo[i][j] == Integer.MAX_VALUE) memo[i][j] = -1;
        return memo[i][j];
    }


}

class FastReader { 
    BufferedReader br; 
    StringTokenizer st; 

    public FastReader() 
    { 
        br = new BufferedReader( 
            new InputStreamReader(System.in)); 
    } 

    String next() 
    { 
        while (st == null || !st.hasMoreElements()) { 
            try { 
                st = new StringTokenizer(br.readLine()); 
            } 
            catch (IOException e) { 
                e.printStackTrace(); 
            } 
        } 
        return st.nextToken(); 
    } 

    int nextInt() { return Integer.parseInt(next()); } 

    long nextLong() { return Long.parseLong(next()); } 

    double nextDouble() 
    { 
        return Double.parseDouble(next()); 
    } 

    String nextLine() 
    { 
        String str = ""; 
        try { 
            if(st.hasMoreTokens()){ 
                str = st.nextToken("\n"); 
            } 
            else{ 
                str = br.readLine(); 
            } 
        } 
        catch (IOException e) { 
            e.printStackTrace(); 
        } 
        return str; 
    }

    int[] readIntLine(String d) {
        String line = nextLine();
        String[] lineSplit = line.split(d);
        int[] intLine = new int[lineSplit.length];
        for(int j=0; j < intLine.length;j++) {
            intLine[j] = Integer.parseInt(lineSplit[j]);
        }
        return intLine;
    }

    int[][] readTwoDimensionArray(String d) {
        int n = nextInt();
        int[][] grid = new int[n][];
        for(int i=0; i < n; i++) {
            grid[i] = readIntLine(d);
        }
        return grid;
    }
} 

class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public TreeNode(String[] inputs) {
        this();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(this);
        if (!inputs[0].equals("null")) {
            this.val = Integer.parseInt(inputs[0]);
        } else {
            return;
        }
        int i = 1;
        while (true) {
            int size = q.size();
            for (int j = 0; j < size; j++) {
                //dequeue the previous element
                TreeNode node = q.poll();
                
                if (i >= inputs.length) return;
                String entry = inputs[i];
                if (!entry.equals("null")) {
                    node.left = new TreeNode(Integer.parseInt(entry));
                    q.offer(node.left);
                }
                i++;

                if (i >= inputs.length) return;
                entry = inputs[i];
                if (!entry.equals("null")) {
                    node.right = new TreeNode(Integer.parseInt(entry));
                    q.offer(node.right);
                }
                i++;
            }
        }

    }

    public String toString() {
        Queue<TreeNode>  queue= new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        queue.offer(this);
        while(!queue.isEmpty()) {
            Stack<TreeNode> curr = new Stack<>();
            int size = queue.size();
            for(int i=0; i < size; i++) {
                TreeNode node = queue.poll();
                if(node != null) {
                    queue.offer(node.left);
                    queue.offer(node.right);
                } 
                curr.push(node);
            }
            boolean printNull = false;
            StringBuilder stb = new StringBuilder();
            while(!curr.isEmpty()) {
                TreeNode node = curr.pop();
                if(node != null){
                    stb.append(node.val + " ");
                    printNull = true;
                }
                else {
                    if(printNull) stb.append("null ");
                }
            }
            
            sb.append(reverseWords(stb.toString()));
        }
        return sb.toString();
        
    }

    private String reverseWords(String s) {
        List<String> wordList = Arrays.asList(s.split("\\s"));
        Collections.reverse(wordList);
        return " " + String.join(" ", wordList);
    }
    public static void main(String[] args) {
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        //write your code here
        TreeNode node = new TreeNode(args);
        out.println(node);
        out.close();
    }
}