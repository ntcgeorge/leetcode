import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * https://leetcode.cn/problems/insert-delete-getrandom-o1/
 * risk level: high
 */


public class Solution380 { 
	
	public static void main(String[] args) 
	{ 
		FastReader fr = new FastReader();
		PrintWriter out=new PrintWriter(System.out);
		//write your code here
		RandomizedSet rs = new RandomizedSet();
		
		out.close();
	}
	
	
	private static class RandomizedSet {
		HashMap<Integer, Integer> map;
		ArrayList<Integer> a;
		Random random;
		public RandomizedSet() {
			map = new HashMap<>();
			a = new ArrayList<>();
			random = new Random();
		}
		
		public boolean insert(int val) {
			if(map.containsKey(val)) return false;
			map.put(val, a.size());
			a.add(val);
			return true;
		}
		
		public boolean remove(int val) {
			if(!map.containsKey(val)) return false;
			int index = map.get(val);
			map.put(a.get(a.size()-1), index);
			Collections.swap(a, a.size()-1, index);
            a.remove(a.size()-1);
            map.remove(val);
			return true;
		}
		
		public int getRandom() {
			int index = random.nextInt(a.size());
			return a.get(index);
		}
	}

	private static class FastReader { 
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

    int[] readIntLine() {
        String line = nextLine();
        String[] lineSplit = line.split(" ");
        int[] intLine = new int[lineSplit.length];
        for(int j=0; j < intLine.length;j++) {
            intLine[j] = Integer.parseInt(lineSplit[j]);
        }
        return intLine;
    }
} 

}

