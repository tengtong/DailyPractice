package dailypractice.m05;

// 810. 黑板异或游戏
// 当轮该该玩家时，黑板上的数字异或结果不为0，即获胜
public class M0522 {

    // 方法一：当数组为偶数时，先选方一定能获得胜利
    //       否则数组为奇数时，初始异或结果就是0，Alice也获胜，不然失败
    public boolean xorGame(int[] nums) {
        int n = nums.length, x = 0;
        if(n % 2 == 0) return true;
        for(int num : nums)
            x ^= num;
        return x == 0;
    }
}
