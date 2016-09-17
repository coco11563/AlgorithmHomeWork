package uselessTest;

/**
 * Created by coco1 on 2016/9/15.
 */
public class K {
    private long K;
    private long bit = 1000000000L;
    private long defaultK = 4698937039L;
    public K(long s) {
        if (s < bit) {
            K = defaultK;
        } else {
            K = s;
        }
    }
    public int choiceTime() {
        return (int) (K / bit);
    }
}
