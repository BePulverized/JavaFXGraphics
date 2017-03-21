package drawing.domain;

import javafx.scene.paint.Color;
/**
 * Created by BePul on 15-2-2017.
 */
public class PaintedText extends DrawingItem{

    private String content;
    private String fontName;
    private Point anchor;
    private double width;
    private double height;

    public String getContent() {
        return content;
    }

    public String getFontName() {
        return fontName;
    }

    public Point getAnchor() {
        return anchor;
    }

    @Override
    public void paintUsing(IPaintable paintable) {
        paintable.paint(this);
    }

    public PaintedText(Color color, String content, String fontName, Point anchor, double width, double height) {
        super(color);
        this.content = content;
        this.fontName = fontName;
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
        return "PaintedText: " + getContent() + " (" + getAnchor().getX() + ", " + getAnchor().getY() + ")";
    }

    @Override
    public boolean insideBoundingBox(Point point) {
        return false;
    }

}
