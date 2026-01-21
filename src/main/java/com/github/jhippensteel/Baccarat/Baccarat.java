/**
 * Filename: Baccarat.java
 * 
 * Author: Jaysen R. Hippensteel
 * 
 * Description: Tracks and implements all background game logic
 * 
 * Last Modified: 1-21-26
 */
package com.github.jhippensteel.Baccarat;

import java.util.Random;
import java.util.ArrayList;

public class Baccarat {
    private int[] playerCards;
    private int[] bankerCards;
    private int playerTotal;
    private int bankerTotal;
    private ArrayList<Hand[]> beadPlate;
    private int gameNumber;
    private Hand currentHand;
    private int beadRow;
    private ArrayList<Hand[]> bigRoad;
    private int bigRow;
    private Hand prevHand;
    private int bigCol;
    private int lastOpenBigCol;

    public Baccarat() {
        playerCards = new int[3];
        bankerCards = new int[3];
        playerTotal = 0;
        bankerTotal = 0;
        beadPlate = new ArrayList<Hand[]>();
        beadPlate.add(new Hand[6]);
        gameNumber = 0;
        beadRow = 0;
        bigRoad = new ArrayList<Hand[]>();
        bigRoad.add(new Hand[6]);
        bigRow = -1;
        bigCol = 0;
        lastOpenBigCol = 1;
    }

    public void dealCards(){
        dealHand();
        prevHand = currentHand;
        currentHand = new Hand(playerCards, playerTotal, bankerCards, bankerTotal);
        updateBeadPlate();
        updateBigRoad();
    }

    private void dealHand() {
        ++gameNumber;
        // Logic to deal cards to player and banker
        playerCards = new int[3];
        bankerCards = new int[3];
        playerTotal = 0;
        bankerTotal = 0;
        playerCards[0] = drawCard();
        if (playerCards[0] < 10){
            playerTotal += playerCards[0];
        }
        bankerCards[0] = drawCard();
        if (bankerCards[0] < 10){
            bankerTotal += bankerCards[0];
        }

        playerCards[1] = drawCard();
        if (playerCards[1] < 10){
            playerTotal += playerCards[1];
            playerTotal = playerTotal % 10;
        }
        bankerCards[1] = drawCard();
        if (bankerCards[1] < 10){
            bankerTotal += bankerCards[1];
            bankerTotal = bankerTotal % 10;
        }
        
        if (playerTotal >= 8 || bankerTotal >= 8) {
            // Natural win, no more cards
            return;
        }
        if (playerTotal > 5) {
            // Player stands, banker draws if 5 or less
            if (bankerTotal <= 5) {
                bankerCards[2] = drawCard();
                if (bankerCards[2] < 10){
                    bankerTotal += bankerCards[2];
                    bankerTotal = bankerTotal % 10;
                }
            }
            return;
        }
        // Player draws a third card
        playerCards[2] = drawCard();
        if (playerCards[2] < 10){
            playerTotal += playerCards[2];
            playerTotal = playerTotal % 10;
        }
        // Banker rules based on player's third card
        if (bankerTotal <= 2 ||
        (bankerTotal == 3 && playerCards[2] != 8) ||
        (bankerTotal == 4 && !(playerCards[2] >= 2 && playerCards[2] <= 7)) ||
        (bankerTotal == 5 && !(playerCards[2] >= 4 && playerCards[2] <= 7)) ||
        (bankerTotal == 6 && !(playerCards[2] == 6 || playerCards[2] == 7))) {
            bankerCards[2] = drawCard();
            if (bankerCards[2] < 10){
                bankerTotal += bankerCards[2];
                bankerTotal = bankerTotal % 10;
            }
        }
    }

    private int drawCard() {
        Random rand = new Random();
        return rand.nextInt(13) + 1; // Cards from 1 to 13
    }

    private void updateBeadPlate() {
        beadPlate.get(beadPlate.size()-1)[beadRow] = currentHand;
        ++beadRow;
        if (beadRow == 6){
            beadPlate.add(new Hand[6]);
            beadRow = 0;
        }
    }

    private void updateBigRoad() {
        ++bigRow;
        if (prevHand == null) {
            bigRoad.get(0)[0] = currentHand;
            return;
        }

        if (bigRow == 6 || bigRoad.get(bigCol)[bigRow] != null){

            if (currentHand.getWinner().equals(prevHand.getWinner())){
                if(bigCol == bigRoad.size()-1){
                    bigRoad.add(new Hand[6]);
                }
                
                ++bigCol;
                --bigRow;
                if (bigRow == 0) {
                    ++lastOpenBigCol;
                }
                bigRoad.get(bigCol)[bigRow] = currentHand;
            }
            
            else {
                if (lastOpenBigCol == bigRoad.size()) {
                    bigRoad.add(new Hand[6]);
                }
                bigCol = lastOpenBigCol;
                ++lastOpenBigCol;
                bigRow = 0;
                bigRoad.get(bigCol)[bigRow] = currentHand;
            }
        }
        
        else {

            if (currentHand.getWinner().equals(prevHand.getWinner())) {
                bigRoad.get(bigCol)[bigRow] = currentHand;
            }
            else {
                if (lastOpenBigCol == bigRoad.size()) {
                    bigRoad.add(new Hand[6]);
                }
                bigCol = lastOpenBigCol;
                ++lastOpenBigCol;
                bigRow = 0;
                bigRoad.get(bigCol)[bigRow] = currentHand;
            }
        }
    }

    public int[] getPlayerCards() {
        return playerCards;
    }
    public int[] getBankerCards() {
        return bankerCards;
    }
    public int getPlayerTotal() {
        return playerTotal;
    }
    public int getBankerTotal() {
        return bankerTotal;
    }

    public ArrayList<Hand[]> getBeadPlate() {
        return beadPlate;
    }

    public ArrayList<Hand[]> getBigRoad() {
        return bigRoad;
    }
}