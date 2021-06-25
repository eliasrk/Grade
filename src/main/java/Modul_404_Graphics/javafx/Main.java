package Modul_404_Graphics.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class Main extends Application {

    public static void main(String[] args){

        createTable();
        getConnection();
        launch(args);
    }

    public static void createTable(){
        try {
            Connection con = getConnection();
            PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS notes(id int NOT NULL AUTO_INCREMENT, first varchar(255), last varchar(255), class varchar(255), grade int(7), PRIMARY KEY(id))");
            System.out.println("hello");
            create.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://127.0.0.1:3306/Modul404";
            String username = "Elias";
            String password = "CsBe.12345";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.print("Connected");

            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Function completed.");
        }

        return null;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Modul404.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Grading Program");
        stage.setScene(scene);
        stage.show();
    }


}
