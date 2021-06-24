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
import javax.swing.table.TableColumn;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    @FXML
    private TableColumn firstNames;
    @FXML
    private TableColumn lastNames;
    @FXML
    private TableColumn classes;
    @FXML
    private TableColumn grades;
    ObservableList<Columns> listM;

    public void switchToScene1(ActionEvent event) throws IOException{

        Parent root = FXMLLoader.load(getClass().getResource("/view/Modul404.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void delete(){
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Modul404", "root", "CsBe.12345");
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


    public static ObservableList<Modul404> getDatabase() {

        try {

            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Modul404", "root", "CsBe.12345");
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM notes";
            ResultSet rs = stmt.executeQuery(query);
            ObservableList<Modul404> list = FXCollections.observableArrayList();
            while (rs.next()) {
                list.addAll(new Modul404());


            }

        } catch (Exception er) {
            er.printStackTrace();
        }
        return getDatabase();
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

            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Modul404", "root", "CsBe.12345");
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




