package yanfazuiai;

import java.util.Arrays;

// 合并两个有序数组
// 将B合并到数组A中
public class Merge {

    // 思路1：因为A足够大，那么从A的末尾开始新数组的排序
    //       哪怕最坏的情况，B中的元素全都比A存在的元素大，那直接将B添加在A的末端也合理
    public void merge(int A[], int m, int B[], int n) {
        if (A == null || B == null) {
            return;
        }
        //因为题目明确说了A数组足够大，所以直接在A数组操作
        int AIndex = m - 1;
        int BIndex = n - 1;
        for (int i = A.length - 1; i >= 0; i--) {
            if (BIndex < 0) {
                A[i] = A[AIndex];
                AIndex--;
            } else if (A[AIndex] < 0) {
                A[i] = B[BIndex];
                BIndex--;
            } else if (A[AIndex] > B[BIndex]) {
                A[i] = A[AIndex];
                AIndex--;
            } else if (A[AIndex] <= B[BIndex]) {
                A[i] = B[BIndex];
                BIndex--;
            }
        }
    }

    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, index = m + n - 1;
        while (i >= 0 && j >= 0)
            nums1[index--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
        while (j >= 0)
            nums1[index--] = nums2[j--];
    }

    // 思路2：将B中元素插排进A的队尾，然后对A数组实现插排/堆排，即不使用新的空间
    //       这里与原题的考题不一致，不推荐
    public void merge2(int A[], int m, int B[], int n) {
        if (B == null || A == null) {
            return;
        }
        for (int i = 0; i < n; i++) {
            A[m] = B[i];
            insertSort(A, m);
            m++;
        }
    }
    // 插排思路：将数组分为左区间和右区间，左区间是有序区间，然后将无序区间的元素放置在有序区间的正确位置
    public void insertSort(int[] arr, int m) {
        int current = arr[m - 1];
        int preIndex = m - 2;
        while (preIndex >= 0 && arr[preIndex] > current) {
            arr[preIndex + 1] = arr[preIndex];
            preIndex--;
        }
        arr[preIndex + 1] = current;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1,3,4,0,0,0};
        int[] b = {2,3,5};
        Merge test = new Merge();
        test.merge(a, 3, b, 3);
        System.out.println(Arrays.toString(a));
    }
}
