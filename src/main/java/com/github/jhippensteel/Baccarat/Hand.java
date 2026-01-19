/**
 * Filename: Hand.java
 * 
 * Author Jaysen R Hippensteel
 * 
 * Description: Contains data for a hand in a baccarat round
 * 
 * Last Modified: 1-19-26
 */
package com.github.jhippensteel.Baccarat;

public class Hand {
    private int[] playerCards;
    private int[] bankerCards;
    private int playerTotal;
    private int bankerTotal;
    private String winner;
    private boolean playerPair;
    private boolean bankerPair;

    public Hand(int[] pCards, int pTotal, int[] bCards, int bTotal) {
        this.playerCards = pCards;
        this.playerTotal = pTotal;
        this.bankerCards = bCards;
        this.bankerTotal = bTotal;

        if (pTotal > bTotal){
            this.winner = "p";
        }
        else if (bTotal > pTotal){
            this.winner = "b";
        }
        else {
            this.winner = "t";
        }

        if (pCards[0] == pCards[1] || pCards[0] == pCards[2] ||
            pCards[1] == pCards[2]){
                this.playerPair = true;
        }
        else this.playerPair = false;
        if (bCards[0] == bCards[1] || bCards[0] == bCards[2] ||
            bCards[1] == bCards[2]){
                this.bankerPair= true;
        }
        else this.bankerPair = false;
    }

    public String getWinner() {
        return winner;
    }

    public int getPlayerTotal() {
        return playerTotal;
    }

    public int getBankerTotal() {
        return bankerTotal;
    }
}