package dailypractice.m05;

import java.util.*;

//690、员工的重要性
//思路：用一个map来存所有的employees信息，然后循环遍历所有下属员工
public class M0501 {
    class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }

    //bfs
    public int getImportanceByBfs(List<Employee> employees, int id) {
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee e : employees) {
            map.put(e.id, e);
        }
        Queue<Employee> queue = new LinkedList();
        queue.add(map.get(id));
        int sum = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Employee curEmployee = queue.poll();
                sum += curEmployee.importance;
                if (curEmployee.subordinates != null) {
                    for (int tempId : curEmployee.subordinates) {
                        queue.add(map.get(tempId));
                    }
                }
            }
        }
        return sum;
    }

    // dfs
    // 递归栈实现
    private Map<Integer, Employee> map;
    private int sum;
    public int getImportanceByDfs(List<Employee> employees, int id) {
        map = new HashMap<>();
        sum = 0;
        for (Employee e : employees) {
            map.put(e.id, e);
        }
        // dfs入口，因为出发点只有一个，所以只要对当前id的人深入搜索即可
        dfs(map.get(id));
        return sum;
    }
    public void dfs (Employee employee) {
        sum += employee.importance;
        //符合条件的都会深入搜索，直到搜到路径不通之后，再返回上一层再向另一个方向深入搜索
        if (employee.subordinates != null) {//算是截止条件//类同方向矩阵
           for (int tempId : employee.subordinates) {
               dfs(map.get(tempId));
           }
        }
    }
}
