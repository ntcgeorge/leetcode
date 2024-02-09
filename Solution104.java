import java.io.PrintWriter;


public class Solution104 {

    public static void main(String[] args) {
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        //write your code here
        int n = fr.nextInt();
        for (int i = 0; i < n; i++) {
            TreeNode root = new TreeNode(fr.nextLine().split(" "));
            out.println(maxDepth(root));
        }
        out.close();
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

}

