package drawing.domain;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.jdbc2.optional.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by BePulverized on 13-3-2017.
 */
public class DatabaseMediator implements IPersistencyMediator {

    private Properties prop;
    private Connection con;
    private MysqlDataSource datasrc;

    public DatabaseMediator()
    {

    }

    private void closeConnection() throws SQLException {
        con.close();
    }

    private void initConnection() throws SQLException
    {
        con = (Connection) datasrc.getConnection();
    }

    @Override
    public ObservableList<DrawingItem> load(String nameDrawing) {
        ObservableList<DrawingItem> drawinglist = null;
        try {
            initConnection();
            PreparedStatement ps=null;
            ResultSet rs = null;
            String q;
            q="SELECT * FROM graphics.drawingitem ORDER BY id DESC LIMIT 1;";
            ps=(PreparedStatement)con.prepareStatement(q);
            rs=ps.executeQuery();

            if(rs.next())
            {
                ByteArrayInputStream bais;
                ObjectInputStream ins;

                bais = new ByteArrayInputStream(rs.getBytes("object"));
                ins = new ObjectInputStream(bais);
                ArrayList<DrawingItem> list = (ArrayList<DrawingItem>) ins.readObject();
                drawinglist = FXCollections.observableList(list);
                ins.close();
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return drawinglist;
    }

    @Override
    public boolean save(Drawing drawing) {

        try{
            initConnection();
            String q;
            q = "INSERT INTO drawingitem(Object) VALUES(?)";
            PreparedStatement ps = null;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(new ArrayList<DrawingItem>(drawing.itemsToObserve()));
            oos.flush();
            oos.close();
            bos.close();

            byte[] data = bos.toByteArray();
            ps=(PreparedStatement)con.prepareStatement(q);
            ps.setObject(1, data);
            ps.executeUpdate();
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return false;
        }

    }

    @Override
    public boolean init(Properties props) {


        datasrc = new MysqlDataSource();
        datasrc.setServerName(props.getProperty("ServerName"));
        datasrc.setDatabaseName(props.getProperty("DatabaseName"));
        datasrc.setPort(3306);
        datasrc.setUser(props.getProperty("dbUser"));
        datasrc.setPassword(props.getProperty("dbPassword"));
        try{
            initConnection();
            closeConnection();
            return true;
        }
        catch(SQLException ex)
        {
            return false;
        }

    }
}
