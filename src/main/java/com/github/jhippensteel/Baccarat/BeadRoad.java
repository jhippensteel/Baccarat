/**
 * Filename: BeadRoad.java
 * 
 * Author: Jaysen R. Hippensteel
 * 
 * Description: Contains UI/UI logic for Bead Road Screen
 * 
 * Last Modified: 1-19-26
 */
package com.github.jhippensteel.Baccarat;

import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.beans.value.*;
import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BeadRoad {
    private VBox content;
    private Baccarat gameState;
    private GridPane grid;
    private Label listenerLabel;
    private ArrayList<Hand[]> beadPlate;

    public BeadRoad(Baccarat gameState, Label listenerLabel){
        content = new VBox();
        grid = new  GridPane();
        content.getChildren().add(grid);
        this.gameState = gameState;
        this.listenerLabel = listenerLabel;
        beadPlate = gameState.getBeadPlate();

        this.listenerLabel.textProperty().addListener((observable, oldValue, newValue) -> {
            updateUI();
        });
    }

    private void updateUI() {
        System.out.println("Update UI Called");
        grid.getChildren().clear();
        StackPane stackPane;
        Circle circle;
        Label handScore;

        for (int column = 0;column < beadPlate.size();column++) {
            for (int row = 0; row < beadPlate.get(column).length; row++) {
                stackPane = new StackPane();
                circle = new Circle(10);

                if(beadPlate.get(column)[row] == null){
                    stackPane.setMinSize(20,20);
                    grid.add(stackPane, column, row);
                    System.out.println("Null Node at" + row + " " + column);
                    continue;
                }

                if (beadPlate.get(column)[row].getWinner().equals("p")){
                    circle.setFill(Color.BLUE);
                    circle.setStroke(Color.BLUE);
                    handScore = new Label(""+beadPlate.get(column)[row].getPlayerTotal());
                    handScore.setTextFill(Color.WHITE);
                    stackPane.getChildren().addAll(circle, handScore);
                    grid.add(stackPane, column, row);
                }
                if (beadPlate.get(column)[row].getWinner().equals("b")){
                    circle.setFill(Color.RED);
                    circle.setStroke(Color.RED);
                    handScore = new Label(""+beadPlate.get(column)[row].getBankerTotal());
                    handScore.setTextFill(Color.WHITE);
                    stackPane.getChildren().addAll(circle, handScore);
                    grid.add(stackPane, column, row);
                }
                if (beadPlate.get(column)[row].getWinner().equals("t")){
                    circle.setFill(Color.GREEN);
                    circle.setStroke(Color.GREEN);
                    handScore = new Label(""+beadPlate.get(column)[row].getPlayerTotal());
                    handScore.setTextFill(Color.WHITE);
                    stackPane.getChildren().addAll(circle, handScore);
                    grid.add(stackPane, column, row);
                }
            }
        }
    }

    public VBox getContent() {
        return content;
    }
}