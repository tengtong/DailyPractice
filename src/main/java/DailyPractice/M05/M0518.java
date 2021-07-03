package dailyPractice.M05;

public class M0518 {

    // 方法一：三重循环
    private int[] xor;
    // 思路：滑动窗口（对i,j,k三个元素都进行循环枚举） + 异或前缀和
    public int countTriplets(int[] arr) {
        int ret = 0;
        // 利用异或的前缀和求解a、b，优秀！
        // xor[i] 表示arr[0]~arr[i]的异或结果
        xor = new int[arr.length];
        xor[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            xor[i] = arr[i] ^ xor[i - 1];
        }
        // 最外层确定窗口的长度
        for (int len = 2; len <= arr.length; len++) {
            for (int i = 0, k = len - 1; k < arr.length; i++, k++) {
                 ret += cunZaiJ(arr, i, k);
            }
        }
        return ret;
    }
    // 方法：判断i～j中是否
    public int cunZaiJ(int[] arr, int i, int k) {
        int count = 0;
        for (int j = i + 1; j <= k; j++) {
            // 当i!=0时，a=xor[j-1]^xor[i-1], b=xor[j-1]^xor[k]
            // 故a==b, 可以转化成xor[i-1]==xor[k]
            int a = (i == 0 ? xor[j - 1] : xor[j - 1] ^ xor[i - 1]);
            int b = xor[k] ^ xor[j - 1];
            if (a == b) count++;
        }
        return count;
    }

    // 方法二：在方法一的基础上，对寻找a==b优化（只需要对i,k进行枚举）
    public int countTriplets1(int[] arr) {
        // 这里设置xor[0]==0, xor[i]表示前i个元素的异或结果
        // 这样计算a、b时，就不用对头部元素特殊考虑了
        // 这里的数组比较特殊，xor[i]是不包括数组arr[i]的
        int[] xor = new int[arr.length + 1];
        for (int i = 1; i < xor.length; i++) {
            xor[i] = arr[i - 1] ^ xor[i - 1];
        }
        int count = 0;
        // int a = xor[i] ^ xor[j];
        // int b = xor[j] ^ xor[k + 1];
        for (int i = 0; i < arr.length; i++) {
            for (int k = i + 1; k < arr.length; k++) {
                if (xor[i] == xor[k + 1]) {
                    count += k - i;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = {2,3,1,6,7};
        M0518 test = new M0518();
        System.out.println(test.countTriplets1(arr));
    }
}
