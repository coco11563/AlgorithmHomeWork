package Elementary_Sorts;

import java.util.Arrays;

/**
 * Created by coco1 on 2016/10/8.
 *
 * 摘自百度百科
 *
 * 鸡尾酒排序法
 *
 * http://baike.baidu.com/link?url=4FRN1WbzHiSjNVJ3RT4-SJytnFjk3wANcPT5XfyX0cydyfcRzMRZckCsHW3OxyHXZhuei229hV25EpIERBCoxK
 */
public class CockTailSort {
    public static int[] cocktailSort(int[] src)
    {
        //将最小值排到队尾
        for(int i = 0 ; i < src.length/2 ; i++)
        {
            for(int j = i ; j < src.length-i-1 ; j++)
            {
                if(src[j] < src[j+1])
                {
                    int temp = src[j];
                    src[j] = src[j+1];
                    src[j+1] = temp;
                }
                System.out.println("交换小"+Arrays.toString(src));
            }
            //将最大值排到队头
            for(int j = src.length-1-(i+1); j > i ; j--)
            {
                if(src[j] > src[j-1])
                {
                    int temp = src[j];
                    src[j] = src[j-1];
                    src[j-1] = temp;
                }
                System.out.println("交换大"+Arrays.toString(src));
            }
            System.out.println("第"+i+"次排序结果："+ Arrays.toString(src));
        }
        return src;
    }
}
