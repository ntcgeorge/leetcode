import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * https://leetcode.cn/problems/remove-duplicate-letters/submissions/500809687/
 * risk-level: super high
 */

public class Solution316 {

    public static void main(String[] args) {

        FastReader fr = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        //write your code here
        int n = fr.nextInt();
        for (int i = 0; i < n; i++) {
            String s = fr.nextLine();
            out.println(removeDuplicateLetters(s));
        }
        out.close();

    }


    public static String removeDuplicateLetters(String s) {
        int[] count = new int[256];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]++;
        }
        Stack<Character> stack = new Stack<>();
        boolean[] inStack = new boolean[256];
        for (char ch : s.toCharArray()) {
            count[ch]--;
            if (inStack[ch]) continue;
            inStack[ch] = true;
            // pop as much as possible
            while (!stack.isEmpty() && stack.peek() > ch) {
                if (count[stack.peek()] == 0) {
                    break;
                }
                // 若之后还有，则可以 pop
                inStack[stack.pop()] = false;
            }
            stack.push(ch);
            inStack[ch] = true;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.empty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();

    }

    private static class FastReader {
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

        int[] readIntLine() {
            String line = nextLine();
            String[] lineSplit = line.split(" ");
            int[] intLine = new int[lineSplit.length];
            for (int j = 0; j < intLine.length; j++) {
                intLine[j] = Integer.parseInt(lineSplit[j]);
            }
            return intLine;
        }
    }
}

