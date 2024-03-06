import java.io.PrintWriter;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://leetcode.cn/problems/sort-an-array/
 * time: 16 min
 */
public class Solution912 {
    private int[] aux;

    public static void main(String[] args) {
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        int n = fr.nextInt();
        for(int i=0; i < n; i++) {
            
            Solution912 sol = new Solution912();
            int[] nums = fr.readIntLine();
            for(int ans : sol.sortArray(nums)) {
                out.print(ans + " ");
            }
            out.println();
        }
        out.close();
    }

    public int[] sortArray(int[] nums) {
        this.aux = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void mergeSort(int[] nums, int lo, int hi) {
        if(lo == hi) return;
        int mid = lo + (hi - lo) / 2;
        
        mergeSort(nums, lo, mid);
        mergeSort(nums, mid+1, hi);

        merge(nums, lo, mid , hi);
    }

    private void merge(int[] nums, int lo, int mid, int hi) {
        
        int left = lo;
        int right = mid + 1;
        for(int i=lo; i <= hi; i++) {
            if(left > mid) {
                aux[i] = nums[right++];
            }
            else if(right > hi) {
                aux[i] = nums[left++];
            }
            else if(nums[left] > nums[right]) {
                aux[i] = nums[right++];
            }
            else {
                aux[i] = nums[left++];
            }
        }
        for(int i=lo; i <=hi; i++) {
            nums[i] = aux[i];
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

