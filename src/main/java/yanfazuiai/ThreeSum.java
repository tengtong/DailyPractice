package yanfazuiai;

import java.util.*;

// 15. 三数之和
// 不重复
public class ThreeSum {

    // 方法一：hashMap + 两层循环
    //       利用两数之和，先双循环求出a+b，再找 0-(a+b)，并对三元结果进行排序
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ret = new LinkedList<>();
        if (nums == null || nums.length < 3) {
            return ret;
        }
        Set<List<Integer>> listSet = new HashSet();
        // key：nums[i]，value：index
        Map<Integer, Integer> map = new HashMap<>();
        // a = nums[i]
        for (int i = 0; i < nums.length; i++) {
            // b = nums[j]
            map.put(nums[i], i);
            for (int j = i + 1; j < nums.length; j++) {
                int temp = - (nums[i] + nums[j]);
                if (map.containsKey(temp) && map.get(temp) != i && map.get(temp) != j) {
                    ArrayList curList = new ArrayList(3);
                    curList.add(nums[i]);
                    curList.add(nums[j]);
                    curList.add(temp);
                    curList.sort(new Comparator<Integer>() {
                        @Override
                        // 默认返回0，是不排序的意思
                        public int compare(Integer o1, Integer o2) {
                            return o1 - o2;
                        }
                    });
                    listSet.add(curList);
                }
                map.put(nums[j], j);
            }
        }
        ret = new LinkedList<>(listSet);
        return ret;
    }

    // 方法二：排序 + 双指针
    public List<List<Integer>> threeSum1(int[] nums) {
        int n = nums.length;
        // 先排序，往后遍历，结果集不会重复
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {3,0,-2,-1,1,2};
        ThreeSum test = new ThreeSum();
        List<List<Integer>> lists = test.threeSum(nums);
        for (List list : lists) {
            System.out.println(list.toString());
        }
    }
}
