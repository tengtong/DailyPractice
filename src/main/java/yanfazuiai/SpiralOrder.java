package yanfazuiai;

import java.util.LinkedList;
import java.util.List;

// 54. 螺旋矩阵（按顺指针打印矩阵）
// 思路：用一个方向矩阵来标记下一步的方向
public class SpiralOrder {
    // {0,1}，0表示同一维数组，1表示数组中index为1
    private int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public List<Integer> spiralOrder(int[][] matrix) {
        // 结果集
        List<Integer> ret = new LinkedList<>();
        // 特殊情况处理
        if (matrix.length == 0 || matrix[0].length == 0 || matrix == null) {
            return ret;
        }
        // 遍历总长度就是矩阵元素的总个数
        int n = matrix.length, m = matrix[0].length;
        int length = n * m;
        // 用一个标记矩阵来记录已经被访问过位置
        // boolean数组，默认都是false
        // 类似bfs？，不过遍历的执行顺序不一样，不过也是一种遍历，所以也可以用相同的方法进行使用
        boolean[][] flagMatrix = new boolean[n][m];
        // row行, 第几行==第几个一维数组, matrix[row][col]
        int curRow = 0, curCol = 0;
        int direIndex = 0;
        while (length-- > 0) {
            // 添加当前[row, col]位置的元素
            ret.add(matrix[curRow][curCol]);
            flagMatrix[curRow][curCol] = true;
            // 计算下一个坐标的位置，并判断是否要更改方向
            int tempRow = curRow + direction[direIndex][0];
            int tempCol = curCol + direction[direIndex][1];
            // 如果需要转向，则按方向矩阵进行固定顺序转向
            if (tempRow < 0 || tempRow >= n || tempCol < 0 || tempCol >= m || flagMatrix[tempRow][tempCol]) {
                direIndex = (direIndex + 1) % 4;
            }
            int nextRow = curRow + direction[direIndex][0];
            int nextCol = curCol + direction[direIndex][1];
            curRow = nextRow;
            curCol = nextCol;
        }
        return ret;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3}, {4,5,6},{7,8,9}};
        SpiralOrder test = new SpiralOrder();
        System.out.println(test.spiralOrder(matrix));
    }
}
