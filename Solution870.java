
import java.io.PrintWriter;
import java.util.*;
import java.util.Map.Entry;


public class Solution870 { 
	
	public static void main(String[] args) 
	{ 
		FastReader fr = new FastReader();
		PrintWriter out=new PrintWriter(System.out);
		//write your code here
		int n = fr.nextInt();
		for(int i=0; i < n; i++) {
			int[] nums1 = fr.readIntLine();
			int[] nums2 = fr.readIntLine();
			for(int ans : advantageCount(nums1, nums2)) {
				out.print(ans + " ");
			}
			out.print("\n\n");
		}
		out.close();
	}
	
	public static int[] advantageCount(int[] nums1, int[] nums2) {
		TreeMap<Integer, Integer> map = new TreeMap<>();
		for(int x : nums1) {
			if(map.containsKey(x)) {
				map.put(x, map.get(x) + 1);
			}
			else {
				map.put(x, 1);
			}
		}
		int[] ans = new int[nums1.length];
		for(int i=0; i<nums2.length; i++) {
			Integer ceilingKey = map.ceilingKey(nums2[i] + 1);
			if(ceilingKey == null){
				Entry<Integer, Integer> entry = map.firstEntry();
				Integer key = entry.getKey();
				Integer value = entry.getValue();
				ans[i] = key;
				if(value ==1) map.remove(key);
				else map.put(key, value - 1);
			}
			else {
				Integer value = map.get(ceilingKey);
				ans[i] = ceilingKey;
				if(value == 1) map.remove(ceilingKey);
				else map.put(ceilingKey, value -1);
			}
		}
		return ans;
		
    }

	


}

