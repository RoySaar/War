package com.saar.roy.myapplication;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

/**
 * Created by roymac on 12/03/2017.
 */

public class Game {

    private int[] nums;
    private Player player1;
    private Player player2;
    public static final int PLAYER2_WON = 0;
    public static final int PLAYER1_WON = 1;
    public static final int TIE = 2;
    public static final int NOT_OVER = 2;


    public Game() {
        this.nums = new int[52];
        for (int i = 0; i < 52; i++) {
            nums[i] = i;
        }
        player1 = new Player();
        player2 = new Player();
        player1.addCardsToDeck(generatePack());
        player2.addCardsToDeck(generatePack());
    }

    public int[] getNums(){
        return nums;
    }

    public Queue<Card> generatePack() {

        Queue<Card> cards = new LinkedList<Card>();
        Random rnd = new Random();
        for (int i = 0; i < 26; i++) {
            int num = rnd.nextInt(52);
            while (nums[num] == -1) {
                num = rnd.nextInt(52);
            }
            cards.add(new Card(num));
            nums[num] = -1;
        }
        return cards;
    }

    public boolean bothPlayed() {
        if (player1.getField().isEmpty() || player2.getField().isEmpty())
            return false;
        else
            return true;
    }

    public void playEach() {
        player1.throwCard();
        player2.throwCard();
    }

    public void playPLayer1() {
        player1.throwCard();
    }

    public void playPLayer2() {
        player2.throwCard();
    }

    public int handlePlay() {
        if (player1.getField().peek().value > player2.getField().peek().value) {
            player1.addCardsToDeck(player2.getField());
            player1.addCardsToDeck(player1.getField());
            player1.clearField();
            player2.clearField();
            return PLAYER1_WON;
        }
        if (player2.getField().peek().value > player1.getField().peek().value) {
            player2.addCardsToDeck(player1.getField());
            player2.addCardsToDeck(player2.getField());
            player1.clearField();
            player2.clearField();
            return PLAYER2_WON;
        }
        else {
            player1.throwCard();
            player2.throwCard();
            return TIE;
        }
    }

    public int gameResult() {
        if (player1.isDeckEmpty())
            return PLAYER2_WON;
        if (player2.isDeckEmpty())
            return PLAYER1_WON;
        else
            return NOT_OVER;
    }

    public int getPlayer1CardCount() {
        return player1.getCardCount();
    }

    public int getPlayer2CardCount() {
        return player2.getCardCount();
    }

    public int getTopPlaye1rCard() {
        return player1.getField().peek().serial;
    }

    public int getTopPlayer2Card() {
        return player2.getField().peek().serial;
    }
}
