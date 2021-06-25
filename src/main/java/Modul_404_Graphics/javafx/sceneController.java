package Modul_404_Graphics.javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.IOException;
import java.sql.*;

import static Modul_404_Graphics.javafx.Mysqlconnect.ConnectDb;

public class sceneController {

    public Stage stage;
    public Scene scene;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField klasse;
    @FXML
    private TextField grade;
    @FXML
    private TableView<Columns> dataBase;

    public void switchToScene1(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/Modul404.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void delete(){
        try {
            Connection conn =ConnectDb();
            String query = " DELETE FROM notes\n" +
                    "ORDER BY id DESC\n" +
                    "LIMIT 1 ";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.execute();
        } catch (Exception e) {
            System.err.println("Got an exception in the insert phase");
            System.err.println(e.getMessage());
        }

    }


    public void listed() {
        Connection conn = ConnectDb();
        ObservableList<Columns> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select first, last,class,grade from users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.addAll(new Columns((rs.getString("first")), (rs.getString("last")), (rs.getString("classes")), (rs.getString("grade"))));
                System.out.println("this is running");
            }
        } catch (Exception e) {
            System.out.println("fix me");
        }
        dataBase.setItems(list);
    }

    public void submit() {
        String first1 = firstName.getText();
        String last1 = lastName.getText();
        String klasse1 = klasse.getText();
        String grade1 = grade.getText();
        if ((!(first1.length() > 0)) || (!(last1.length() > 0)) || (!(klasse1.length() > 0)) || (!(grade1.length() > 0))) {
            JOptionPane.showMessageDialog(null, "A Field is empty");
        }
        try {

            Connection conn = ConnectDb();
            String query = " insert into notes (first, last, class, grade)"
                    + " values (?, ?, ?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, first1);
            preparedStmt.setString(2, last1);
            preparedStmt.setString(3, klasse1);
            preparedStmt.setString(4, grade1);
            preparedStmt.execute();
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception in the insert phase");
            System.err.println(e.getMessage());
        }
    }


}





