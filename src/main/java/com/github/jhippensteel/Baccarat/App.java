package com.github.jhippensteel.Baccarat;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;


public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Baccarat gameState = new Baccarat();
        GameBoard board = new GameBoard(gameState);
        Scene scene = new Scene(board.createContent(), 400, 300);
        stage.setTitle("Baccarat");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
