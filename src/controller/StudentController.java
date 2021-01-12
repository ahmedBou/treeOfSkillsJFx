package controller;

import db.DbConnect;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import static controller.LoginController.sessionApp;

public class StudentController implements Initializable, EventHandler {
    // Competances Labels
    @FXML
    private Label comp1;
    @FXML
    private Label comp2;
    @FXML
    private Label comp3;
    @FXML
    private Label comp4;
    @FXML
    private Label comp5;
    @FXML
    private Label comp6;
    @FXML
    ImageView imageProfile;

    @FXML
    ImageView simplonIcon;

    @FXML
    private Label cancelButton;

    @FXML
    private ProgressBar niv1;
    @FXML
    private ProgressBar niv2;
    @FXML
    private ProgressBar niv3;

    @FXML
    private Label sessionId;

    // Buttons Levels Containers
    @FXML
    private HBox comp1Container, comp2Container, comp3Container, comp4Container, comp5Container, comp6Container;
    // Levels ID's
    @FXML
    private Button comp1Level1, comp1Level2, comp1Level3,  comp2Level1, comp2Level2, comp2Level3,
            comp3Level1, comp3Level2, comp3Level3,  comp4Level1, comp4Level2, comp4Level3,
            comp5Level1, comp5Level2, comp5Level3,  comp6Level1, comp6Level2, comp6Level3;

    // store levels aka button ID's
    private String[] levels = {
            "comp1Level1", "comp1Level2", "comp1Level3",  "comp2Level1", "comp2Level2", "comp2Level3",
            "comp3Level1", "comp3Level2", "comp3Level3",  "comp4Level1", "comp4Level2", "comp4Level3",
            "comp5Level1", "comp5Level2", "comp5Level3",  "comp6Level1", "comp6Level2", "comp6Level3"
    };

    public void remplirLabelBonjour(){
        DbConnect connectNow = new DbConnect();
        Connection connectDb = connectNow.getConnect();
        String recupNom = "SELECT nomApprenant FROM apprenant WHERE idApprenant = "+sessionApp;
        System.out.println(recupNom);

        try {
            Statement statement = connectDb.createStatement();
            ResultSet queryResult = statement.executeQuery(recupNom);
            while(queryResult.next()){
                sessionId.setText(queryResult.getString(1));

            }

        } catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }



    // load competences and levels automatically
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        this.loadCompetancesAndLevels();
        fillLevelComp();
        remplirLabelBonjour();
        System.out.println("called");
        File imgPathProfile = new File("img/profile.png");
        Image imgPath = new Image(imgPathProfile.toURI().toString());
        imageProfile.setImage(imgPath);

