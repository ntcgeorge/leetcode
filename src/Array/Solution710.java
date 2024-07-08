
import java.io.PrintWriter;
import java.util.*;
/**
 * link: https://leetcode.cn/problems/random-pick-with-blacklist/
 * risk level: high
 */


public class Solution710 { 
	
	public static void main(String[] args) 
	{ 
		FastReader fr = new FastReader();
		PrintWriter out=new PrintWriter(System.out);
		//write your code here
		int n = fr.nextInt();
		int[] blacklist = fr.readIntLine();
		Solution710 solution = new Solution710(n, blacklist);
		for(int i=0; i < 100; i++) out.println(solution.pick());
		out.close();
	}
	private Random random;
	HashMap<Integer, Integer> ltr;
	int size;
	public Solution710(int n, int[] blacklist) {
		random = new Random();
		ltr = new HashMap<>();
		int m = blacklist.length;
		HashSet<Integer> set = new HashSet<>();
		size = n - m;
		for(int i=0; i < m; i++) {
			if(blacklist[i] >= size) set.add(blacklist[i]);
		}
		int left = size;
		for(int i=0; i< m; i++) {
			if(blacklist[i] < size) {
				while(set.contains(left)) {
					// find a place for you
					left++;
				}
				ltr.put(blacklist[i], left);
				set.add(left);
			}
		}
		
		
    }
    
    public int pick() {
		int index = random.nextInt(size);
		if(ltr.containsKey(index)) return ltr.get(index);
		return index;
    }
	


}

