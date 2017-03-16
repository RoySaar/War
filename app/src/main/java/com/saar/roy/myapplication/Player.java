package com.saar.roy.myapplication;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by USER1 on 07/03/2017.
 */
public class Player {
    private final Queue<Card> deck;
    private Stack<Card> field;

    public void addCardsToDeck(Iterable<Card> cards) {
        for (Card card:cards
             ) {
            deck.add(card);
        }
    }

    public Player() {
        deck = new LinkedList<>();
        field = new Stack<>();
    }

    public int getCardCount() {return deck.size();}

    public void throwCard() {field.push(deck.remove());}

    public boolean isDeckEmpty() {
        return deck.isEmpty();
    }

    public void clearField() {
        field = new Stack<Card>();
    }

    public Stack<Card> getField() {
        return this.field;
    }
}
