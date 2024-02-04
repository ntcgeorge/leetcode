import java.io.PrintWriter;
import java.util.Random;

/**
 * https://leetcode.cn/problems/random-pick-with-weight/submissions/500206248/
 * risk level: medium
 */
public class Question528 { 
	int[] preSum;
	int sum;
	private final Random random = new Random();

	public static void main(String[] args) 
	{ 
		FastReader fr = new FastReader();
		PrintWriter out=new PrintWriter(System.out);
		//write your code here
		int n = fr.nextInt();
		int[] w = new int[n];
		for(int i=0; i < n; i++) {
			w[i] = fr.nextInt();
		}
		Question528 q = new Question528(w);
		int x = 0;
		
		while(x++ < 100) {
			out.println(q.pickIndex());
		}
		
		out.close();
	}


	public Question528(int[] w) {
        preSum = new int[w.length + 1];
		preSum[0] = 0;
		for(int i=0; i < w.length; i++) {
			preSum [i+1] = preSum[i] + w[i];
		}
		sum = preSum[preSum.length - 1];
    }
    
    public int pickIndex() {
		int left = 0;
		int right = preSum.length - 1;
		int target = random.nextInt(sum);

		// find the left boundary
		while(left <= right) {
			int mid = left + (right - left) / 2;
			if(preSum[mid] == target) return mid;
			if(preSum[mid] < target) left = mid + 1;
			if(preSum[mid] > target) right = mid - 1; 
		}
		return right;
    }



}

