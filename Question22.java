import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Stack;

public class Question22 {
    

    public static List<String> generateParenthesis(int n) {
        String p = "";
        ArrayList<String> ps = new ArrayList<>();
        generateParenthesis(ps, p, n);
        return ps;
    }

    private static void generateParenthesis(ArrayList<String> ps, String p, int n) {
            if(p.length() == 2 * n) {
                if(valid(p)) ps.add(p);
            }
            else {
                String newP = p + "(";
                generateParenthesis(ps, newP, n);
                newP = p + ")";
                generateParenthesis(ps, newP, n);
            }   
    }

    private static boolean valid(String parenthese) {
        Stack<Character> st = new Stack<>();
        for(int i=0; i < parenthese.length(); i++) {
            Character ch = parenthese.charAt(i);
            if(ch.equals(')')) {
                if (st.empty()) return false;
                else st.pop();
            }
            if(ch.equals('(')) {
                st.push(ch);
            }
        }
        return st.empty();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for(String p : generateParenthesis(n)) {
            System.out.print(p + " ");
        }
        scanner.close();
    }
}
