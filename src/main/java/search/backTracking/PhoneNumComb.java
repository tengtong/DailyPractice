package search.backTracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 回溯，是一种特殊的DFS
 * 普通的dfs：主要是用在"可达性问题"，这种问题只需要执行到特定的位置然后返回即可
 * backtracking：主要用与求解"排列组合问题"，需要求解出所有符合条件的结果，这种问题在执行到特殊位置返回之后还会继续执行求解过程
 *
 * 因为回溯不是立即返回，会继续执行，因此需要注意元素标记的问题
 * 在访问一个新元素进入新的递归调用时，需要将新元素标记为已经访问，这样才能在继续递归调用时不用重复访问该元素//prefix//tempAdress
 * 但是在递归返回时，需要将元素标记为未访问，因为只需要保证在一个递归链中不同时一个元素，可以访问已经访问过但是不在当前递归链中的元素//delete操作
 */

/**
 * backTracking
 *
 * public mainFunction{
 *     变量初始化：adresses/combinations结果集、tempSet/tempAdress/prefix临时集（临时集！=子集，多次递归共同完成一个子集）、s/digit、（标志集）
 *     特殊情况判断
 *     dofunction
 *     return 结果集；
 * }
 *
 * private doFunction{
 *     递归出口；
 *     临时变量初始化；
 *     for() {//第一层循环//abc中的a//255.210.210.10中的255
 *         tempSet.append
 *         doFuntion();
 *         tempSet.delete
 *     }
 * }
 */

/**
 * 数字键盘组合
 * Input: Digit String "23"
 * Outpus: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * 提示：数字键盘'2','3'分别"abc","def"，即求"abc"和"def"的所有排列可能
 */
public class PhoneNumComb {
    private static final String[] KEYS = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

    public static List<String> solution(String dights){
        List<String> combinations = new ArrayList<>();//combinations结果集
        StringBuilder tempCombine = new StringBuilder();
        if (dights == null || dights.length() == 0){
            return combinations;
        }
        doCombination(tempCombine,combinations,dights);
        return combinations;
    }
    private static void doCombination(StringBuilder tempCombine, List<String> combinations, final String digits){
        if (tempCombine.length() == digits.length()){//进行一个条件判断
            combinations.add(tempCombine.toString());
            return;
        }
        //因为这里顺序是固定，故按顺序来排列即可，只有ad，没有da，故没有设置标志集
        int curDigits = digits.charAt(tempCombine.length()) - '0';//得到当前的数字//2 = '2'-'0'
        String letters = KEYS[curDigits];//通过KEYS取出数字'2'的字符串"abc"
        for (char c:letters.toCharArray()){//遍历"abc"中的每个字符//for循环进行回溯，如果剪枝也写在里面
            tempCombine.append(c);                       //添加
            doCombination(tempCombine,combinations,digits);
            tempCombine.deleteCharAt(tempCombine.length() - 1);//删除
        }
    }

    public static void main(String[] args) {
        String s = "23";
        List<String> list = new ArrayList<>();
        list = PhoneNumComb.solution(s);
        System.out.println(list.toString());
    }
}
