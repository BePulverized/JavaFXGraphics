package drawing.domain;

import java.io.Serializable;

/**
 * Created by BePul on 15-2-2017.
 */
public class Point implements Serializable{

    private double x;
    private double y;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
