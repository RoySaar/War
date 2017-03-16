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
    private Player player;
    private Player cpu;
    public static final int CPU_WON = 0;
    public static final int PLAYER_WON = 1;
    public static final int TIE = 2;
    public static final int NOT_OVER = 2;


    public Game() {
        this.nums = new int[52];
        for (int i = 0; i < 52; i++) {
            nums[i] = i;
        }
        player = new Player();
        cpu = new Player();
        player.addCardsToDeck(generatePack());
        cpu.addCardsToDeck(generatePack());
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

    public void playEach() {
        player.throwCard();
        cpu.throwCard();
    }

    public int handlePlay() {
        if (player.getField().peek().value > cpu.getField().peek().value) {
            player.addCardsToDeck(cpu.getField());
            player.addCardsToDeck(player.getField());
            player.clearField();
            cpu.clearField();
            return PLAYER_WON;
        }
        if (cpu.getField().peek().value > player.getField().peek().value) {
            cpu.addCardsToDeck(player.getField());
            cpu.addCardsToDeck(cpu.getField());
            player.clearField();
            cpu.clearField();
            return CPU_WON;
        }
        else {
            player.throwCard();
            cpu.throwCard();
            return TIE;
        }
    }

    public int gameResult() {
        if (player.isDeckEmpty())
            return CPU_WON;
        if (cpu.isDeckEmpty())
            return PLAYER_WON;
        else
            return NOT_OVER;
    }

    public int getPlayerCardCount() {
        return player.getCardCount();
    }

    public int getCpuCardCount() {
        return cpu.getCardCount();
    }

    public int getTopPlayerCard() {
        return player.getField().peek().serial;
    }

    public int getTopCpuCard() {
        return cpu.getField().peek().serial;
    }
}
