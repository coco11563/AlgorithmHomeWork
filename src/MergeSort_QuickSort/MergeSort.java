package MergeSort_QuickSort;

/**
 * Created by coco1 on 2016/9/23.
 *
 * 1.1基础的归并方法
 *
 * base on the idea of merging
 *
 * taking an auxiliary arry to hold the data
 *
 * maintain three index
 *
 * i:the current entry in the left half
 *
 * j:the current entry on the right half
 *
 * k:the current entry in the sorted result
 *
 * merge sort is facing on two sorted array
 *
 * 1.2自顶向下的归并方法
 */
public class MergeSort{
    public static void main(String args[]) {
        int[] a = {1, 6 ,7 ,10, 5, 8, 9};
        if (!isSorted(a,0,a.length-1)) {
            System.out.print(1);
        }
        mergeSort(a, new int[a.length -1] ,0 ,3 , a.length-1);
        if (isSorted(a,0,a.length-1)) {
            System.out.print(1);
        }
    }
    public static void mergeSort(int[] a, int[] aux, int lo, int mid, int hi) {
        aux = a.clone(); //Copy不是什么好东西
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid + 1, hi);
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) //i > mid即i走到终点
                a[k] = aux[j++]; //剩下的用j处剩下的补全
            else if (j > hi) //j > hi 即j走到终点
                a[k] = aux[i++]; //剩下的用i处剩下的补全
            else if (aux[j] < aux[i]) //正常判断 <----继承了comparable接口后应该改写这部分
                a[k] = aux[j++]; //小的插入
            else
                a[k] = aux[i++]; //小的插入
        }
    }
    public static boolean isSorted(int[] a, int lo, int hi) {
        while (lo < hi) {
            if (a[lo] > (a[lo + 1])) {
                return false;
            }
            lo += 1;
        }
        return true;
    }
}
