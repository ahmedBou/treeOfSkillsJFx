package sample;

import java.sql.*;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.Connection;

public class RegisterController implements Initializable {

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastname;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirmPass;

    @FXML
    private Button register;

    @FXML
    private Label registerMsg;

    @FXML
    private ImageView simplonIcon;
    @FXML
    private Button cancelBtn;
    @FXML
    private Label registerSuccess;
    @FXML
    private Label matchPass;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File imgPathSimplon = new File("img/simplo.png");
        Image imgPath = new Image(imgPathSimplon.toURI().toString());
        simplonIcon.setImage(imgPath);
    }



    public void cancelButtonOnAction(){
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void registerOnAction(){
        System.out.println("hello");
        if(!firstName.getText().isBlank() &&
                !lastname.getText().isBlank() &&
                !username.getText().isBlank()&&
                !password.getText().isBlank()&&
                !confirmPass.getText().isBlank())   {
            System.out.println("success");
        }else{
            registerMsg.setText("veuiller remplir tout les champs!!!");
        }
    }

    public void registerOnAction(ActionEvent event){
        registerSuccess.setText("vous etes bien enregister!!");
        registerValidation();
    }

    public void registerValidation(){
        if(password.getText().equals(confirmPass.getText())){
            matchPass.setText("le mot de passe se correspond");
        }else{
            matchPass.setText("le mot de passe ne se correspond pas!!");

        }
    }

}
