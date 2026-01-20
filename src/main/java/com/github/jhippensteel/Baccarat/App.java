/**
 * Filename: App.java
 * Author: Jaysen R. Hippensteel
 * 
 * Description: Starting point of application. Initializes game logic and 
 *              outermost UI wrapper. Extends from JavaFX Application class.
 * 
 * Last Modified: 1-19-26
 */
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
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        stage.setTitle("Baccarat");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
