import java.io.PrintWriter;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * https://leetcode.cn/problems/maximum-binary-tree/submissions/501466533/
 * risk-level: medium
 */
public class Solution654 {

    public static void main(String[] args) {
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        int n = fr.nextInt();
        for(int i=0; i < n; i++) {
            // write your code here
            out.println();
        }
        out.close();
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if(nums.length == 0) return null;
        int maxIndex = argMax(nums);
        TreeNode root = new TreeNode(nums[maxIndex]);
        int[] leftArray = new int[maxIndex];
        int[] rightArray = new int[nums.length - maxIndex - 1];
        for(int i=0; i < maxIndex; i++) {
            leftArray[i] = nums[i];
        }
        for(int i=maxIndex + 1; i < nums.length; i++) {
            rightArray[i - maxIndex - 1] = nums[i];
        }

        root.left = constructMaximumBinaryTree(leftArray);
        root.right = constructMaximumBinaryTree(rightArray);
        return root;
    }

    private int argMax(int[] nums) {
        int max = Integer.MIN_VALUE;
        int index = 0;
        for(int i=0; i < nums.length; i++) {
            if(nums[i] > max) {
                max = nums[i];
                index = i;
            }
        }
        return index;
        
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

