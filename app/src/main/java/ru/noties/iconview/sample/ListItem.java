package ru.noties.iconview.sample;

import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;

public class ListItem {

    private final int icon;
    private final int color;

    public ListItem(@DrawableRes int icon, @ColorInt int color) {
        this.icon = icon;
        this.color = color;
    }

    public int icon() {
        return icon;
    }

    public int color() {
        return color;
    }
}
