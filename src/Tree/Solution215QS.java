import java.io.PrintWriter;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Quick sort version solution of question 215
 * https://leetcode.cn/problems/kth-largest-element-in-an-array/
 * time:
 */
public class Solution215QS {
    int k;
    int v;
    public static void main(String[] args) {
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        int n = fr.nextInt();
        for(int i=0; i < n; i++) {
            Solution215QS sol = new Solution215QS();
            int k = fr.nextInt();
            int[] nums = fr.readIntLine(",");
            int ans = sol.findKthLargest(nums, k);
            out.println(ans);
        }
        out.close();
    }

    public int findKthLargest(int[] nums, int k) {
        this.k = k;
        quickSort(nums, 0, nums.length - 1);
        return this.v;

    }

    private void quickSort(int[] nums, int lo, int hi) {
        if(hi<lo) return;
        int p = partition(nums, lo, hi);
        if(nums.length - p == this.k) {
            this.v = nums[p];
            return;
        }
        quickSort(nums, lo, p-1);
        quickSort(nums, p+1, hi);
    }

    private int partition(int[] nums, int lo, int hi) {
        if(lo == hi) return lo;
        int left = lo;
        int right = hi + 1;
        int w = nums[lo];
        while(true) {
            while(nums[--right] > w) if(right == lo) break;
            while(nums[++left] <= w) if(left == hi) break;
            if(right <= left) break;
            exch(nums, left, right);
        }
        exch(nums, lo, right);
        return right;
    }

    private void exch(int[] nums, int v, int w) {
        int temp = nums[v];
        nums[v] = nums[w];
        nums[w] = temp;
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

