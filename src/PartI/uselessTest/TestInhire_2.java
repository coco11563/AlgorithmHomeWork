package uselessTest;

/**
 * Created by coco1 on 2016/9/15.
 */
public class TestInhire_2 extends TestInhire {
    private  int a = 0;
    @Override
    public void setThis_1() {
        a = 100;
    }
    public static int generate(long seed) {
        long bit = (seed * 0x5DEECE66DL + 0xBL) & ((1L << 48) - 1);
        return (int)(seed >>> (48 - bit));
    }
    public static void main(String args[] ) {
        TestInhire t = new TestInhire_2();
        long i = 0xBL;
        Long temp = 0x5DEECE66DL;
        System.out.println(i);
        System.out.println(temp);
        System.out.println(Integer.MAX_VALUE);
        System.out.print(generate(10));
//        for (int i = 0 ; i < 1000 ; i ++)
//            System.out.println(generate(1000L));
    }

}
