package drawing.domain;

import drawing.javafx.JavaFXPaintable;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.canvas.*;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * Created by BePul on 22-2-2017.
 */
public class DrawingTool extends Application {

    private Canvas drawingCanvas;
    private IPaintable paintable;
    private Drawing drawing;
    private Point mouseclick;
    private BorderPane root;
    private HBox buttons;
    private DatabaseMediator conn;
    private boolean useDatabase = false;
    SerializationMediator ser;

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Graphics Jordy Verlaek");
        root = new BorderPane();
        drawingCanvas = new Canvas(1000,1000);
        GraphicsContext gc = this.drawingCanvas.getGraphicsContext2D();
        this.paintable = new JavaFXPaintable(gc);
        RefreshCanvas(useDatabase);
        AddDrawingFigures();
        drawing.paintUsing(paintable);
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();

        /* wanneer het programma sluit wordt er automatisch opgeslagen */
        if(useDatabase) {
            Runtime.getRuntime().addShutdownHook(new Thread(() -> conn.save(drawing)));
        }
        else{
            Runtime.getRuntime().addShutdownHook(new Thread(() -> ser.save(drawing)));
        }

    }

    public void AddDrawingFigures() {
        //Controls
        buttons = new HBox();
        root.setTop(buttons);
        root.setMargin(buttons, new Insets(5,5,5,5));

        root.setCenter(drawingCanvas);

        //Mouseclick
        root.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseclick = new Point(event.getX(), event.getY());
            }
        });
        //Combobox
        ComboBox cbDrawings = new ComboBox();
        cbDrawings.setItems(drawing.itemsToObserve());
        buttons.getChildren().add(cbDrawings);

        //AddButton
        Button addOval = new Button("Add Oval");
        buttons.getChildren().add(addOval);
        addOval.setOnAction(event -> {

            Oval oval = new Oval(Color.BLACK, mouseclick, 20, 20, 20);
            drawing.insertDrawingItem(oval);
            //Redraw
            drawing.paintUsing(paintable);
        });

        //AddPolygon
        Button addPolygon = new Button("Add Polygon");
        buttons.getChildren().add(addPolygon);
        addPolygon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        //Set text
        TextField text = new TextField();
        buttons.getChildren().add(text);
        //AddText
        Button addText = new Button("Add Text");
        buttons.getChildren().add(addText);
        addText.setOnAction(event -> {
            if(text.getText()!= null)
            {
                drawing.insertDrawingItem(new PaintedText(Color.BLACK, text.getText(), "Times new Roman", mouseclick, 20, 20));
                //Redraw
                drawing.paintUsing(paintable);
            }
        });

        //Delete
        Button delete = new Button("Delete selected");
        buttons.getChildren().add(delete);
        delete.setOnAction(event -> {
            drawing.removeDrawingItem((DrawingItem)cbDrawings.getValue());
            //Redraw canvas
            drawing.paintUsing(paintable);
        });

        // Checkbox voor databaseconnectie
        CheckBox chb = new CheckBox("Use database");
        buttons.getChildren().add(chb);
        chb.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov,
                                Boolean old_val, Boolean new_val) {
                try {
                    RefreshCanvas(new_val);
                    drawing.paintUsing(paintable);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    public void RefreshCanvas(boolean db) throws IOException {
        // Inladen van properties vanuit properties file
        Properties properties = new Properties();
        InputStream input = new FileInputStream("config.properties");
        properties.load(input);
        // Opzetten database connectie
        conn = new DatabaseMediator();
        conn.init(properties);
        // Opzetten serializatie
        ser = new SerializationMediator();
        ser.init(properties);

        // Laad de drawings in via een serializatie file
        if(db == false) {
            drawing = new Drawing("TestDrawing", 900, 900);
            for(DrawingItem item : ser.load("TestDrawing"))
            {
                drawing.insertDrawingItem(item);
            }
        }
        // OF

        // Laad de drawings in via de database
        else {
            drawing = new Drawing("TestDrawing", 900, 900);
            for (DrawingItem item : conn.load(" TestDrawing")) {
                drawing.insertDrawingItem(item);
            }
        }
    }




}
