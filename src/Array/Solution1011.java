
import java.io.PrintWriter;



/**
 * https://leetcode.cn/problems/capacity-to-ship-packages-within-d-days/
 * risk level: medium
 */
public class Solution1011 { 
	
	public static void main(String[] args) 
	{ 
		FastReader fr = new FastReader();
		PrintWriter out=new PrintWriter(System.out);
		//write your code here
		int n = fr.nextInt();
		for(int i=0; i < n; i++) {
			int days = fr.nextInt();
			int[] weights = fr.readIntLine();
			out.println(shipWithinDays(weights, days));
		}


		out.close();
	}
	
	public static int shipWithinDays(int[] weights, int days) {
		long left = 1;
		long right = 500 * 5 * 10000;
		long mid;
		while(left <= right) {
			mid = left + (right - left) / 2;
			long target = check(weights, mid);
			// excessive load
			if(target <= days) right = mid - 1;
			if(target > days) left = mid + 1;
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

