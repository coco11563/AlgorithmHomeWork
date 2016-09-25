package MergeSort_QuickSort;

import java.util.Comparator;

/**
 * Created by coco1 on 2016/9/23.
 *
 * A standard example for Comparator in use
 */
public class Student {
    public String name;
    public int age;

    //declare comparator
    public static final Comparator<Student> BYNAME = new ByName();
    public static final Comparator<Student> BYAGE = new ByAge();

    private static class ByName implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.name.compareTo(o2.name);//use String way to sort
        }
    }
    private static class ByAge implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.age - o2.age;//use int way to sort
        }
    }
}