        File imgPathSimplon = new File("img/simplo.png");
        Image imgPathP = new Image(imgPathSimplon.toURI().toString());
        simplonIcon.setImage(imgPathP);
    }

    public void cancelButtonOnAction(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    //    select ca.*, c.titreComp , a.nomApprenant ,n.titreNiveau from apprenant a ,competence c ,compapprenant ca ,niveau n where c.idComp = ca.idComp AND a.idApprenant = ca.idApprenant AND c.idNiveau = n.idNiveau

    // For loading competences and levels
    public void fillLevelComp(){
        DbConnect dbConnect = new DbConnect();
        Connection connectDb = dbConnect.getConnect();

        String queryFill = "select titreComp, idComp from competence ";

        try{
            Statement statement = connectDb.createStatement();
            ResultSet queryFillRes = statement.executeQuery(queryFill);
            while(queryFillRes.next()){
                System.out.println("Fill competence and level : "+ queryFillRes.getString(1));

                String compt = queryFillRes.getString("idComp");
                switch (compt){
                    case "1":{
                        comp1.setText(queryFillRes.getString("titreComp"));
                        break;

                    }
                    case "2":{
                        comp2.setText(queryFillRes.getString("titreComp"));
                        break;

                    }
//                    case "3":{
//                        comp3.setText(queryFillRes.getString("titreComp"));
//                        break;
//                    }
//                    case "4":{
//                        comp4.setText(queryFillRes.getString("titreComp"));
//                        break;
//
//                    }
                }


            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    // check current clicked level
    public void checkCurrentLevel() {

    }

    // Insert current clicked level to database
    private void insertCurrentLevel(int levelID, int competenceID){
        // Open connection to database
        DbConnect dbConnect = new DbConnect();
        Connection connectDb = dbConnect.getConnect();

        // Query that expect to be executed
        String query = "UPDATE competence SET idNiveau = '" + levelID + "' WHERE idComp = " + competenceID + ";";
        System.out.println(query);
        try {
            Statement statement  = connectDb.createStatement();
            statement.executeUpdate(query);
//            connectDb.commit();
            System.out.println("Database updated successfully ");

            // Close connection
            statement.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }







    public void loadCompetancesAndLevels(){
//        DbConnect dbConnect = new DbConnect();
//        Connection connectDb = dbConnect.getConnect();
//
//
//        String query = "SELECT * FROM competence\n" +
//                "LEFT JOIN niveau ON niveau.competenceID = competence.idComp \n" +
//                "UNION\n" +
//                "SELECT * FROM competence\n" +
//                "RIGHT JOIN niveau ON niveau.competenceID = competence.idComp ;";
//        try {
//            Statement statement = connectDb.createStatement();
//            ResultSet queryResult = statement.executeQuery(query);
//            while(queryResult.next()){
//                // Getting some info from db
//                String competanceID = queryResult.getString("competenceID");
//                String compID = queryResult.getString("idComp");
//                String titreNiveau = queryResult.getString("titreNiveau");
//
//                comp1.setText(queryResult.getString("titreComp"));
//                createLevel(compID, competanceID, titreNiveau, comp1Container);
//
//                // checking all competences
//                switch (compID){
//                    case "1": {
//                        comp1.setText(queryResult.getString("titreComp"));
//                        createLevel(compID, competanceID, titreNiveau, comp1Container);
//                        System.out.println("case 1: " + "compID: " + competanceID + " levelID: " + compID);
//                        break;
//                    }
//                    case "2": {
//                        comp2.setText(queryResult.getString("titreComp"));
//                        createLevel(compID, competanceID, titreNiveau, comp2Container);
////                        System.out.println("case 2: " + "compID: " + competanceID + " compID: " + compID);
//                        break;
//                    }
//                    case "3": {
//                        comp3.setText(queryResult.getString("titreComp"));
//                        createLevel(compID, competanceID, titreNiveau, comp3Container);
//                        System.out.println("case 3: " + "compID: " + competanceID + " compID: " + compID);
//                        break;
//                    }
//                    case "4": {
//                        comp4.setText(queryResult.getString("titreComp"));
//                        createLevel(compID, competanceID, titreNiveau, comp4Container);
//                        System.out.println("case 4: " + "compID: " + competanceID + " compID: " + compID);
//                        break;
//                    }
//                    case "5": {
//                        comp5.setText(queryResult.getString("titreComp"));
//                        createLevel(compID, competanceID, titreNiveau, comp5Container);
//                        System.out.println("case 5: " + "compID: " + competanceID + " compID: " + compID);
//                        break;
//                    }
//                    case "6": {
//                        comp6.setText(queryResult.getString("titreComp"));
//                        createLevel(compID, competanceID, titreNiveau, comp6Container);
//                        System.out.println("case 6: " + "compID: " + competanceID + " compID: " + compID);
//                        break;
//                    }
//                    default:
//                        System.out.println("Unknown competence");
//                }
//            }
//            statement.close();
//            queryResult.close();
//
//        }catch (Exception e){
////            e.printStackTrace();
//            System.out.println(e.getMessage());
////            e.getCause();
//        }
//    }
//
//    // For creating levels as buttons
//    private void createLevel(String compID, String compeanceID, String btnText, HBox container){
//        if (compID.equals(compeanceID)){
//            Button b1 = new Button();
//            b1.setText(btnText);
//            container.getChildren().add(b1);
//        }
    }

    @Override
    public void handle(Event event) {
        // For competence 1
        if (event.getSource() == comp1Level1){
            System.out.println("comp1Level1 clicked");
            insertCurrentLevel(1, 1);

        }else if (event.getSource() == comp1Level2){
            System.out.println("comp1Level2 clicked");
            insertCurrentLevel(2, 1);

        }else if (event.getSource() == comp1Level3){
            System.out.println("comp1Level3 clicked");
            insertCurrentLevel(3, 1);

        }

        // For competence 2
        else if (event.getSource() == comp2Level1){
            System.out.println("comp2Level1 clicked");
            insertCurrentLevel(1, 2);

        }else if (event.getSource() == comp2Level2){
            System.out.println("comp2Level2 clicked");
            insertCurrentLevel(2, 2);

        }else if (event.getSource() == comp2Level3){
            System.out.println("comp2Level3 clicked");
            insertCurrentLevel(3, 2);

        }

        // For competence 3
        else if (event.getSource() == comp3Level1){
            System.out.println("comp3Level1 clicked");
            insertCurrentLevel(1, 3);

        }else if (event.getSource() == comp3Level2){
            System.out.println("comp3Level2 clicked");
            insertCurrentLevel(2, 3);

        }else if (event.getSource() == comp3Level3){
            System.out.println("comp3Level3 clicked");
            insertCurrentLevel(3, 3);

        }

        // For competence 4
        else if (event.getSource() == comp4Level1){
            System.out.println("comp4Level1 clicked");
            insertCurrentLevel(1, 4);

        }else if (event.getSource() == comp4Level2){
            System.out.println("comp4Level2 clicked");
            insertCurrentLevel(2, 4);

        }else if (event.getSource() == comp4Level3){
            System.out.println("comp4Level3 clicked");
            insertCurrentLevel(3, 4);

        }

        // For competence 5
        else if (event.getSource() == comp5Level1){
            System.out.println("comp5Level1 clicked");
            insertCurrentLevel(1, 5);

        }else if (event.getSource() == comp5Level2){
            System.out.println("comp5Level2 clicked");
            insertCurrentLevel(2, 5);

        }else if (event.getSource() == comp5Level3){
            System.out.println("comp5Level3 clicked");
            insertCurrentLevel(3, 5);

        }

        // For competence 6
        else if (event.getSource() == comp6Level1){
            System.out.println("comp6Level1 clicked");
            insertCurrentLevel(1, 6);

        }else if (event.getSource() == comp6Level2){
            System.out.println("comp6Level2 clicked");
            insertCurrentLevel(2, 6);

        }else if (event.getSource() == comp6Level3){
            System.out.println("comp6Level3 clicked");
            insertCurrentLevel(3, 6);

        }

    }

}
