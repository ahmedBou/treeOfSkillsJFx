package sample;

import controller.RegisterController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
<<<<<<< HEAD
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root, 520, 400));
        primaryStage.show();
    }
=======
    public void start(Stage stage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/register.fxml"));
        Parent root = loader.load();
        RegisterController myController = loader.getController();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
>>>>>>> main

        //Set Data to FXML through controller
        myController.promoChoice();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
