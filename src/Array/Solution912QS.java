import java.io.PrintWriter;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution912QS {

    public static void main(String[] args) {
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        int n = fr.nextInt();
        for(int i=0; i < n; i++) {
            int[] nums = fr.readIntLine();
            Solution912QS sol = new Solution912QS();
            sol.sortArray(nums);
            for(int num : nums) {
                out.print(num + " ");
            }
            out.println();
            
        }
        out.close();
    }

    public int[] sortArray(int[] nums) {
        shuffle(nums);
        quickSort(nums);
        return nums;
    }

    private void quickSort(int[] nums) {
        if(nums.length == 1) return;
        quickSort(nums, 0, nums.length - 1);

    }

    private void quickSort(int[] nums, int lo, int hi) {
        if(lo >= hi) return;
        int k = partition(nums, lo, hi);
        quickSort(nums, lo, k - 1);
        quickSort(nums, k+1, hi);
    }

    private int partition(int[] nums, int lo, int hi) {
        int k = nums[lo];
        int left = lo;
        int right = hi + 1; // the range is (left, j], [i, right).
        while(true) {
            while(nums[++left] <= k) if(left == hi) break;
            while(nums[--right] > k) if(right == lo) break;
            if(left >= right) break;
            swap(nums, left, right);
        }
        swap(nums, lo, right);
        return right;
    }

    private void swap(int[] nums, int v, int w) {
        int temp = nums[v];
        nums[v] = nums[w];
        nums[w] = temp;
    }

    public static void shuffle(int[] a) {
        Random random = new Random();
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = i + random.nextInt(n-i);     // between i and n-1
            int temp = a[i];
            a[i] = a[r];
            a[r] = temp;
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

