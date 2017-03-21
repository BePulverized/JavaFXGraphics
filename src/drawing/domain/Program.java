package drawing.domain;

import javafx.scene.paint.Color;

import java.io.File;
import java.util.Scanner;

/**
 * Created by BePul on 15-2-2017.
 */
public class Program {
    public static void main(String[] args){
        Drawing newDrawing = new Drawing("Mijn drawing", 80, 80);
        Oval oval = new Oval(Color.BLACK, new Point(0, 0), 20, 0, 0);
        Point[] poly = new Point[]{new Point(0,0), new Point(1,1)};

        Image image = new Image(Color.BLACK, new File(""), new Point(0,0), 0,0);
        newDrawing.insertDrawingItem(oval);

        newDrawing.insertDrawingItem(image);
        System.out.println("hoi");
        Scanner input = new Scanner(System.in);
        String shape = input.nextLine();

    }
}
