package yanfazuiai;

import java.util.*;

//map中根据value值进行排序
//思路：HashMap + list
//     把map对象存到list中，通过list的比较器对内部元素进行排序
public class MapSortByValue {
    public static void main(String[] args) {
        // 记录输入几行数据
        Scanner sc = new Scanner(System.in);
        Map<Integer, Integer> map = new HashMap<>();
        List<Map.Entry<Integer, Integer>> list;
        int num = sc.nextInt();
        sc.nextLine();
        String[] str = new String[num];
        for (int i = 0; i < num; ++i) {
            str[i] = sc.nextLine();
        }
        for (int i = 0; i < num; ++i) {
            String[] s = str[i].split(" ");
            //query
            if ("query".equals(s[0])) {
                if (map.isEmpty()) {
                    System.out.println("null");
                }
                list = new ArrayList<>(map.entrySet());
                Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
                    @Override
                    public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                });
                //输出前10个热点数据
                if (list.size() <= 10) {
                    for (int j = 0; j < list.size(); ++j) {
                        if (j < list.size() - 1) {
                            System.out.print(list.get(j).getKey() + " ");
                        } else {
                            System.out.println(list.get(j).getKey());
                        }
                    }
                } else {
                    for (int j = 0; j < 10; j++) {
                        if (j < 9) {
                            System.out.print(list.get(j).getKey() + " ");
                        } else {
                            System.out.println(list.get(j).getKey());
                        }
                    }
                }
                //append
            } else if ("append".equals(s[0])) {
                int a = Integer.parseInt(s[1]);
                int b = Integer.parseInt(s[2]);
                if (map.containsKey(a)) {
                    map.put(a, b + map.get(a));
                } else {
                    map.put(a, b);
                }
            }
        }
    }
}
