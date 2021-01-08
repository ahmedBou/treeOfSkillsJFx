package sample;

import java.sql.*;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private TextField email;
    @FXML
    private TextField tel;
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

    ObservableList<String> items = FXCollections.observableArrayList("1er annees","2eme annees");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File imgPathSimplon = new File("img/simplo.png");
        Image imgPath = new Image(imgPathSimplon.toURI().toString());
        simplonIcon.setImage(imgPath);
    }


    public void cancelButtonOnAction() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void registerOnAction() {
        System.out.println("hello");
        if (!firstName.getText().isBlank() &&
                !lastname.getText().isBlank() &&
                !username.getText().isBlank() &&
                !password.getText().isBlank() &&
                !confirmPass.getText().isBlank()) {
            System.out.println("success");
        } else {
            registerMsg.setText("veuiller remplir tout les champs!!!");
        }
    }
    public void promoChoice(){
        DbConnect connectNow = new DbConnect();
        Connection connectDb = connectNow.getConnect();
//        comboBox.getItems().add("Choice 1");
//        comboBox.getItems().add("Choice 2");
//        comboBox.getItems().add("Choice 3");

    }

    public void registerOnAction(ActionEvent event) {
        if (password.getText().equals(confirmPass.getText())) {
            registerValidation();
        } else {
            matchPass.setText("le mot de passe ne se correspond pas!!");

        }

    }
    public void registerValidation(){
        DbConnect connectNow = new DbConnect();
        Connection connectDb = connectNow.getConnect();
        String firstname = firstName.getText();
        String lastName = lastname.getText();
        String userName = username.getText();
        String emailAdd = email.getText();
        String tels = tel.getText();
        String passwords = password.getText();

        int idApp = 4;
        String insertField = "INSERT INTO apprenant(idApp, nomApp, prenomApp, surnom,emailApp, tel, password) VALUES ('" ;
        String insertValues= idApp +"','" +firstname +"','"+ lastName +"','"+ userName +"','"+ emailAdd +"','"+ tels +"','"+ passwords + "')";
        String insertToRegister = insertField + insertValues;


        try {
            Statement statement = connectDb.createStatement();
            statement.executeUpdate(insertToRegister);
            registerSuccess.setText("vous etes bien enregister!!");


        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }


    }

}

