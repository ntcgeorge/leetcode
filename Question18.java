import java.util.*;

/**
 * We maintain a hash map that contains element pairs.
 * The key is their sum, and the value is the index.
 * Collision: if there is collision, we append the index Node at the bottom of
 * link list.
 */
public class Question18 {
    private class Node {
        Node next;
        Integer left;
        Integer right;

        Node(Integer left, Integer right) {
            this.left = left;
            this.right = right;
            this.next = null;
        }

        @Override
        public boolean equals(Object that) {
            if(!(that instanceof Node)) return false;
            Node node = (Node) that;
            return node.left.equals(left) && node.right.equals(right);
        }
    }
    public List<List<Integer>> fourSum(int[] nums, int target) {
        

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num1 = scanner.nextInt();
        int num2 = scanner.nextInt();
        int num3 = scanner.nextInt();
        int num4 = scanner.nextInt();
        int num5 = scanner.nextInt();
        int num6 = scanner.nextInt();

        int[] nums = new int[]{num1, num2, num3, num4, num5, num6};
        int target = scanner.nextInt();
        Question18 q = new Question18();
        List<List<Integer>> result = q.fourSum(nums, target);
        System.out.println(result);
    }
}
