package search.dfs;

/**
 * DFS
 * 深度优先遍历，不同与BFS的一层层，是在得到一个新节点的时候立即对新节点进行遍历
 *
 * 故从一个点出发，DFS遍历到的点都是初始点可达的，故DFS常用来求解 可达性 问题
 * 在程序实现DFS时，需要考虑以下问题：
 * 栈：用栈来保存当前节点信息，当遍历新节点返回时能够继续遍历当前节点，可以使用递归栈
 * 标记：和BFS一样同样需要对已经遍历的节点进行标记
 * 应用场景：树和图的遍历（BFS用的较少），对某种情况进行搜索，可达性的问题
 */

/**
 * 查找最大的连通面积
 * 1是陆地，0是海水，只有在水平和竖直方向的陆地才算连通并属于一个岛屿，求最大的岛屿面积
 * 这里用一个递归函数来实现栈功能
 */
public class MaxAreaOfIsland {
    private int row,col;
    private int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}};

    public int solution(int[][] grid){
        if(grid == null || grid.length == 0){
            return 0;
        }
        row = grid.length;
        col = grid[0].length;
        int maxArea = 0;
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col;j++){
                maxArea = Math.max(maxArea,dfs(grid,i,j));//遍历矩阵中每个节点，进行dfs
            }
        }
        return maxArea;
    }
    private int dfs(int[][] grid,int r,int c){
        if (r < 0 || r >= row || c < 0 || c >= col || grid[r][c] == 0){//该点不在矩阵范围内，无效点
            return 0;
        }
        grid[r][c] = 0;//标记
        int area = 1;//面积初始化，因为至少存在当前点，故面积初始为1
        //继续遍历周围四个点，并查询是否为1，统计出最大的陆地面积，并将遍历过的地方抹成0
        //因为这里的area是在for-each外面重置的，故下面的area是统计了四个方向的面积
        for (int[] d:direction){
            area += dfs(grid,r + d[0],c + d[1]);
        }
        return area;
    }
}
