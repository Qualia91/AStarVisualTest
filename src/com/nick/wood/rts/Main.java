package com.nick.wood.rts;

import com.nick.wood.rts.controller.MainWindowController;
import com.nick.wood.rts.model.MainModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        MainModel mainModel = new MainModel();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/MainWindowView.fxml"));

        loader.setControllerFactory(controller -> {
            if (controller.equals(MainWindowController.class)) {
                return new MainWindowController(mainModel);
            }
            else {
                throw new RuntimeException("Failed to create controller");
            }
        });

        Parent root = loader.load();

        primaryStage.setTitle("RTS");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
