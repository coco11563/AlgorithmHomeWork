package uselessTest;

/**
 * Created by coco1 on 2016/9/15.
 */
public class TestInhire {
    private int a = 0;
    public void setThis_1() {
        a = 100;
    }
    public void print() {
        setThis_1();

        System.out.print(a);
    }
}
