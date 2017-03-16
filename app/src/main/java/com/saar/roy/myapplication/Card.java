package com.saar.roy.myapplication;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/**
 * Created by USER1 on 07/03/2017.
 */
public class Card {
    public final int serial;
    public final value;

    public Card(int serial) {
        this.serial = serial;
        this.value = serial % 13 + 1;
        if (this.value == 1)
          this.value = 14;
    }
}
