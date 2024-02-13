import java.io.PrintWriter;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution315 {
    HashMap<Integer, TreeNode> indexToNode;
    public static void main(String[] args) {
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        int n = fr.nextInt();
        for(int i=0; i < n; i++) {
            // write your code here
            Solution315 sol = new Solution315();
            int[] nums = fr.readIntLine();
            List<Integer> ans = sol.countSmaller(nums);
            out.println(ans);
        }
        out.close();
    }
    public List<Integer> countSmaller(int[] nums) {
        indexToNode = new HashMap<>();
        TreeNode root = null;
        for(int i=0; i < nums.length; i++) {
            root = insert(root, nums[i], i);
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i=0; i < nums.length; i++) {
            TreeNode node = indexToNode.get(i);
            ans.add(leftSize(node.left));
        }
        return ans;
    }

    private TreeNode insert(TreeNode root, int val, int index) {
        if(root == null) {
            root = new TreeNode(val);
            indexToNode.put(index, root);
            return root;
        }
        if(val < root.val) {
            root.left = insert(root.left, val, index);
        }
        else{
            root.right = insert(root.right, val, index);
        }
        return root;
    }

    private int leftSize(TreeNode root) {
        if(root == null) return 0;
        return leftSize(root.left) + 1;
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

