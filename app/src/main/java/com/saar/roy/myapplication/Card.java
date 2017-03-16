package com.saar.roy.myapplication;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/**
 * Created by USER1 on 07/03/2017.
 */
public class Card {
    public final int serial;
    public int value;

    public Card(int serial) {
        this.serial = serial;
        value = serial % 13 + 1;
        if (value == 1)
          value = 14;
    }
}
