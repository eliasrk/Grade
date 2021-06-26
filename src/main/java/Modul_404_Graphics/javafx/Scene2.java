package Modul_404_Graphics.javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import static Modul_404_Graphics.javafx.dBConnection.ConnectDb;

public class Scene2 {

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
    private BarChart barGraph;
    @FXML
    private TableColumn<String, String> first, last, classes;
    @FXML
    private TableColumn<Float, Float> graded;

    public void switchToScene1(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Modul404.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void delete() {
        try {
            Connection conn = ConnectDb();
            String query = " DELETE FROM notes\n" +
                    "ORDER BY id DESC\n" +
                    "LIMIT 1 ";
            assert conn != null;
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.execute();
        } catch (Exception e) {
            System.err.println("Got an exception in the insert phase");
            System.err.println(e.getMessage());
        }

    }

    public void Graph() {
        try {
            final CategoryAxis xAxis = new CategoryAxis();
            final NumberAxis yAxis = new NumberAxis();
            XYChart.Series<String, Float> set1 = new XYChart.Series<>();
            xAxis.setMaxWidth(40);
            barGraph.getData().add(set1);
            barGraph.setTitle("Country Summary");
            xAxis.setLabel("Country");
            yAxis.setLabel("Value");
            Connection conn = ConnectDb();
            assert conn != null;
            PreparedStatement ps = conn.prepareStatement("select * from notes");
            ResultSet rs = ps.executeQuery();
            try {
                while (rs.next()) {

                    set1.getData().add(new XYChart.Data((rs.getString("first")), (rs.getFloat("grade"))));

                }
            } catch (Exception e) {
                System.out.println("fix me");
            }
        } catch (Exception e) {
            System.out.println("fix me");
        }
    }

    public void listed() throws SQLException {

        Connection conn = ConnectDb();
        ObservableList<Columns> list = FXCollections.observableArrayList();
        assert conn != null;
        PreparedStatement ps = conn.prepareStatement("select * from notes");
        ResultSet rs = ps.executeQuery();
        try {

            while (rs.next()) {
                list.add(new Columns((rs.getString("first")), (rs.getString("last")),
                        (rs.getString("class")), (rs.getFloat("grade"))));
            }
        } catch (Exception e) {
            System.out.println("fix me");
        }

        first.setCellValueFactory(new PropertyValueFactory<>(first.getId().toLowerCase()));
        last.setCellValueFactory(new PropertyValueFactory<>(last.getId().toLowerCase()));
        graded.setCellValueFactory(new PropertyValueFactory<>(graded.getId().toLowerCase()));
        classes.setCellValueFactory(new PropertyValueFactory<>(classes.getId().toLowerCase()));
        dataBase.setItems(list);
        conn.close();


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
            assert conn != null;
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





