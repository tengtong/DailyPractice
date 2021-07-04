package sort.countsort;

public class CountingSort {
    public void sort(int[] array){
        //1、找出最大最小
        int max = array[0];//求出待排序数组的最大值，最小值，找出取值区间
        int min = array[0];
        for(int i = 0; i<array.length; i++){
            if(array[i] > max){
                max = array[i];
            }
            if(array[i] < min){
                min = array[i];
            }
        }
        //计数
        int[] countArr = new int[max - min + 1];
        for(int i = 0; i<array.length; i++){
            int index = array[i] - min;//这里取最小值的index为0
            countArr[index] += 1;
        }
        for(int i = 1; i<countArr.length; i++){//对数组内元素进行累加//？
            countArr[i] = countArr[i] + countArr[i-1];
        }
        int[] temp = new int[array.length];//创建临时数组存储最终有序的数据列表
        for(int i = array.length-1; i >= 0; i--){//逆序扫描待排序数组，可保证元素的稳定行
            int index = array[i] - min;
            temp[countArr[index] - 1] = array[i];
            countArr[index] -= 1;
        }
        for(int i = 0; i<temp.length; i++){//将临时数据列表依次放入原始数组
            array[i] = temp[i];
        }
    }
}
