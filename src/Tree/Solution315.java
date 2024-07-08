import java.io.PrintWriter;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * link: https://leetcode.cn/problems/count-of-smaller-numbers-after-self/submissions/502472929/
 * time: 56min
 * notes:
 * 1. the number of entry greater than the current number is accumulated, that is
 * because each operation only happens once on a slice of the array.
 * 2. use inner class pair to store the position and value is pretty handy, that is because
 * the class can provide all the information when doing the merging.
 * 3. need to update count when count[left] == count[right], because when that holds, left is greater
 * than all the left entry of right pointer.
 * */
public class Solution315 {

    private Pair[] aux; // auxillary array
    private int[] count;
    public static void main(String[] args) {
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        int n = fr.nextInt();
        for(int i=0; i < n; i++) {
            Solution315 sol = new Solution315();
            int[] nums = fr.readIntLine(",");
            List<Integer> ans = sol.countSmaller(nums);
            out.println(ans);
        }
        out.close();
    }

    private static class Pair {
        int index;
        int val;

        Pair (int index, int val) {
            this.index = index;
            this.val = val;
        }
    } 

    public List<Integer> countSmaller(int[] nums) {
        aux = new Pair[nums.length];
        Pair[] arr = new Pair[nums.length];
        for(int i=0; i < nums.length; i++) {
            arr[i] = new Pair(i, nums[i]);
        }
        count = new int[nums.length];
        sort(arr, 0, arr.length - 1);
        List<Integer> ans = new ArrayList<>();
        for(int x : count) {  
            ans.add(x);
        }
        return ans;
    }

    private void sort(Pair[] arr, int lo, int hi) {
        if(hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(arr, lo, mid);
        sort(arr, mid+1, hi);
        merge(arr, lo, mid, hi);
    }

    private void merge(Pair[] arr, int lo, int mid, int hi) {
        
        for(int i=lo; i <= hi; i++) {
            aux[i] = arr[i];
        }
        int left = lo;
        int right = mid + 1;
        for(int i=lo; i <=hi; i++) {
            if(left > mid) {
                arr[i] = aux[right++];
            } 
            else if(right > hi) {
                arr[i] = aux[left++];
                count[arr[i].index] += right - mid - 1;
            }
            else if(aux[left].val > aux[right].val) {
                arr[i] = aux[right++];
            }
            else {
                arr[i] = aux[left++];
                count[arr[i].index] += right - mid - 1;
            }
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

