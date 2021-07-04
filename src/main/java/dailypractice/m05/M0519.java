package dailypractice.m05;

import java.util.Arrays;

// 1738. 找出第 K 大的异或坐标值
public class M0519 {

    public int kthLargestValue(int[][] matrix, int k) {
        // 先用一个二维异或矩阵计算各个坐标的结果
        // 因为异或的最值很难判断，需要通过二进制的形式进行判断，所以不如将所有结果计算出来，然后排个序
        // row表示行，即有几个一维数组
        int row = matrix.length, col = matrix[0].length;
        int[][] xor = new int[row][col];
        xor[0][0] = matrix[0][0];
        for (int i = 1; i < row; i++) {
            xor[i][0] = xor[i - 1][0] ^ matrix[i][0];
        }
        for (int j = 1; j < col; j++) {
            xor[0][j] = xor[0][j - 1] ^ matrix[0][j];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                // ABCDEFGH = ABCDEF ^ ABCDGH ^ ABCD
                xor[i][j] = xor[i - 1][j] ^ xor[i][j - 1] ^ xor[i - 1][j - 1] ^ matrix[i][j];
            }
        }
        // 找出矩阵中第k大的值
        // 矩阵中元素没有排序规律，不能二分
        // 将矩阵中所有元素存入一维数组，然后排序
        //    也可以在xor[i][j]生成的时候就将其加入一个list中
        int[] sortArr = new int[row * col];
        int t = 0;
        // 先遍历行，再遍历列，即逐个一维数组进行遍历
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                sortArr[t++] = xor[i][j];
            }
        }
        // 对数组进行排序, Arrays.sort默认升序排列
        // 优化方向：排序
        Arrays.sort(sortArr);
        return sortArr[sortArr.length - k];
    }

    public static void main(String[] args) {
        int[][] matrix = {{5,2}, {1,6}};
        M0519 test = new M0519();
        System.out.println(test.kthLargestValue(matrix, 1));
    }
}
