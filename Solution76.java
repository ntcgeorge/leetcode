package george.SlidingWindow;
// Working program with FastReader

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * https://leetcode.cn/problems/minimum-window-substring/
 */

public class Solution76 {


    public static void main(String[] args) {
        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);

        // write your code here
        String s = fr.next();
        String t = fr.next();
        out.println(minWindow(s, t));

        out.flush();
    }

    public static String minWindow(String s, String t) {
        int left = 0;
        int right = 0;
        int need = 0;
        String champion = new String(s);
        HashMap<Character, Integer> maps = new HashMap<>();
        HashMap<Character, Integer> mapt = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            Character ch = t.charAt(i);
            if (mapt.containsKey(ch)) {
                mapt.put(ch, mapt.get(ch) + 1);
            } else {
                mapt.put(t.charAt(i), 1);
            }
            maps.put(ch, 0);

        }

        while (right < s.length()) {

            // expand the window
            while (need < t.length() && right < s.length()) {
                right++;
                Character ch = s.charAt(right - 1);
                if (maps.containsKey(ch)) {
                    maps.put(ch, maps.get(ch) + 1);
                    if(mapt.get(ch) >= maps.get(ch)) need++;
                }
            }
            // shrink the window
            while (need == t.length() && left < right) {
                Character c = s.charAt(left);
                if(mapt.containsKey(c)) {
                    maps.put(c, maps.get(c) - 1);
                    if(maps.get(c) < mapt.get(c)) need--;
                    if(right - left <= champion.length()) champion = s.substring(left, right);
                }
                left++;
            }
            
        }
        if(left == 0) return "";
        else return champion;
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
