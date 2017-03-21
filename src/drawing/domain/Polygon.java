package drawing.domain;

import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by BePul on 15-2-2017.
 */
public class Polygon extends DrawingItem {

    private ArrayList<Point> vertices;
    private double weight;

    public ArrayList<Point> getVertices(){
        return vertices;
    }

    public Polygon(Point anchor, Color color, ArrayList<Point> vertices, double weight) {
        super(color, anchor);
        this.vertices = vertices;
        this.weight = weight;
    }

    @Override
    public void paintUsing(IPaintable paintable) {
        for (Point vert : vertices)
        {
            paintable.paint(vert, vertices.get(vertices.indexOf(vert) + 1));
            if (vertices.indexOf(vert) + 2 >= vertices.size())
            {
                break;
            }
        }
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
        return "Polygon";
    }

    @Override
    public boolean insideBoundingBox(Point point) {
        return false;
    }


}
