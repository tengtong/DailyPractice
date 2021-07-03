package search.backTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringFullPermutation {
    public String[] solution(String s) {
        //存放结果集
        List<String> list = new ArrayList<>();
        char[] str = s.toCharArray();
        int len = str.length;
        //先排序，后面避免重复排列
        Arrays.sort(str);
        boolean[] haused = new boolean[len];
        list = backTrack(list, new StringBuilder(), str, len, haused);
        return list.toArray(new String[list.size()]);
    }
    public List<String> backTrack(List<String> list, StringBuilder sb, char[] str, int len, boolean[] haused) {
        if (sb.length() == len) {
            list.add(sb.toString());
            return list;
        }
        for (int i = 0; i < len; i++) {
            if (haused[i]) continue;
            //重复元素则不再进行排序
            if (i != 0 && str[i] == str[i - 1] && haused[i - 1]) continue;
            haused[i] = true;
            sb.append(str[i]);
            backTrack(list, sb, str, len, haused);
            sb.deleteCharAt(sb.length() - 1);
            haused[i] = false;
        }
        return list;
    }
}
