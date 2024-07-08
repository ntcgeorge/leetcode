import java.io.PrintWriter;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.lang.Math;

/**
 * link: https://leetcode.cn/problems/partition-equal-subset-sum/
 * time: 1 hr
 * notes:
 * 1. to find the equal sum of each group is exactly the same as finding
 * the whether the nums can pick up a random subset with sum equals to half
 * of the total sum.
 */
public class Solution416 {

    public static void main(String[] args) {
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        int n = fr.nextInt();
        for(int i=0; i < n; i++) {
            Solution416 sol = new Solution416();
            out.println(sol.canPartition(fr.readIntLine(",")));
        }
        out.close();
    }

    public boolean canPartition(int[] nums) {
        //dp[i][j] = whether first i number in nums can be splitted into two groups
        // with sum of j
        int n = nums.length;
        
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }
        boolean[][] dp = new boolean[n+1][sum+1];
        if(sum % 2 != 0) return false;
        sum = sum / 2;
        for(int k=1; k <= sum; k++) {
            dp[0][k] = false;
        }
        for(int k=0; k <= n; k++) {
            dp[k][0] = true;
        }

        for(int i=1; i <= n; i++) {
            for(int j=1; j<= sum; j++) {
                if(j - nums[i-1] < 0) {
                    dp[i][j] = dp[i - 1][j];
                }
                else {
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];
                }
            }
        }

        return dp[n][sum];

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