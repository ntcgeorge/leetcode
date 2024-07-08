import java.io.PrintWriter;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Math;

/**
 * link: https://leetcode.cn/problems/edit-distance/submissions/521107017/
 * time: 35 min
 * notes:
 * 1. the pointer starts at the end of the string, so that removing a character only
 * need to move the pointer for a position.
 * 2. no need to care about the strategy for next step, you just have to list them all.
 */
public class Solution72 {

    public static void main(String[] args) {
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        int n = fr.nextInt();
        for(int i=0; i < n; i++) {
            Solution72 sol = new Solution72();
            String word1 = fr.next();
            String word2 = fr.next();
            out.println(sol.minDistance(word1, word2));
        }
        out.close();
    }
    
    int[][] memo;
    public int minDistance(String word1, String word2) {
        memo = new int[word1.length()][word2.length()];
        for(int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dp(word1, word1.length() - 1, word2, word2.length() - 1);
    }
    // return smallest edit distance for string word1[0: index1], word2[0: index2]
    private int dp(String word1, int index1, String word2, int index2) {
        if(index1 < 0) return index2 + 1;
        if(index2 < 0) return index1 + 1;

        char ch1 = word1.charAt(index1);
        char ch2 = word2.charAt(index2);
        if(memo[index1][index2] != -1) return memo[index1][index2];
        
        if(ch1 == ch2) {
            memo[index1][index2] = dp(word1, index1-1, word2, index2-1);
        }
        else {
            memo[index1][index2] = min(
                dp(word1, index1, word2, index2 - 1) + 1,
                dp(word1, index1 - 1, word2, index2) + 1,
                dp(word1, index1 -1 , word2, index2 - 1) + 1
            );
        }
        return memo[index1][index2];
        
    }
    
    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
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