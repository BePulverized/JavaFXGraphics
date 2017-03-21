package drawing.domain;

/**
 * Created by BePul on 22-2-2017.
 */
public interface IPaintable {
    void paint(Oval oval);
    void paint(Point from, Point too);
    void paint(PaintedText text);
    void paint(Image image);
    void ClearCanvas();
}
