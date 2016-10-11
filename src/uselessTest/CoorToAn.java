package uselessTest;

/**
 * Created by coco1 on 2016/10/11.
 */
public class CoorToAn {
    /**
     * 输入两个坐标点得到角度
     * 坐标的正算
     * 64位精度
     * @param x1 坐标点1 x
     * @param x2 坐标点2 x
     * @param y1 坐标点1 y
     * @param y2 坐标点2 y
     * @return 角度
     */

    public static double XYtoAn(double x1, double x2, double y1, double y2) {
        return Math.atan((y2-y1)/(x2-x1));
    }

    /**
     * 输入坐标角度距离返回点距离
     * 坐标的反算
     * 64位精度
     * @param x1 输入坐标x
     * @param y1 输入坐标y
     * @param Ang 输入角度
     * @param distance 输入距离
     * @return 原来点的坐标{x,y}
     */
    public static double[] AntoXY(double x1, double y1, double Ang, double distance) {
        double[] ret = new double[2];
        ret[0] = x1 + distance * Math.sin(Ang);
        ret[1] = y1 + distance * Math.cos(Ang);
        return ret;
    }
}
