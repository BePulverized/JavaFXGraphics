package drawing.domain;

import java.io.*;
import java.util.Comparator;
import javafx.scene.paint.Color;
/**
 * Created by BePul on 15-2-2017.
 */
public abstract class DrawingItem implements Comparator<DrawingItem>, Serializable {

    private transient Color color;
    public DrawingItem previousState;
    private Point anchor;

    public Point getAnchor() {
        return this.anchor;
    }

    public Color getColor() {
        return this.color;
    }

    public DrawingItem(Color color, Point anchor) {
        this.color = color;
        this.anchor = anchor;
    }

    public DrawingItem(Color color) {
        this.color = color;
    }

    public int compareTo(DrawingItem d) {
        return (int) (this.getAnchor().getX() - d.getAnchor().getX());
    }

    public abstract void paintUsing(IPaintable paintable);

    public String toString() {
        return color.toString();
    }

    public abstract boolean insideBoundingBox(Point point);
}



