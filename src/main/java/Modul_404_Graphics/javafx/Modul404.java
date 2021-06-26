package Modul_404_Graphics.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Modul404 {
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    public Stage stage;
    public Scene scene;
    public void quit(){
        System.exit(0);

    }
    public void switchToScene2(ActionEvent event) throws IOException {
        String user = username.getText();
        String pass = password.getText();

        /*if ((user.equals("user")) || (pass.equals("pass"))) {*/
            Parent root = FXMLLoader.load(getClass().getResource("/view/Scene2.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

/*
        }*/


    }
}

