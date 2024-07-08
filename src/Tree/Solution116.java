import java.io.PrintWriter;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.w3c.dom.Node;

/**
 * link: https://leetcode.cn/problems/populating-next-right-pointers-in-each-node/description/
 * risk-level: low
 */
public class Solution116 {

    public static void main(String[] args) {
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        int n = fr.nextInt();
        for(int i=0; i < n; i++) {
            
            
        }
        out.close();
    }

    public static Node connect(Node root) {
        if(root == null) return null;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i<size; i++) {
                Node node = q.poll();
                if(i+1 <size) {
                    node.next = q.peek();
                }
                if(node.left != null) q.offer(node.left);
                if(node.right != null) q.offer(node.right);
            }
        }
        return root;
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
    
        int[] readIntLine() {
            String line = nextLine();
            String[] lineSplit = line.split(" ");
            int[] intLine = new int[lineSplit.length];
            for(int j=0; j < intLine.length;j++) {
                intLine[j] = Integer.parseInt(lineSplit[j]);
            }
            return intLine;
        }
    } 
    
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;
    
        public Node() {}
        
        public Node(int _val) {
            val = _val;
        }
    
        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };


    
    
}

