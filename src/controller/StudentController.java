package controller;

import db.DbConnect;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class StudentController implements Initializable {
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
    private ProgressBar niv1;
    @FXML
    private ProgressBar niv2;
    @FXML
    private ProgressBar niv3;


    // Buttons Levels Containers
    @FXML
    private HBox comp1Container, comp2Container, comp3Container, comp4Container, comp5Container, comp6Container;

    // load competences and levels automatically
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.loadCompetancesAndLevels();
        System.out.println("called");
        File imgPathSimplon = new File("img/profile.png");
        Image imgPath = new Image(imgPathSimplon.toURI().toString());

        imageProfile.setImage(imgPath);
    }

    // For loading competences and levels
    public void loadCompetancesAndLevels(){
        DbConnect dbConnect = new DbConnect();
        Connection connectDb = dbConnect.getConnect();


        String query = "SELECT * FROM competence\n" +
                "LEFT JOIN niveau ON niveau.competenceID = competence.idComp \n" +
                "UNION\n" +
                "SELECT * FROM competence\n" +
                "RIGHT JOIN niveau ON niveau.competenceID = competence.idComp ;";
        try {
            Statement statement = connectDb.createStatement();
            ResultSet queryResult = statement.executeQuery(query);
            while(queryResult.next()){
                // Getting some info from db
                String competanceID = queryResult.getString("competenceID");
                String compID = queryResult.getString("idComp");
                String titreNiveau = queryResult.getString("titreNiveau");

                comp1.setText(queryResult.getString("titreComp"));
                createLevel(compID, competanceID, titreNiveau, comp1Container);

                // checking all competences
                switch (compID){
                    case "1": {
                        comp1.setText(queryResult.getString("titreComp"));
                        createLevel(compID, competanceID, titreNiveau, comp1Container);
                        System.out.println("case 1: " + "compID: " + competanceID + " levelID: " + compID);
                        break;
                    }
                    case "2": {
                        comp2.setText(queryResult.getString("titreComp"));
                        createLevel(compID, competanceID, titreNiveau, comp2Container);
//                        System.out.println("case 2: " + "compID: " + competanceID + " compID: " + compID);
                        break;
                    }
                    case "3": {
                        comp3.setText(queryResult.getString("titreComp"));
                        createLevel(compID, competanceID, titreNiveau, comp3Container);
                        System.out.println("case 3: " + "compID: " + competanceID + " compID: " + compID);
                        break;
                    }
                    case "4": {
                        comp4.setText(queryResult.getString("titreComp"));
                        createLevel(compID, competanceID, titreNiveau, comp4Container);
                        System.out.println("case 4: " + "compID: " + competanceID + " compID: " + compID);
                        break;
                    }
                    case "5": {
                        comp5.setText(queryResult.getString("titreComp"));
                        createLevel(compID, competanceID, titreNiveau, comp5Container);
                        System.out.println("case 5: " + "compID: " + competanceID + " compID: " + compID);
                        break;
                    }
                    case "6": {
                        comp6.setText(queryResult.getString("titreComp"));
                        createLevel(compID, competanceID, titreNiveau, comp6Container);
                        System.out.println("case 6: " + "compID: " + competanceID + " compID: " + compID);
                        break;
                    }
                    default:
                        System.out.println("Unknown competence");
                }
            }
            statement.close();
            queryResult.close();

        }catch (Exception e){
//            e.printStackTrace();
            System.out.println(e.getMessage());
//            e.getCause();
        }
    }

    // For creating levels as buttons
    private void createLevel(String compID, String compeanceID, String btnText, HBox container){
        if (compID.equals(compeanceID)){
            Button b1 = new Button();
            b1.setText(btnText);
            container.getChildren().add(b1);
        }
    }

}
