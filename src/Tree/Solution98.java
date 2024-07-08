import java.io.PrintWriter;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * link: https://leetcode.cn/problems/validate-binary-search-tree/
 * time: 8min
 */
public class Solution98 {
    Stack<Integer> stack;
    boolean flag;
    public static void main(String[] args) {
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        int n = fr.nextInt();
        for(int i=0; i < n; i++) {
            
            
        }
        out.close();
    }

    public boolean isValidBST(TreeNode root) {
        stack = new Stack<>();
        flag = true;
        traverse(root);
        return flag;
    }

    private void traverse(TreeNode root) {
        if(root == null) return;
        traverse(root.left);
        if(!stack.isEmpty() && stack.peek() >= root.val) {
            flag = false;
            return;
        }
        stack.push(root.val);
        traverse(root.right);
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

}

