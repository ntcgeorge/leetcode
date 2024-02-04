
import java.io.PrintWriter;

/**
 * 
 */

public class Solution875 { 
	
	public static void main(String[] args) 
	{ 
		FastReader fr = new FastReader();
		PrintWriter out=new PrintWriter(System.out);
		//write your code here
		int n = fr.nextInt();
		int h = fr.nextInt();
		int[] piles = new int[n];
		for(int i=0; i < n; i++){
			piles[i] = fr.nextInt();
		}
		out.print(minEatingSpeed(piles, h) + "\n");
		out.close();
	}
	

	public static int minEatingSpeed(int[] piles, int h) {
		int left = 1;
		int right = 1000000000;
		while(left <= right) {
			int mid = left + (right - left) / 2;
			long target = check(piles, mid);
			// eating too slow
			if(target > h) left = mid + 1;
			// eating too fast
			if(target < h) right = mid - 1;
			if(target == h) right = mid - 1;
		}
		return left;
    }
	
	public static long check(int[] piles, int x) {
		long hours = 0;
		for (int i = 0; i < piles.length; i++) {
			hours += piles[i] / x;
			if (piles[i] % x > 0) {
				hours++;
			}
		}
		return hours;
	}



}

