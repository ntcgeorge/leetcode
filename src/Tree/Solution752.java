import java.io.PrintWriter;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Math;

/**
 * link: https://leetcode.cn/problems/open-the-lock/
 * time: 50min
 * notes: pay attention to the position of adding the visited node.
 */
public class Solution752 {

    public static void main(String[] args) {
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        int n = fr.nextInt();
        for(int i=0; i < n; i++) {
            String[] deadends = fr.nextLine().split(",");
            String target = fr.next();
            Solution752 sol = new Solution752();
            int ans = sol.openLock(deadends, target);
            out.println(ans);
        }
        out.close();
    }

    public int openLock(String[] deadends, String target) {
        HashSet<String> dead = new HashSet<>();
        HashSet<String> marked = new HashSet<>();
        for(String num : deadends) {
            dead.add(num);
        }
        Queue<String> q = new LinkedList<>();
        q.add("0000");
        if(dead.contains("0000")) return -1;
        int step = 0; // final answer
        //bfs
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i < size; i++) {
                String s = q.poll();
                if(marked.contains(s) || dead.contains(s)) continue;
                marked.add(s);
                if(s.equals(target)) return step;
                for(String next : adj(s)) {
                    if(dead.contains(next))continue;
                    if(!marked.contains(next)){
                        q.offer(next);
                         // avoid repeatedly visiting a node
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private Iterable<String> adj(String num) {
        int[] margin = new int[]{-1, 1};
        char[] chs = num.toCharArray();
        ArrayList<String> arr = new ArrayList<>();
        for(int i=0; i < num.length(); i++) {
            char ch = num.charAt(i);
            int x = ch - '0';
            char temp = chs[i];
            for(int m : margin) {
                int y = x + m;
                if(y > 9) y = 0;
                if(y < 0) y = 9;
                chs[i] = (char)(y + '0');
                arr.add(new String(chs));
                chs[i] = temp;
            }
        }
        return arr;
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