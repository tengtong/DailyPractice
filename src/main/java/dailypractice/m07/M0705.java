package dailypractice.m07;

import java.util.HashMap;
import java.util.Map;

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
     * 通过率：6/31
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

    public static void main(String[] args) {
        M0705 test = new M0705();
        String formula = "H2O";
        System.out.println(test.countOfAtoms(formula));
    }
}
