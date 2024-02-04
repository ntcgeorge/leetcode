import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/
 * risk: high
 */
public class Question34 { 
	static class FastReader { 
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
	} 

	public static void main(String[] args) 
	{ 
		FastReader fr = new FastReader();
		PrintWriter out=new PrintWriter(System.out);
		// write your code here
		int n = fr.nextInt();
		int[] nums = new int[n];
		int target = fr.nextInt();
		for(int i=0; i < n; i++) {
			nums[i] = fr.nextInt();
		}
		for(int num : searchRange(nums, target)) {
			out.print(num + " ");
		}
		out.print("\b\n");
		out.close();
	}


	public static int[] searchRange(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;
		int[] ans = new int[2];
		while(left <= right) {
			int mid = left + (right - left) / 2;
			if(nums[mid] < target) left = mid + 1;
			if(nums[mid] == target) right = mid - 1;
			if(nums[mid] > target) right = mid - 1;
		}
		if(left > nums.length - 1 || nums[left] != target) ans[0] = -1;
		else ans[0] = left;

		left = 0;
		right = nums.length - 1;
		while(left <= right) {
			int mid = left + (right - left) / 2;
			if(nums[mid] > target) right = mid - 1;
			if(nums[mid] == target) left = mid + 1;
			if(nums[mid] < target) left =  mid + 1;
		}
		
		if(right < 0 || nums[right] != target) ans[1] = -1;
		else ans[1] = right;
		return ans;
    }

}

