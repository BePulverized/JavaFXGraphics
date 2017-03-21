package drawing.domain;

import javafx.collections.ObservableList;

import java.util.Properties;

/**
 * Created by BePulverized on 13-3-2017.
 */
public interface IPersistencyMediator {

    ObservableList<DrawingItem> load(String nameDrawing);
    boolean save(Drawing drawing);
    boolean init(Properties props);

}
