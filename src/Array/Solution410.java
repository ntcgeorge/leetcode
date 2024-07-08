
import java.io.PrintWriter;

/**
 * link: https://leetcode.cn/problems/split-array-largest-sum/description/
 * risk level: same to Solution1011 because it is the same as that one
 */

public class Solution410 { 
	
	public static void main(String[] args) 
	{ 
		FastReader fr = new FastReader();
		PrintWriter out=new PrintWriter(System.out);
		//write your code here
		int n = fr.nextInt();
		for(int i=0; i < n; i++) {
			int days = fr.nextInt();
			int[] weights = fr.readIntLine();
			out.println(splitArray(weights, days));
			out.println();
		}
		out.close();
	}

	public static int splitArray(int[] nums, int k) {
        long left = 0;
		long right = 1000000000;
		long mid;
		while(left <= right) {
			mid = left + (right - left) / 2;
			long target = check(nums, mid);
			// excessive load
			if(target <= k) right = mid - 1;
			if(target > k) left = mid + 1;
		}
		return (int)left;
    }

    public static long check(int[] weights, long load) {
		long sum = 0;
		int count = 0;
		for(int weight : weights) {
			if(weight > load) return Long.MAX_VALUE;
			sum += weight;
            if(sum == load) {
                count ++;
                sum = 0;
            }
			if(sum > load){
				count ++;
				sum = weight;
			}
		}
		if(sum > 0) count++;
		return count;
	}
	
	


}

