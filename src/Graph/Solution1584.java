import java.io.PrintWriter;
import java.lang.annotation.Inherited;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.*;

/**
 * https://leetcode.cn/problems/min-cost-to-connect-all-points/submissions/504532786/
 * time: 37min
 */
public class Solution1584 {

    public static void main(String[] args) {
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        int n = fr.nextInt();
        for(int i=0; i < n; i++) {
            int m = fr.nextInt();
            int[][] points = new int[m][2];
            for(int j=0; j < m; j++) {
                points[j] = new int[]{fr.nextInt(), fr.nextInt()};
            }
            out.println(minCostConnectPoints(points));
            
        }
        out.close();
    }
    
    public static int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int sum = 0;
        UF uf = new UF(n);
        List<Edge> edges = new ArrayList<>();
        for(int i=0; i < n; i++) {
            for(int j=i+1; j < n; j++) {
                int[] p1 = points[i];
                int[] p2 = points[j];
                double distance = Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
                edges.add(new Edge(i, j, distance));
            }
        }
        Edge[] e = (Edge[]) edges.toArray(new Edge[edges.size()]);
        
        Arrays.sort(e);
        for(int j = 0; j < e.length; j++) {
            Edge edge = e[j];
            int v = edge.either();
            int w = edge.other(v);
            if(uf.connected(v, w)) continue;
            uf.union(v, w);
            sum += edge.length();
    }
    return sum;
    }
}

class UF {
    
    int count;
    int[] arr;
    public UF (int n) {
        arr = new int[n];
        for(int i=0; i < n; i++) {
            arr[i] = i;
        }
        count = n;
    }

    public void union(int p, int q) {
        int pId = find(p);
        int qId = find(q);
        if(pId == qId) return;
        
        arr[pId] = qId;
        count--;
    }

    public boolean connected(int v, int w) {
        return find(v) == find(w);
    }

    public int find(int v) {
        while(v != arr[v]) {
            int parent = arr[v];
            arr[v] = arr[parent];
            v = parent;
        }
        return v;
    }

    public int count() {
        return count;
    }
}

class Edge implements Comparable<Edge>{
    private int v, w;
    private double distance;
    public Edge(int v, int w, double distance) {
        this.v = v;
        this.w = w;
        this.distance = distance;
    }

    public int either() {
        return this.v;
    }

    public int other(int v) {
        if(v == this.v) return this.w;
        if(v == this.w) return this.v;
        throw new IllegalArgumentException("the node is not on this edge");
    }

    public double length() {
        return distance;
    }

    @Override
    public int compareTo(Edge that) {
        return Double.compare(this.length(), that.length());
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
}
