import java.io.PrintWriter;
import java.util.*;

import javax.swing.event.ListDataListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Math;

/**
 * link: https://leetcode.cn/problems/n-queens
 * time: 1hr20min
 * 
 * notes:
 * 1. each row and each column can have only one queen, because there is n
 * queen on the n by n board.
 * 2. we can use tracing-back method to list all the possible combination (row by row)
 * and exclude the invalid options(diagonal condition).
 * 3. how do we determine the diagonal condition:
 * if there is a queen on (x, y), then diagonal position(p, q) would satisfy
 * x - p == y - q or x - p == q - y
 * Pitfall:
 * 1. line 75: should not return the method if the option is invalid but to move
 * to the next position
 */
public class Solution51 {

    public static void main(String[] args) {
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        int n = fr.nextInt();
        for(int i=0; i < n; i++) {
            int size = fr.nextInt();
            Solution51 sol = new Solution51();
            out.println(sol.solveNQueens(size));
        }
        out.close();
    }
    int n;
    private static class Pair {
        int x; 
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    List<String> board;
    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        ArrayList<List<String>> res = new ArrayList<>();
        board = new ArrayList<>();
        List<Pair> prev = new ArrayList<>();
        backTrace(board, prev, res);
        return res;   
    }

    private void backTrace(List<String> board,  List<Pair> prev, List<List<String>> res) {
        if(board.size() == n) {
            res.add(new ArrayList<String>(board));
            return;
        }
        // iterate over the current row

        int row = board.size();

        // check if  previous queens make it invalid.
        for(int col=0; col < n; col++) {
            boolean flag = false;
            // System.out.println("row:" + row + "  col: " + col);
            Pair curr = new Pair(col, row);
            for(Pair pair : prev) {
                if(check(pair, curr)) {
                    flag = true;
                    break;
                }
            }
            if(flag) continue;
            prev.add(curr);
            StringBuilder sb = new StringBuilder();
            for(int i=0; i < col; i++) {
                sb.append(".");
            }
            sb.append("Q");
            for(int i=col+1; i < n; i++) {
                sb.append(".");
            }
            board.add(sb.toString());

            // calling the function
            backTrace(board, prev, res);
            board.remove(board.size() - 1);
            prev.remove(prev.size() - 1);
        }
        
    }

    private boolean check(Pair p1, Pair p2) {
        
        return p1.x - p2.x == p1.y - p2.y
        || p1.x - p2.x == p2.y - p1.y 
        || p1.x == p2.x;
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
}