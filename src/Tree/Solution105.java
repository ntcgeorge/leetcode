import java.io.PrintWriter;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 */
public class Solution105 {

    public static void main(String[] args) {
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        int n = fr.nextInt();
        for(int i=0; i < n; i++) {
            // write your code here
            
        }
        out.close();
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
    }

    private static TreeNode build(int[] preorder, int pstart, int pend, int[] inorder, int instart, int inend) {
        if(pstart > pend) return null;
        int val = preorder[pstart];
        TreeNode root = new TreeNode(val);
        
        // split the array
        int index = findIndex(inorder, val);
        int leftSize = index - instart - 1;

        root.left = build(preorder, pstart + 1, pstart + leftSize, inorder, index - leftSize, index - 1);
        root.right = build(preorder, pstart + leftSize + 1, pend, inorder, index + 1, inend);
        return root;
    }

    private static int findIndex(int[] nums, int target) {
        for(int i=0; i < nums.length; i++) {
            if(nums[i] == target) return i;
        }
        return -1;
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

