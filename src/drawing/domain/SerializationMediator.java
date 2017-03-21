package drawing.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by BePulverized on 13-3-2017.
 */
public class SerializationMediator implements IPersistencyMediator {

    private Properties props;

    @Override
    public ObservableList<DrawingItem> load(String nameDrawing) {

        ObservableList<DrawingItem> drawingList = null;
        try{
            FileInputStream filein = new FileInputStream("C:\\Users\\BePulverized\\Documents\\" + nameDrawing + ".ser");
            ObjectInputStream in = new ObjectInputStream(filein);
            ArrayList<DrawingItem> list = (ArrayList<DrawingItem>) in.readObject();
            drawingList = FXCollections.observableList(list);
            in.close();
            filein.close();
            return drawingList;
        }
        catch(Exception ex)
        {
            return null;
        }
    }

    @Override
    public boolean save(Drawing drawing) {

        try {
            FileOutputStream fileout = new FileOutputStream("C:\\Users\\BePulverized\\Documents\\" + drawing.getName() + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileout);
            out.writeObject(new ArrayList<>(drawing.getDrawingItems()));
            out.close();
            fileout.close();
            return true;
        }
        catch(IOException io)
        {
            return false;
        }
    }

    @Override
    public boolean init(Properties props) {

        return false;
    }
}
