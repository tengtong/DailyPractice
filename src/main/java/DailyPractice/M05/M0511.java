package DailyPractice.M05;

import java.util.Arrays;

// 1734. 解码异或后的排列
// 思路：1、返回数组perm，它是前 n 个正整数的排列，且 n 是个 奇数
//      2、ABCDE = A ^ (BC^DE)
public class M0511 {
    public int[] decode(int[] encoded) {
        int n = encoded.length + 1;
        int[] perm = new int[n];
        // 获取的ABCDE
        int abcde = 0;
        for (int i = 1; i <= n; i++) {
            abcde ^= i;
        }
        // 获取BCDE// encoded = {AB、BC、CD、DE}
        int bcde = 0;
        for (int i = 1; i < encoded.length; i+=2) {
            bcde ^= encoded[i];
        }
        int a = abcde ^ bcde;
        perm[0] = a;
        for (int i = 1; i < encoded.length + 1; i++) {
            perm[i] = encoded[i - 1] ^ perm[i - 1];
        }
        return perm;
    }

    public static void main(String[] args) {
        int[] encoded = {3,1};
        M0511 test = new M0511();
        int[] ret =  test.decode(encoded);
        System.out.println(Arrays.toString(ret));
    }
}
