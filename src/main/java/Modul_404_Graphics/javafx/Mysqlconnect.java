package Modul_404_Graphics.javafx;

import java.sql.Connection;
import java.sql.DriverManager;

public class Mysqlconnect {

        public static Connection ConnectDb(){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Modul404", "root", "CsBe.12345");
                return conn;
            } catch (Exception e) {
                return null;
            }

        }


}


