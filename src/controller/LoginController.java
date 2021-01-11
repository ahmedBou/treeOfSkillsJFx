package controller;
import java.sql.*;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import db.DbConnect;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;



public class LoginController implements Initializable {
    static  int session = 0;
    @FXML
    private Button cancelButton;

//    private Button loginButton;

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
        if(!usernameField.getText().isBlank() &&
                !passwordField.getText().isBlank()){
            validateLogin();

        }else{
            loginMessage.setText("Veuiller entrer votre surnom et votre mot de passe!");

        }
    }

    public void cancelButtonOnAction(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    public void toRegisterForm(){
        try{

            Parent root = FXMLLoader.load(getClass().getResource("/view/register.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 520, 550));
            registerStage.show();

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void validateLogin(){
        DbConnect connectNow = new DbConnect();
        Connection connectDb = connectNow.getConnect();
        String verifyLogin = "SELECT * FROM apprenant WHERE surnom = '"
                + usernameField.getText() +"' And password= '"+passwordField.getText()+"'AND isStaff = 1";
        System.out.println(verifyLogin);

        try {
            Statement statement = connectDb.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while(queryResult.next()){
                System.out.println(usernameField.getText().length());
                System.out.println(queryResult.getString("surnom").length());
                System.out.println(usernameField.getText().equals(queryResult.getString("surnom")));

                if(queryResult.getString("surnom").equals(usernameField.getText())){

                    loginMessage.setText("success");
                    createStudentPage();
                    remplirListeApprenant();

                }else{
                    loginMessage.setText("invalid mot de passe ou surnom, reessaye");
                }
            }



        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void createStudentPage(){
        try{

        Parent root = FXMLLoader.load(getClass().getResource("/view/student.fxml"));
        Stage registerStage = new Stage();
        registerStage.initStyle(StageStyle.UNDECORATED);
        registerStage.setScene(new Scene(root, 520, 500));
        registerStage.show();

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void remplirListeApprenant() {
        VBox VbApp = new VBox();
        VbApp.setMaxSize(220,70);

        HBox HbApp = new HBox();
        HbApp.setMaxSize(220,70);


        DbConnect connectNow = new DbConnect();
        Connection connectDb = connectNow.getConnect();
        String recupApp = "select a.nomApp from apprenant a , promoapprenant p , staff s where s.idPromo = p.idPromo AND s.idStaff ='" +session+ "' AND a.idApp = p.idApp";

        try {
            Statement statement = connectDb.createStatement();
            ResultSet queryResult = statement.executeQuery(recupApp);
            while(queryResult.next()){
                // NomCompApp.setText(queryResult.getInt(2)+" "+queryResult.getInt(3));

                // HbApp.getChildren().add(NomCompApp);
                String x = queryResult.getString(1);
                System.out.println(x);
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }

}
