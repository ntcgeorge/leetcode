import java.io.PrintWriter;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * link: https://leetcode.cn/problems/kth-smallest-element-in-a-bst/submissions/501650707/
 * time: 15 min
 */
public class Solution230 {
    public static void main(String[] args) {
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        int n = fr.nextInt();
        for(int i=0; i < n; i++) {
            

            Solution230 sol = new Solution230();
            int k = fr.nextInt();
            String s = fr.nextLine();
            String[] inputs = s.split(",");
            TreeNode root = new TreeNode(inputs);
            out.println(sol.kthSmallest(root, k));
        }
        out.close();
    }

    public int kthSmallest(TreeNode root, int k) {
        Queue<Integer> ans = new LinkedList<>();
        traverse(root, ans);
        int x = 0;
        for(int i=0; i < k; i++) {
            x = ans.poll();
        }
        return x;
        
    }

    private void traverse(TreeNode root, Queue<Integer> ans) {
        if(root == null) return;

        traverse(root.left, ans);
        ans.offer(root.val);
        traverse(root.right, ans);
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

