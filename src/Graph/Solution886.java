import java.io.PrintWriter;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution886 {
    /**
     * https://leetcode.cn/problems/possible-bipartition/
     * time: 26min
     */
    private boolean[] marked;
    boolean [][] graph;
    boolean ans;
    boolean[] color;
    public boolean possibleBipartition(int n, int[][] dislikes) {
        color = new boolean[n];
        graph = new boolean[n][n];
        marked = new boolean[n];
        ans = true;
        for(int i=0; i < dislikes.length; i++) {
            int[] entry = dislikes[i];
            graph[entry[0]-1][entry[1]-1] = true;
            graph[entry[1]-1][entry[0]-1] = true;
        }
        for(int i = 0; i < n; i++) {
            if(!marked[i]) dfs(graph, i, true);
        }
        return ans;
    }
    
    private void dfs(boolean[][] graph, int v, boolean c) {
        if(!ans) return;
        marked[v] = true;
        color[v] = c;
        for(int w=0; w < graph[v].length; w++) {
            if(graph[v][w]) {
                if(!marked[w]) {
                    dfs(graph, w, !c);
                }else if(color[v] == color[w]) {
                    ans = false;
                    return;
                }
            }
        }
    }
    private static class FastReader { 
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
    } 

}

