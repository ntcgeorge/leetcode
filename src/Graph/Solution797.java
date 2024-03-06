import java.io.PrintWriter;
import java.util.*;

/**
 * https://leetcode.cn/problems/all-paths-from-source-to-target/
 * time: 1hr37min
 */
public class Solution797 {
    private boolean[] marked;
    private List<List<Integer>> ans;
    private int[][] graph;
    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        Solution797 sol = new Solution797();
        int[][] graph = new int[][]{new int[]{1,2}, new int[]{3}, new int[]{3}, new int[]{}};
        List<List<Integer>> ans = sol.allPathsSourceTarget(graph);
        out.println(ans);
        out.close();
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        this.graph = graph;
        ans = new ArrayList<List<Integer>>();
        dfs(0, new LinkedList<Integer>());
        return ans;
    }

    private void dfs(int v, LinkedList<Integer> path) {
        path.add(v);
        if(v == graph.length - 1) {
            ans.add(path);
        }

        for(int w : graph[v]) {
            dfs(w, path);
        }
        path.removeLast();
        
    }


}

