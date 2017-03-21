package drawing.domain;

import java.io.File;
import javafx.scene.paint.Color;
/**
 * Created by BePul on 15-2-2017.
 */
public class Image extends DrawingItem {

    private File file;
    private Point anchor;
    private double width;
    private double height;

    public File getFile() {
        return file;
    }

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

    public Image(Color color, File file, Point anchor, double width, double height) {
        super(color);
        this.file = file;
        this.anchor = anchor;
        this.width = width;
        this.height = height;
    }

    @Override
    public int compare(DrawingItem o1, DrawingItem o2) {
        double dist1 = Math.sqrt(Math.pow(o1.getAnchor().getX(), 2) + Math.pow(o1.getAnchor().getY(), 2));
        double dist2 = Math.sqrt(Math.pow(o2.getAnchor().getX(), 2) + Math.pow(o2.getAnchor().getY(),2));
        if(dist1 > dist2)
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }

    @Override
    public String toString() {
        return super.toString() + " Height: " + height + " x: " + anchor.getX() + " y: " + anchor.getY();
    }

    @Override
    public boolean insideBoundingBox(Point point) {
        return false;
    }
}
