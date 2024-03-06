import java.io.PrintWriter;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution493 {

    int count;


    public static void main(String[] args) {
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        int n = fr.nextInt();
        for(int i=0; i < n; i++) {
            Solution493 sol = new Solution493();
            int[] nums = fr.readIntLine(",");
            int ans = sol.reversePairs(nums);
            out.println(ans);
        }
        out.close();
    }

    public int reversePairs(int[] nums) {
        int count = 0;
        for(int i=0; i < nums.length; i++) {
            double target = ((double)nums[i]) / 2.0;
            
            int cnt = search(nums, i+1, nums.length-1, target);
            System.out.println("count: " + cnt);
            count += cnt;
        }
        return count;
    }
    
    // find the minimum number in the range that is greater than target
    private int search(int[] nums, int lo, int hi, double target) {
        int[] arr = new int[hi - lo + 1];
        for(int i=lo; i <= hi; i++) {
            arr[i - lo] = nums[i];
        }
        Arrays.sort(arr);
        int left = 0;
        int right = arr.length - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if((double)arr[mid] > target) {
                right = mid - 1;
            }
            if((double)arr[mid] == target) {
                right = mid - 1;
            }
            if((double)arr[mid] < target) {
                left = mid + 1;
            }
            
        }
        if(right < 0 || (double)arr[right] < target) return 0;
        return right;
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
    
        int[] readIntLine(String d) {
            String line = nextLine();
            String[] lineSplit = line.split(d);
            int[] intLine = new int[lineSplit.length];
            for(int j=0; j < intLine.length;j++) {
                intLine[j] = Integer.parseInt(lineSplit[j]);
            }
            return intLine;
        }
    } 

}

