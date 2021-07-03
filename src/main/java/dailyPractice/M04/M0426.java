package dailyPractice.M04;

import java.util.Arrays;

//1011.在D天内送达包裹的能力
//思路：因为题目特殊性，船载能力有下限和上限，故在这个可能范围结果中寻找满足条件的最大值即可
public class M0426 {
    public int shipWithinDays(int[] weights, int D) {
        int left = Arrays.stream(weights).max().getAsInt();
        int right = Arrays.stream(weights).sum();
        while (left < right) {
            int mid = left + (right - left) / 2;
            //判断一下当前运载能力mid能否完成任务
            int day = 1;
            int curWeight = 0;
            for (int weight : weights) {
                if (curWeight + weight > mid) {
                    day++;
                    curWeight = 0;
                }
                curWeight += weight;
            }
            //如果实际天数小于等于规定时间，则进一步减少船载能力进行尝试
            if (day <= D) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
