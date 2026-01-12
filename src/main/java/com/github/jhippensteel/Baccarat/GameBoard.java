package com.github.jhippensteel.Baccarat;

import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.event.*;
import javafx.scene.control.*;
import java.util.Random;
import java.lang.Thread;

public class GameBoard {
    private Baccarat gameState;

    public GameBoard(Baccarat gameState) {
        this.gameState = gameState;
    }

    public Parent createContent() {
        TilePane root = new TilePane();
        VBox tile = new VBox();
        Text text = new Text("Hello, JavaFX!");

        final int[] counter = {0};
        Button button = new Button("Click Me");
        Label label = new Label();
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                counter[0]++;
                label.setText("Button Clicked " + counter[0] + " times!");
            }
        };
        button.setOnAction(event);
        tile.getChildren().addAll(text, button, label);
        root.getChildren().add(tile);
        BetArea betArea = new BetArea(gameState);
        root.getChildren().add(betArea.getContent());
        return root;
    }
}

