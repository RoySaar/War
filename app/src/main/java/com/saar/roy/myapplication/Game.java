package com.saar.roy.myapplication;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Stack;

/**
 * Created by roymac on 12/03/2017.
 */

public class Game {

    public Stack<Card> pool;
    private int[] nums;
    public final int[] IMAGES = {R.drawable.ace_of_clubs,R.drawable.two_of_clubs,R.drawable.three_of_clubs,R.drawable.four_of_clubs,R.drawable.five_of_clubs,R.drawable.six_of_clubs,R.drawable.seven_of_clubs,R.drawable.eight_of_clubs,R.drawable.nine_of_clubs,
    R.drawable.ten_of_clubs,R.drawable.jack_of_clubs,R.drawable.queen_of_clubs,R.drawable.king_of_clubs,R.drawable.ace_of_diamonds,R.drawable.two_of_diamonds,R.drawable.three_of_diamonds,R.drawable.four_of_diamonds,R.drawable.five_of_diamonds,R.drawable.six_of_diamonds,R.drawable.seven_of_diamonds,R.drawable.eight_of_diamonds,R.drawable.nine_of_diamonds,
    R.drawable.ten_of_diamonds,R.drawable.jack_of_diamonds,R.drawable.queen_of_diamonds,R.drawable.king_of_diamonds,R.drawable.ace_of_hearts,R.drawable.two_of_hearts,R.drawable.three_of_hearts,R.drawable.four_of_hearts,R.drawable.five_of_hearts,R.drawable.six_of_hearts,R.drawable.seven_of_hearts,R.drawable.eight_of_hearts,R.drawable.nine_of_hearts,
    R.drawable.ten_of_hearts,R.drawable.jack_of_hearts,R.drawable.queen_of_hearts,R.drawable.king_of_hearts,R.drawable.ace_of_hearts,R.drawable.two_of_spades,R.drawable.three_of_spades,R.drawable.four_of_spades,R.drawable.five_of_spades,R.drawable.six_of_spades,R.drawable.seven_of_spades,R.drawable.eight_of_spades,R.drawable.nine_of_spades,
    R.drawable.ten_of_spades,R.drawable.jack_of_spades,R.drawable.queen_of_spades,R.drawable.king_of_spades
    };

    public Game() {
        this.nums = new int[52];
        for (int i = 0; i < 52; i++) {
            nums[i] = i;
        }
        pool = new Stack<Card>();
    }

    public int[] getNums(){
        return nums;
    }

    public Stack<Card> getPool() {
        return pool;
    }

    public Stack<Card> generatePack() {

        Stack<Card> cards = new Stack<Card>();
        Random rnd = new Random();
        for (int i = 0; i < 26; i++) {
            int num = rnd.nextInt(52);
            while (nums[num] == -1) {
                num = rnd.nextInt(52);
            }
            cards.push(new Card(num));
            nums[num] = -1;
        }
        return cards;
    }
}
