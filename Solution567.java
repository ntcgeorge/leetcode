// Working program with FastReader

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution567 {


    public static void main(String[] args) {
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);

        // write your code here
        String s1 = fr.next();
        String s2 = fr.next();
        out.println(checkInclusion(s1, s2));

        out.flush();
    }


    public static boolean checkInclusion(String s1, String s2) {
        if (s2.length() < s1.length()) return false;
        int need = 0;
        HashMap<Character, Integer> window = new HashMap<>();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            Character ch = s1.charAt(i);
            if (window.containsKey(ch)) window.put(ch, window.get(ch) + 1);
            else window.put(ch, 1);
            map.put(ch, 0);
        }

        int right = 0;
        int left = 0;

        while (right < s1.length()) {
            Character ch = s2.charAt(right);
            if (window.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
                if (map.get(ch) <= window.get(ch)) need++;
            }
            right++;
        }

        while (need < s1.length() && right < s2.length()) {

            // read in the right-most character
            Character rch = s2.charAt(right);
            if (window.containsKey(rch)) {
                map.put(rch, map.get(rch) + 1);
                if (map.get(rch) <= window.get(rch)) need++;
            }
            right++;


            // pop out the left-most character
            Character lch = s2.charAt(left);
            if (window.containsKey(lch)) {
                if (map.get(lch) <= window.get(lch)) need--;
                map.put(lch, map.get(lch) - 1);
            }
            left++;// move the left pointe

        }
        return need == s1.length();

    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(
                    new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                if (st.hasMoreTokens()) {
                    str = st.nextToken("\n");
                } else {
                    str = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
