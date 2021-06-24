package Modul_404_Graphics.javafx;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Mysqlconnect {

        Connection conn = null;
        public static Connection ConnectDb(){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Modul404", "root", "CsBe.12345");
                return conn;
            } catch (Exception e) {
                return null;
            }

        }
    public static ObservableList<Columns> Mysqlconnect(){
        Connection conn = ConnectDb();
        ObservableList<Columns> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from users");
            ResultSet rs = ps.executeQuery();
/**/
            while (rs.next()){
                list.add(new Columns((rs.getString("first")), (rs.getString("last")), (rs.getString("classes")), (rs.getString("grade"))));
            }
        } catch (Exception e) {
            System.out.println("fix me");
        }
        return list;
    }

}


