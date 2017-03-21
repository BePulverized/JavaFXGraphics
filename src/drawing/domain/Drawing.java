package drawing.domain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by BePul on 15-2-2017.
 */
public class Drawing extends DrawingItem implements Serializable {

    private String name;
    private ArrayList<DrawingItem> items;
    int width;
    int height;
    private ObservableList<DrawingItem> observableList;

    public ObservableList<DrawingItem> itemsToObserve(){
        return FXCollections.unmodifiableObservableList(observableList);
    }

    public String getName() {
        return name;
    }

    public List<DrawingItem> getDrawingItems()
    {
        return Collections.unmodifiableList(items);
    }

    public Drawing(String name, int width, int height) {
        super(null, null);
        this.name = name;
        this.width =width;
        this.height =height;
        items = new ArrayList<>();
        observableList = FXCollections.observableArrayList(items);
    }

    public void insertDrawingItem(DrawingItem item)
    {
        observableList.add(item);
        Collections.sort(observableList, (o1, o2) -> o1.compareTo(o2));
    }

    public void removeDrawingItem(DrawingItem item){
        observableList.remove(item);
    }

    public void paintUsing(IPaintable paintable)
    {
        paintable.ClearCanvas();
        for(DrawingItem item:observableList) {
            item.paintUsing(paintable);
        }
    }


    @Override
    public int compare(DrawingItem o1, DrawingItem o2) {
        return 0;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean insideBoundingBox(Point point) {
        return false;
    }
}
