import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class Question704 { 
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
		
		int n = fr.nextInt();
		int target = fr.nextInt();
		int[] nums = new int[n];
		for(int i=0; i < n; i++) {
			nums[i] = fr.nextInt();
		}
		out.print(search(nums, target) + "\n");
		out.close();
	}

	public static int search(int[] nums, int target) {
        int left = 0;
		int right = nums.length - 1;
		int mid;
		while(left <= right) {
			mid = left + (right - left) / 2;
			if(nums[mid] ==  target) return mid;
			if(nums[mid] < target) left = mid +1;
			if(nums[mid] > target) right = mid - 1;
		}
		return -1;
    }
}




