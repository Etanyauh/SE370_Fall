package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.UsersBag;

import java.io.File;
import java.io.IOException;

/**
 * Main application class.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        stage.setTitle("Email Client");
//        File data_dir = new File(System.getProperty("user.dir")+"/data/");
//        if(!data_dir.exists()){
//        	System.out.println("Creating data directory.");
//        	data_dir.mkdirs();
//        }
//        File users = new File(System.getProperty("user.dir")+"/data"+ File.separator+"UsersBag.ser");
//        System.out.println(users.getPath());
//        if(!users.exists()) {
//            System.out.println("Creating UsersBag.ser file");
//            users.createNewFile();
//        } else {
//            System.out.println("Loading UsersBag.ser file");
//            UsersBag.load();
//        }
        stage.setScene(
            createScene(
                loadMainPane()
            )
        );	

        stage.show();
    }


    private Pane loadMainPane() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = (Pane) loader.load(
            getClass().getResourceAsStream(
                ViewNavigator.MAIN
            )
        );

        MainController mainController = loader.getController();

        ViewNavigator.setMainController(mainController);
        ViewNavigator.loadScreen(ViewNavigator.SIGN_IN);

        return mainPane;
    }

 
    private Scene createScene(Pane mainPane) {
        Scene scene = new Scene(
            mainPane
        );

        scene.getStylesheets().setAll(
            getClass().getResource("vista.css").toExternalForm()
        );

        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
