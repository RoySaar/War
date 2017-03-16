package com.saar.roy.myapplication;

import java.util.Stack;

/**
 * Created by USER1 on 07/03/2017.
 */
public class Player {
    private final Queue<Card> deck;
    private final Stack<Card> field;

    public void addCardsToDeck(Iterable<Card> cards) {
        for (Card card:cards
             ) {
            deck.add(card);
        }
    }

    public Player() {0
        deck = new LinkedList<>();
        field = new Stack<>();
    }

    public void throwCard() {
        field.push(deck.remove());
    }

    public boolean isDeckEmpty() {
        return deck.isEmpty();
    }

    public Stack<Card> getField() {
        return this.field;
    }
}
