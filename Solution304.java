import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class Solution304 { 
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
		int m, n;
		m = fr.nextInt();
		n = fr.nextInt();
		int[][] matrix = new int[m][n];
		for(int i=0; i < m; i++)
			for(int j=0; j < n; j++)
				matrix[i][j] = fr.nextInt();
		NumMatrix nm = new NumMatrix(matrix);
		int[] region = new int[4];
		for(int j=0; j < 3; j++) {
			for(int i=0; i < 4; i++) {
				region[i] = fr.nextInt();
				out.write(region[i]);
			}
			out.println(nm.sumRegion(region[0], region[1], region[2],region[3]));
		}
		
		
		out.close();
	}

	private static class NumMatrix {
		private int[][] prefix;
		public NumMatrix(int[][] matrix) {
			
			int m, n;
			m = matrix.length;
			n = matrix[0].length;
			
			prefix = new int[m+1][n+1];
			// pad the first row and column
			for(int i = 0; i < m; i++) {
				prefix[i][0] = 0;
			}
			for(int i=0; i < n; i++) {
				prefix[0][i] = 0;
			}
			for(int r=0; r < m; r++) {
				for(int c=0; c < n; c++){
					prefix[r+1][c+1] = prefix[r][c+1] + prefix[r+1][c] - prefix[r][c] + matrix[r][c];
				}
			}
	
		}
		
		public int sumRegion(int row1, int col1, int row2, int col2) {
			return prefix[row2+1][col2+1] - prefix[row2+1][col1] - prefix[row1][col2+1] + prefix[row1][col1];
		}
	}

}

