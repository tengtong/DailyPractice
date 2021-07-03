package DailyPractice.M06;

import java.util.*;

// 剑指 Offer 38. 字符串的排列
// abc
public class M0622 {

    // 普通 DFS 主要用在可达性问题，这种问题只需要执行到特点的位置然后返回即可。
    // 而 Backtracking 主要用于求解排列组合问题，例如有 { 'a','b','c' } 三个字符，求解所有由这三个字符排列得到的字符串，这种问题在执行到特定的位置返回之后还会继续执行求解过程。
    // 因为 Backtracking 不是立即返回，而要继续求解，因此在程序实现时，需要注意对元素的标记问题：
    //   在访问一个新元素进入新的递归调用时，需要将新元素标记为已经访问，这样才能在继续递归调用时不用重复访问该元素；
    //   即在当前递归链中，将已经访问过的元素进行标记，保证不会重复访问
    //   但是在递归返回时，需要将元素标记为未访问，因为只需要保证在一个递归链中不同时访问一个元素，可以访问已经访问过但是不在当前递归链中的元素。
    //   即回溯到上一个元素时，访问过但是不在新的递归链中的元素需要重新标记为未访问状态

    // 方法一：回溯
    // 待修改
    public String[] permutation(String s) {
        List<String> combinations = new LinkedList<>();
        if (s == null || s.length() == 0) {
            return new String[]{};
        }
        doCombination(new StringBuilder(), combinations, s);
        String[] res = combinations.stream().toArray(size -> new String[size]);
        return res;
    }
    /**
     *
     * @param prefix 记录当前的一种排列组合
     * @param combinations 结果集，只要负责记录其中一个结果即可，具体的拼接过程由prefix来完成
     * @param str 初始条件
     */
    private void doCombination(StringBuilder prefix, List<String> combinations, String str) {
        // 如果前缀字符串长度和总的相同，表示都处理完了，直接将当前结果存入即可
        if (prefix.length() == str.length()) {
            combinations.add(prefix.toString());
            return;
        }
        // 然后依次枚举，'a'、''b'、'c'
        for (char c : str.toCharArray()) {
            // 添加
            prefix.append(c);
            // 将当前结果存入到前缀结果中，然后依次对后续结果进行相同的操作，直至枚举完毕
            doCombination(prefix, combinations, str);
            // 删除
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    // 方法二：回溯
    List<String> rec;
    // 类同打印矩阵，用一个辅助矩阵来记录已经访问过的位置
    boolean[] vis;
    public String[] permutation1(String s) {
        int n = s.length();
        rec = new ArrayList<String>();
        vis = new boolean[n];
        char[] arr = s.toCharArray();
        // 排完序之后，保证有相同的字符也不会重复
        Arrays.sort(arr);
        StringBuffer perm = new StringBuffer();
        backtrack(arr, 0, n, perm);
        int size = rec.size();
        String[] recArr = new String[size];
        for (int i = 0; i < size; i++) {
            recArr[i] = rec.get(i);
        }
        return recArr;
    }
    public void backtrack(char[] arr, int i, int n, StringBuffer perm) {
        if (i == n) {
            rec.add(perm.toString());
            return;
        }
        for (int j = 0; j < n; j++) {
            if (vis[j] || (j > 0 && !vis[j - 1] && arr[j - 1] == arr[j])) {
                continue;
            }
            vis[j] = true;
            perm.append(arr[j]);
            backtrack(arr, i + 1, n, perm);
            perm.deleteCharAt(perm.length() - 1);
            vis[j] = false;
        }
    }

    // 方法三：既然是将全部可能搜索出来，那么bfs和dfs也可以咯
}
