package dailypractice.m07;

import java.util.*;

/**
 * 726. 原子的数量
 * @author tengtong
 */
public class M0705 {
    /**
     * 求一个化学式的原子数量
     * 模拟法，()2，遇到括号记录里面的字符串
     * @param formula
     * @return map.toString
     * 通过率：6/31，这里还未考虑括号的情况
     */
    public String countOfAtoms(String formula) {
        char[] chars = formula.toCharArray();
        int n = chars.length;
        Map<Character, Integer> map = new MyselfHashMap();
        int i = 0;
        while (i < n) {
            if (isAtom(chars[i])) {
                char curAtom = chars[i];
                int j = i + 1;
                int curNum = 0;
                while (j < n && isNum(chars[j])) {
                    curNum = chars[j] - '0' + curNum * 10;
                    j++;
                }
                curNum = curNum == 0 ? 1 : curNum;
                i = j - 1;
                map.put(curAtom, curNum);
            }
            i++;
        }
        return map.toString();
    }
    public boolean isAtom (char letter) {
        if ('A' <= letter && letter <= 'Z') {
            return true;
        } else {
            return false;
        }
    }
    public boolean isNum (char letter) {
        if ('0' <= letter && letter <= '9') {
            return true;
        } else {
            return false;
        }
    }
    class MyselfHashMap<K,T> extends HashMap<K,T> {
        @Override
        public String toString() {
            String ans = "";
            for (Map.Entry<K,T> entry : entrySet()) {
                if (!"1".equals(entry.getValue().toString())) {
                    ans += entry.getKey().toString() + entry.getValue().toString();
                } else {
                    ans += entry.getKey().toString();
                }
            }
            return ans;
        }
    }

    /**
     * 方法二：栈 + 哈希表
     * @param args
     */
    int i, n;
    String formula;

    public String countOfAtoms1(String formula) {
        this.i = 0;
        this.n = formula.length();
        this.formula = formula;
        Deque<Map<String, Integer>> stack = new LinkedList<Map<String, Integer>>();
        stack.push(new HashMap<String, Integer>());
        while (i < n) {
            char ch = formula.charAt(i);
            if (ch == '(') {
                i++;
                stack.push(new HashMap<String, Integer>()); // 将一个空的哈希表压入栈中，准备统计括号内的原子数量
            } else if (ch == ')') {
                i++;
                int num = parseNum(); // 括号右侧数字
                Map<String, Integer> popMap = stack.pop(); // 弹出括号内的原子数量
                Map<String, Integer> topMap = stack.peek();
                for (Map.Entry<String, Integer> entry : popMap.entrySet()) {
                    String atom = entry.getKey();
                    int v = entry.getValue();
                    topMap.put(atom, topMap.getOrDefault(atom, 0) + v * num); // 将括号内的原子数量乘上 num，加到上一层的原子数量中
                }
            } else {
                String atom = parseAtom();
                int num = parseNum();
                Map<String, Integer> topMap = stack.peek();
                topMap.put(atom, topMap.getOrDefault(atom, 0) + num); // 统计原子数量
            }
        }
        Map<String, Integer> map = stack.pop();
        TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>(map);
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
            String atom = entry.getKey();
            int count = entry.getValue();
            sb.append(atom);
            if (count > 1) {
                sb.append(count);
            }
        }
        return sb.toString();
    }
    public String parseAtom() {
        StringBuffer sb = new StringBuffer();
        sb.append(formula.charAt(i++)); // 扫描首字母
        while (i < n && Character.isLowerCase(formula.charAt(i))) {
            sb.append(formula.charAt(i++)); // 扫描首字母后的小写字母
        }
        return sb.toString();
    }
    public int parseNum() {
        if (i == n || !Character.isDigit(formula.charAt(i))) {
            return 1; // 不是数字，视作 1
        }
        int num = 0;
        while (i < n && Character.isDigit(formula.charAt(i))) {
            num = num * 10 + formula.charAt(i++) - '0'; // 扫描数字
        }
        return num;
    }

    public static void main(String[] args) {
        M0705 test = new M0705();
        String formula = "H2O";
        System.out.println(test.countOfAtoms(formula));
    }
}
