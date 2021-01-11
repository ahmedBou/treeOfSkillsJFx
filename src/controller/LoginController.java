package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import db.DbConnect;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;






public class LoginController implements Initializable {

    static  int session = 0;
    @FXML
    private Button cancelButton;

    private Button loginButton;

    @FXML
    private Label loginMessage;

    @FXML
    private ImageView imgHeadView;
    @FXML
    private ImageView imgSimplonView;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;







    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File imgPathSimplon = new File("img/simplon.jpeg");
        Image imgPath = new Image(imgPathSimplon.toURI().toString());
        imgSimplonView.setImage(imgPath);

        File imgPathCode = new File("img/code.png");
        Image imgPathHead = new Image(imgPathCode.toURI().toString());
        imgHeadView.setImage(imgPathHead);
    }

    public void LoginButtonOnAction(){
        if(!usernameField.getText().isBlank() && !passwordField.getText().isBlank()){

            validateLogin();
            createAccountForm();



        }else{

            loginMessage.setText("entrer votre surnom et votre mot de passe!");


        }
    }

    public void cancelButtonOnAction(){
        session = 0;
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void validateLogin(){
        DbConnect connectNow = new DbConnect();
        Connection connectDb = connectNow.getConnect();
        String verifyLogin = "SELECT * FROM staff WHERE nomStaff = '"
                + usernameField.getText() +"' And pswdStaff= '" +passwordField.getText()+"'AND isStaff = 1";

        try {
            Statement statement = connectDb.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()) {
                if (queryResult.getString("nomStaff").equals(usernameField.getText())) {

                    loginMessage.setText("success");

                    session = queryResult.getInt(2);
                    System.out.println(session);
                    System.out.println(queryResult.getString("nomStaff"));

                    //cancelButtonOnAction();


                } else {
                    loginMessage.setText("invalid mot de passe ou surnom, reessaye");
                }


            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }

    public void createAccountForm(){
        try{

         Parent root = FXMLLoader.load(getClass().getResource("/view/staff.fxml"));


        Stage registerStage = new Stage();
        registerStage.initStyle(StageStyle.UNDECORATED);
        registerStage.setScene(new Scene(root, 1520, 1095));
        registerStage.show();

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }






}