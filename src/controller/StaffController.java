package controller;


import db.DbConnect;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import static controller.LoginController.session;


public class StaffController implements Initializable {

    @FXML
    private BorderPane rootPane;


    @FXML
    public  Label NomCompApp;
    @FXML
    public Button app;

    @FXML
    public  HBox HbApp;
    @FXML
    public  VBox VbApp;
    @FXML
    public  AnchorPane AnchorListes;
    @FXML
    public    Label promoLabel;
    @FXML
    public    Label refLabel;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        remplirPromoLabel();
        remplirListeApprenant();
        remplirReferienciel();

    }

    public void remplirListeApprenant() {
        int i = 0;
        DbConnect connectNow = new DbConnect();
        Connection connectDb = connectNow.getConnect();
        String recupApp = "select a.nomApprenant ,a.prenomApprenant ,a.idApprenant from apprenant a , promoapprenant p , staff s where s.id_Promo = p.id_Promo AND s.idStaff ='"+session+"' AND a.idApprenant = p.idApprenant";
        System.out.println(recupApp);
        try {
            Statement statement = connectDb.createStatement();
            ResultSet queryResult = statement.executeQuery(recupApp);

            while (queryResult.next()) {
                i+=100;
                Button app1 = new Button();


                app1.setMinWidth(300);
                app1.setMinHeight(100);


                app1.setId(""+queryResult.getInt(3));
                System.out.println(queryResult.getString(1));
                app1.setText(queryResult.getString(1)+" "+queryResult.getString(2));


                app1.setLayoutY(i);
                app1.setLayoutX(120);
                AnchorListes.getChildren().add(app1);


                app1.setOnAction(
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event){
                                System.out.println(Integer.parseInt(app1.getId()));
                                int idApp = Integer.parseInt(app1.getId());
                                DbConnect connectNow = new DbConnect();
                                Connection connectDb = connectNow.getConnect();
                                String recupApp = "select a.nomApprenant ,a.prenomApprenant ,a.idApprenant from apprenant a , promoapprenant p , staff s where s.id_Promo = p.id_Promo AND s.idStaff ='"+session+"' AND a.idApprenant = p.idApprenant";
                                System.out.println(recupApp);

                                }
                            }
                );

                i += 30;
            }



        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        try {
            connectDb.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void remplirPromoLabel() {

        DbConnect connectNow = new DbConnect();
        Connection connectDb = connectNow.getConnect();

        String recupPromo = "SELECT p.titre_Promo FROM promotion p ,staff s WHERE p.id_Promo = s.id_Promo AND s.idStaff="+session;
        try {
            Statement st1 = connectDb.createStatement();
            ResultSet reqPromo = st1.executeQuery(recupPromo);
            while (reqPromo.next()) {
                System.out.println(reqPromo.getString("titre_Promo"));
                promoLabel.setText(reqPromo.getString(1));


            }
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void remplirReferienciel() {

        DbConnect connectNow = new DbConnect();
        Connection connectDb = connectNow.getConnect();

        String recupRef = "SELECT ref.titreRef from refereniel ref , promotion p , staff s WHERE ref.id_Promo = p.id_Promo AND s.id_Promo = p.id_Promo And s.idStaff="+session;
        try {
            Statement st2 = connectDb.createStatement();
            ResultSet reqRef = st2.executeQuery(recupRef);
            while (reqRef.next()) {
                //System.out.println(reqRef.getString("titre_Promo"));
                refLabel.setText(reqRef.getString(1));


            }
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}

//select a.nomApprenant , s.nomStaff , r.titreRef,pr.titre_Promo from apprenant a , promoapprenant p ,promotion pr , staff s ,refereniel r where a.idApprenant = p.idApprenant AND P.id_Promo = r.id_Promo And s.idStaff = 1 and P.id_Promo =s.id_Promo AND pr.id_Promo = p.id_Promo