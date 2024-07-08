import java.io.PrintWriter;
import java.util.*;

import javax.swing.text.StyledEditorKit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Math;

/**
 * link: https://leetcode.com/problems/regular-expression-matching/submissions/1270007116/
 * time: too freaking hard
 * notes:
 * 1. there are two possibilities if the pattern character is *: match 
 * the previous zero times or multiple times
 * 2. base case is very tricky, have to split into two different cases i.e i==n or j==m
 * 3. even the two current character matches(ch2 == '.' or ch1==ch2), it is still possible 
 * to mismatch if the next pattern character is '*'.
 * 4. I suggest do not consider the * state like the way algs4 used(non-finite state machine).
 * 
 * 
 */
public class Solution10 {

private final boolan DEBUG = true;

    public static void main(String[] args) {
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        int n = fr.nextInt();
        for(int i=0; i < n; i++) {
            Solution10 sol = new Solution10();
            String s = fr.next();
            String p = fr.next();
            out.println(sol.isMatch(s, p));
        }
        out.close();
    }

    int[][] memo; // if memo[i][j] = s[i...] and p[j...] match
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        
        memo = new int[n][m];
        for(int[] row : memo) {
            Arrays.fill(row, -1);
        }
        dp(0, 0, s, p);
        for(int i=0; i < n; i++) {
            System.out.println();
            for(int j=0; j < m; j++) {
                System.out.print(memo[i][j] + ",");
            }
        }
        System.out.println();
        
        return memo[0][0] == 1;
    }
    
    private boolean dp(int i, int j, String s, String p) {
        System.out.printf("Traversing:[%d, %d]\n", i,j);
        boolean res;
        // base case
        int n = s.length();
        int m = p.length();
        if(j == m) {
            return i == n;
        }

        if(i == n) {
            
            if((m - j) % 2 == 1) return false;
            for(; j+1 < m; j=j+2) {
                if(p.charAt(j+1) != '*') return false;
            }
            return true;
        }

        if(memo[i][j] != -1) return memo[i][j] == 1;

        char ch1 = s.charAt(i);
        char ch2 = p.charAt(j);

        if(ch2 == '.') {
            if(j+1 < m && p.charAt(j+1) == '*') res = dp(i+1, j+1, s, p) || dp(i, j+1, s, p);
            else res = dp(i+1, j+1, s, p);
        }else if(ch2 == '*') {
            if(ch1 == p.charAt(j-1) || p.charAt(j-1) == '.') {
                res = dp(i+1, j+1, s, p) || dp(i+1, j, s, p) || dp(i, j+1, s, p);
            }else {
                res = dp(i, j+1, s, p);
            }

        }
        else {
            if(ch1 != ch2) {
                if (j < m - 1 && p.charAt(j+1) == '*') {
                    res = dp(i, j+1, s, p);
                } else {
                    res = false;
                }
            }
            else {
                if(j+1< m && p.charAt(j+1) == '*') res = dp(i+1, j+1, s, p) || dp(i, j+1, s, p);
                else res = dp(i+1, j+1, s, p);
            }
        }
       if(res) memo[i][j] = 1;
       else {
            memo[i][j] = 0;
        }
       return res;
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