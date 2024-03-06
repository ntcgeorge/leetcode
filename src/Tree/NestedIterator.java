import java.io.PrintWriter;
import java.util.* ;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * https://leetcode.cn/problems/flatten-nested-list-iterator/
 * time: 22 min
 */  


public class NestedIterator implements Iterator<Integer>{

    NestedIterator[] arr;
    int pos;
    Integer val;

    public NestedIterator(List<NestedInteger> nestedList) {
        if(nestedList == null) return;
        arr = new NestedIterator[nestedList.size()];
        int i=0;
        for(NestedInteger ni: nestedList) {
            arr[i] = new NestedIterator(ni.getList());
            if(ni.isInteger()) {
                arr[i].val = ni.getInteger();
            }
            i++;
        }
        pos = 0;
    }


    @Override
    public Integer next() {
        NestedIterator curr = arr[pos];
        if(curr.val == null) {
            pos++;
            return curr.val;
        }
        else {
            if(curr.hasNext()) return curr.next();
            return null;
        }
        
    }

    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        List<NestedInteger> nestedList= new ArrayList<>();
    }

    @Override
    public boolean hasNext() {
        return pos == arr.length;
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

