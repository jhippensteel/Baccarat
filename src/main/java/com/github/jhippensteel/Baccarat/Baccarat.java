package com.github.jhippensteel.Baccarat;

import java.util.Random;

public class Baccarat {
    private int[] playerCards;
    private int[] bankerCards;
    private int playerTotal;
    private int bankerTotal;

    public Baccarat() {
        playerCards = new int[3];
        bankerCards = new int[3];
        playerTotal = 0;
        bankerTotal = 0;
    }

    public void dealCards() {
        // Logic to deal cards to player and banker
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
}