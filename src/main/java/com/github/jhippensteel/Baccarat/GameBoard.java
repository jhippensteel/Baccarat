/**
 * Filename: GameBoard.java
 * 
 * Author: Jaysen R. Hippensteel
 * 
 * Description: Wrapper class for UI. Contains various Baccarat screens.
 * 
 * Last Modified: 1-21-26
 */
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
        GridPane root = new GridPane();
        root.setId("game-board");
        BetArea betArea = new BetArea(gameState);
        BeadRoad beadRoad = new BeadRoad(gameState, betArea.getBankerTotal());
        BigRoad bigRoad = new BigRoad(gameState, betArea.getBankerTotal());
        root.add(bigRoad.getContent(), 0, 0);
        root.add(betArea.getContent(), 2, 0);
        root.add(beadRoad.getContent(), 1, 0);
        return root;
    }
}

