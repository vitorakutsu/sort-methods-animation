package org.example.animacaoordenacao;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.animacaoordenacao.Methods.AnimationUtils;
import org.example.animacaoordenacao.Methods.ArrayUtils;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        AnchorPane pane = new AnchorPane();
        ArrayUtils.startButton(stage, pane);
        ArrayUtils.initializeArray(pane);

        Scene scene = new Scene(pane, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
