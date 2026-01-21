/**
 * Filename: BigRoad.java
 * 
 * Author: Jaysen R. Hippensteel
 * 
 * Description: Contains UI/UI logic for Big Road Screen
 * 
 * Last Modified: 1-21-26
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

public class BigRoad {
    private VBox content;
    private Baccarat gameState;
    private GridPane grid;
    private Label listenerLabel;
    private ArrayList<Hand[]> bigRoad;

    public BigRoad(Baccarat gameState, Label listenerLabel){
        content = new VBox();
        content.setId("big-road");
        grid = new  GridPane();
        grid.getStyleClass().add("bead-grid");
        content.getChildren().add(grid);
        this.gameState = gameState;
        this.listenerLabel = listenerLabel;
        bigRoad = gameState.getBigRoad();

        this.listenerLabel.textProperty().addListener((observable, oldValue, newValue) -> {
            updateUI();
        });
    }

    private void updateUI() {
        System.out.println("Update Big UI Called");
        grid.getChildren().clear();
        StackPane stackPane;
        Circle circle;
        Label handScore;

        for (int column = 0;column < bigRoad.size();column++) {
            for (int row = 0; row < bigRoad.get(column).length; row++) {
                stackPane = new StackPane();
                circle = new Circle(15);

                if(bigRoad.get(column)[row] == null){
                    stackPane.setMinSize(30,30);
                    grid.add(stackPane, column, row);
                    System.out.println("Null Node at" + row + " " + column);
                    continue;
                }

                if (bigRoad.get(column)[row].getWinner().equals("p")){
                    circle.setFill(Color.TRANSPARENT);
                    circle.setStroke(Color.BLUE);
                    circle.setStrokeWidth(5);
                    stackPane.getChildren().add(circle);
                    grid.add(stackPane, column, row);
                }
                if (bigRoad.get(column)[row].getWinner().equals("b")){
                    circle.setFill(Color.TRANSPARENT);
                    circle.setStroke(Color.RED);
                    circle.setStrokeWidth(5);
                    stackPane.getChildren().add(circle);
                    grid.add(stackPane, column, row);
                }
                if (bigRoad.get(column)[row].getWinner().equals("t")){
                    circle.setFill(Color.TRANSPARENT);
                    circle.setStroke(Color.GREEN);
                    circle.setStrokeWidth(5);
                    stackPane.getChildren().add(circle);
                    grid.add(stackPane, column, row);
                }
            }
        }
    }

    public VBox getContent() {
        return content;
    }
}