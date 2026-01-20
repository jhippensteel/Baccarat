/**
 * Filename: BetArea.java
 * 
 * Author: Jaysen R. Hippensteel
 * 
 * Description: Contains UI/UI logic for the bet area
 * 
 * Last Modified: 1-19-26
 */
package com.github.jhippensteel.Baccarat;

import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.event.*;
import javafx.scene.control.*;
import java.util.Random;
import java.lang.Thread;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.geometry.Insets;

public class BetArea {
    private HBox content;
    private VBox playerColumn;
    private VBox bankerColumn;
    private VBox tieColumn;
    private Button playerButton;
    private Button bankerButton;
    private Button tieButton;
    private HBox playerCardRow;
    private HBox bankerCardRow;
    private Label playerTotal;
    private Label bankerTotal;
    private Baccarat gameState;

    public BetArea(Baccarat gameState) {
        this.gameState = gameState;
        content = new HBox(75);
        playerColumn = new VBox();
        bankerColumn = new VBox();
        tieColumn = new VBox();
        content.getChildren().addAll(playerColumn, bankerColumn, tieColumn);
        
        playerButton = new Button("Player");
        playerButton.setOnAction(e -> dealCards());
        bankerButton = new Button("Banker");
        bankerButton.setOnAction(e -> dealCards());
        tieButton = new Button("Tie");
        tieButton.setOnAction(e -> dealCards());

        playerCardRow = new HBox();
        bankerCardRow = new HBox();
        playerTotal = new Label("");
        bankerTotal = new Label("");

        playerColumn.getChildren().addAll(playerButton, playerCardRow, playerTotal);
        bankerColumn.getChildren().addAll(bankerButton, bankerCardRow, bankerTotal);
        tieColumn.getChildren().add(tieButton);

        
    }

    private void dealCards() {
        playerCardRow.getChildren().clear();
        bankerCardRow.getChildren().clear();
        playerTotal.setText("");
        bankerTotal.setText("");
        gameState.dealCards();
        // Update UI with dealt cards and totals
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> updateUI(0, true));
        pause.play();
    }
    private void updateUI(int stage, boolean isPlayer) {
        if (stage == 3) {
            if (isPlayer) {
                playerTotal.setText(""+gameState.getPlayerTotal());
                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(event -> updateUI(stage, false));
                pause.play();
                return;
            }
            else {
                bankerTotal.setText(""+gameState.getBankerTotal());
                return;
            }
        }
        if (isPlayer) {
            if (gameState.getPlayerCards()[stage] != 0) {
                Label playerCard = new Label("" + gameState.getPlayerCards()[stage]);
                playerCard.getStyleClass().add("bordered-label");
                playerCardRow.getChildren().add(playerCard);
            }
            else if (gameState.getBankerCards()[stage] != 0) {
                updateUI(stage, false);
                return;
            }
            else {
                updateUI(stage + 1, true);
                return;
            }
            if (gameState.getBankerCards()[stage] != 0) {
                PauseTransition pause = new PauseTransition(Duration.seconds(2));
                pause.setOnFinished(event -> updateUI(stage, false));
                pause.play();
                return;
            }
            else{
                PauseTransition pause = new PauseTransition(Duration.seconds(2));
                pause.setOnFinished(event -> updateUI(stage + 1, true));
                pause.play();
                return;
            }
        }
        if (!isPlayer) {
            Label bankerCard = new Label("" + gameState.getBankerCards()[stage]);
            bankerCard.getStyleClass().add("bordered-label");
            bankerCardRow.getChildren().add(bankerCard);
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> updateUI(stage + 1, true));
            pause.play();
            return;
        }
    }
    public HBox getContent() {
        return content;
    }

    public Label getBankerTotal() {
        return bankerTotal;
    }
}