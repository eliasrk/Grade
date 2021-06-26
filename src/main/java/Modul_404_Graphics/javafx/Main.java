package Modul_404_Graphics.javafx;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Objects;

import static Modul_404_Graphics.javafx.dBConnection.*;
public class Main extends Application {

    public static void main(String[] args){
        createTable();
        launch(args);
    }



    public static void createTable(){
        try {
            Connection con = ConnectDb();
            assert con != null;
            PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS notes(id int NOT NULL AUTO_INCREMENT," +
                    " first varchar(255), last varchar(255), class varchar(255), grade int(7), PRIMARY KEY(id))");
            create.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Modul404.fxml")));
        Scene scene = new Scene(root);
        stage.setTitle("Grading Program");
        stage.setScene(scene);
        stage.show();
    }


}
