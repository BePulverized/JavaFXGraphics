package drawing.domain;
import javafx.scene.paint.Color;
/**
 * Created by BePul on 15-2-2017.
 */
public class Oval extends DrawingItem {


    private Point anchor;
    private double width;
    private double height;
    private double weight;


    @Override
    public Point getAnchor() {
        return anchor;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public void paintUsing(IPaintable paintable) {
        paintable.paint(this);
    }

    public Oval(Color color, Point anchor, double width, double height, double weight) {
        super(color);
        this.anchor = anchor;
        this.width = width;
        this.height = height;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Oval" + " (" + getAnchor().getX() + ", " + getAnchor().getY() + ")";
    }

    @Override
    public boolean insideBoundingBox(Point point) {
        boolean boundingboxX = (super.getAnchor().getX() < point.getX())
                && (super.getAnchor().getX() + width > point.getX());
        boolean boundingboxY = (super.getAnchor().getY() < point.getY())
                && (super.getAnchor().getY() + height > point.getY());
        return boundingboxX && boundingboxY;

    }

    @Override
    public int compare(DrawingItem o1, DrawingItem o2) {
        double dist1 = Math.sqrt(Math.pow(o1.getAnchor().getX(), 2) + Math.pow(o1.getAnchor().getY(), 2));
        double dist2 = Math.sqrt(Math.pow(o2.getAnchor().getX(), 2) + Math.pow(o2.getAnchor().getY(), 2));
        if (dist1 > dist2) {
            return 1;
        } else {
            return -1;
        }
    }
}
