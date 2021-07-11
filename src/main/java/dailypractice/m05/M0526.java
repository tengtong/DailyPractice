package dailypractice.m05;

import javafx.util.Pair;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

// 1190. 反转每对括号间的子串
// 默认：括号的顺序是对的，且括号内的元素个数是对称的
public class M0526 {

    // 方法一：括号，栈
    // 思路：记录括号的index，然后对括号内的字符串进行翻转
    // 优化方向：1、遍历的同时，就可以剔除括号
    //         2、偶数个括号内的字符不需要翻转，这里进行了重复翻转
    public String reverseParentheses(String s) {
        // 利用栈统计左右括号，并统计他们的index，Pair<Character, Integer>
        Stack<Pair<Character, Integer>> bracketStack = new Stack();
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                bracketStack.push(new Pair('(', i));
            }
            if (s.charAt(i) == ')') {
                start = bracketStack.pop().getValue() + 1;
                end = i - 1;
                s = reverseString(s, start, end);
            }
        }
        // 对结果s剔除'(',')'
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            if (curChar == '(' || curChar == ')') {
                continue;
            }
            ret.append(curChar);
        }
        return ret.toString();
    }
    // 翻转指定范围的字符串
    public String reverseString(String s, int start, int end) {
        int mid = (start + end) / 2;
        char[] chars = s.toCharArray();
        for (int i = start; i <= mid; i++) {
            char temp = chars[i];
            chars[i] = chars[start + end - i];
            chars[start + end - i] = temp;
        }
        return new String(chars);
    }

    // 方法二：使用栈，遇到括号处理一次
    // 细节：类同我的初始思路，用栈里存栈，但这里对一个栈内翻转直接用sb.reverse()实现了，故不需要栈内栈
    //      主要思路就是遇到 ')' 就对一个括号内的字符串sb进行翻转，然后返回上一层
    //      将栈中的字符串，即 ( ( 两个括号内字符串拼接上，再继续向后遍历，遇到 ) 进行同样的操作
    public String reverseParentheses1(String s) {
        // 双端队列实现的栈
        Deque<String> stack = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                // 遇到 ( ，将 ( ( 两个括号之间的字符串存入栈中，跳入下一层，待右边的 ( 遇到 ) ，完成翻转再接入
                stack.push(sb.toString());
                sb.setLength(0);// 清空
            } else if (s.charAt(i) == ')') {
                // 遇到 ) ，对当前的sb进行翻转，同时跳到上一层，接上pop的字符串
                sb.reverse();
                sb.insert(0, stack.pop());
            } else {
                // 遇到的不是括号，是字符，直接拼接到sb中
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        M0526 test = new M0526();
        String s = "(u(love)i)";
        String ret = test.reverseParentheses1(s);
        System.out.println(ret);
    }
}
