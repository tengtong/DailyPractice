package yanFaZuiAi;

public class ReverseString {

    // 方法一：StringBuilder自带的reverse()方法
    public String solve (String str) {
        StringBuilder strBr = new StringBuilder(str);
        return strBr.reverse().toString();
    }

    // 方法二：字符数组对半交换字符
    public String solve1 (String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            swap(chars, i, chars.length - 1 - i);
        }
        return new String(chars);
    }
    public void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
