package uselessTest;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by coco1 on 2016/9/10.
 */
public class GenericTest {

    public static  void main(String args[]) {
        Pair<String>[] table = (Pair<String>[]) new Pair<?>[10]; //对这种情况使用ArrayList会更好

    }
}
