import java.io.PrintWriter;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/
 * risk-level: high
 */
public class Solution114 {

    public static void main(String[] args) {
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        int n = fr.nextInt();
        for(int i=0; i < n; i++) {
            // write your code here
            
        }
        out.close();
    }

    public static void flatten(TreeNode root) {
        if(root == null) return;
        flatten(root.left);
        flatten(root.right);
        // flip
        TreeNode left = root.left; 
        TreeNode right = root.right;
    
        root.left = null;
        root.right = left;
        TreeNode head = root;
        while(head.right != null) {
            head = head.right;
        }
        head.right = right;


        
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


    private static class TreeNode {
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

    }

}

