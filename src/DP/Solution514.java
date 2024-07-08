import java.io.PrintWriter;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Math;

/**
 * link: https://leetcode.cn/problems/freedom-trail/
 * time: 1hr fail
 * note: the next transition state can be not physically adjacent to the current state.
 */
public class Solution514 {
    HashMap<Character, LinkedList<Integer>> map;
    public static void main(String[] args) {
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        int n = fr.nextInt();
        for(int i=0; i < n; i++) {
            Solution514 sol = new Solution514();
            int result = sol.findRotateSteps(fr.next(), fr.next());
            out.println(result);
        }
        out.close();
    }
    int[][] memo; // memo[i][j] = the min step at ith character in ring and j-th in key
    public int findRotateSteps(String ring, String key) {
        int m = ring.length();
        int n = key.length();
        memo = new int[m][n+1];
        map = new HashMap<>();
        char[] chs = ring.toCharArray();
        for(int i=0; i < chs.length; i++) {
            if(!map.containsKey(chs[i])) {
                map.put(chs[i], new LinkedList<>());
                map.get(chs[i]).add(i);
            }
            else{
                map.get(chs[i]).add(i);
            }
        }
        for(int[] row : memo) {
            Arrays.fill(row, -1);
        }
        // base case
        for(int i=0; i < m; i++) {
            memo[i][n] = 0;
        }
        return dp(ring, key, 0, 0);

    }

    private int dp(String ring, String key, int i, int j) {
        if(i == ring.length()) i = 0;
        if(i < 0) i = ring.length() - 1;
        if(memo[i][j] != -1) return memo[i][j];

        char k = key.charAt(j);

        int n = ring.length();
        int champ = Integer.MAX_VALUE;
        for(Integer x : map.get(k)) {
            int delta = Math.abs(x - i);
            delta = Math.min(delta, n - delta);
            int dist = dp(ring, key, x, j+1);
            champ = Math.min(champ, 1 + delta + dist);
        }

        memo[i][j] = champ;
        return champ;
        
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