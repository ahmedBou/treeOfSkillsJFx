package sample;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

    @FXML
    private BorderPane rootPane;

    @FXML
    private AnchorPane anchor1;


    @FXML
    private Label  NomCompApp;


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
            remplirListeAppreanant();


        }else{

            loginMessage.setText("entrer votre surnom et votre mot de passe!");


        }
    }

    public void cancelButtonOnAction(){
        //session = 0;
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
            while(queryResult.next()){
                if(queryResult.getString("nomStaff").equals(usernameField.getText())){

                    loginMessage.setText("success");
                    createAccountForm();
                    session = queryResult.getInt(2);
                    System.out.println(session);

                }else{
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
        Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
        Stage registerStage = new Stage();
        registerStage.initStyle(StageStyle.UNDECORATED);
        registerStage.setScene(new Scene(root, 520, 695));
        registerStage.show();

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    /*public void createFormApp(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("staff.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 520, 695));
            registerStage.show();

        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    */


    public void remplirListeAppreanant() {
        VBox VbApp = new VBox();
        VbApp.setMaxSize(220,70);

        HBox HbApp = new HBox();
        HbApp.setMaxSize(220,70);


        DbConnect connectNow = new DbConnect();
        Connection connectDb = connectNow.getConnect();
        String recupApp = "select a.nomApprenant from apprenant a , promoapprenant p , staff s where s.id_Promo = p.id_Promo AND s.idStaff ='" +session+ "' AND a.idApprenant = p.idApprenant";

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
