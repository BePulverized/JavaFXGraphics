package drawing.javafx;

import drawing.domain.*;
import drawing.domain.Polygon;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.*;
import javafx.scene.text.Font;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * Created by BePul on 5-3-2017.
 */
public class JavaFXPaintable implements IPaintable{

    private GraphicsContext gc;
    public JavaFXPaintable(GraphicsContext gc) {
        this.gc = gc;
    }

    @Override
    public void paint(Oval oval) {
        gc.setFill(oval.getColor());
        gc.fillOval(oval.getAnchor().getX(), oval.getAnchor().getY(), oval.getWidth(), oval.getHeight());
    }

    @Override
    public void paint(Point from, Point to) {

        gc.strokeLine(from.getX(), from.getY(), to.getX(),
                to.getY());

    }

    @Override
    public void paint(PaintedText text) {
        gc.setFont(Font.font(text.getFontName()));
        gc.setFill(text.getColor());
        gc.fillText(text.getContent(), text.getAnchor().getX(), text.getAnchor().getY());
    }

    @Override
    public void paint(Image image) {
        try {
            BufferedImage buffer = ImageIO.read(image.getFile());
            javafx.scene.image.Image img = SwingFXUtils.toFXImage(buffer, null);
            gc.drawImage(img, image.getAnchor().getX(), image.getAnchor().getY(), image.getWidth(), image.getHeight());

        }
        catch(Exception ex)
        {
            ex.fillInStackTrace();
        }
    }

    @Override
    public void ClearCanvas() {
        gc.clearRect(0,0,1000,1000);
    }
}
