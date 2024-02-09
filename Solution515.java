import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution515 {
    public static void main(String[] args) {
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        //write your code here
        int n = fr.nextInt();
        for (int i = 0; i < n; i++) {
            String[] inputs = fr.nextLine().split(",");
            TreeNode root = new TreeNode(inputs);
            out.println(largestValues(root));
        }
        out.close();
    }

    public static List<Integer> largestValues(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<TreeNode> curr = new LinkedList<>();
        curr.add(root);
        ans.add(root.val);

        while (!curr.isEmpty()) {
            int size = curr.size();
            int max = Integer.MIN_VALUE;
            boolean flag = false;
            for (int i = 0; i < size; i++) {
                TreeNode node = curr.poll();

                TreeNode left = node.left;
                if (left != null && left.val > max) max = left.val;
                if (left != null) {
                    curr.offer(left);
                    flag = true;
                }

                TreeNode right = node.right;
                if (right != null && right.val > max) max = right.val;
                if (right != null) {
                    curr.offer(right);
                    flag = true;
                }
            }
            if (flag) ans.add(max);
        }
        return ans;
    }
}
