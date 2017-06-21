package ru.noties.iconview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class IconView extends ImageView {

    private static final PorterDuff.Mode DEFAULT_MODE = PorterDuff.Mode.MULTIPLY;

    private int color;
    private PorterDuff.Mode mode = DEFAULT_MODE;

    public IconView(Context context) {
        super(context);
        init(context, null);
    }

    public IconView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attributeSet) {
        if (attributeSet != null) {
            final TypedArray array = context.obtainStyledAttributes(attributeSet, R.styleable.IconView);
            try {
                this.mode = forValue(array.getInteger(R.styleable.IconView_iv_mode, DEFAULT_MODE.ordinal())); // multiply by default
                final int color = array.getColor(R.styleable.IconView_iv_color, 0);
                setColor(color);
            } finally {
                array.recycle();
            }
        }
    }

    public void setMode(@NonNull PorterDuff.Mode mode) {
        this.mode = mode;
        setColor(this.color);
    }

    /**
     * @param color color to apply, passing `0` or `Color.TRANSPARENT` will remove colorFilter
     */
    public void setColor(@ColorInt int color) {

        this.color = color;

        final ColorFilter filter;
        if (color == 0) {
            filter = null;
        } else {
            filter = new PorterDuffColorFilter(color, mode);
        }

        setColorFilter(filter);
    }

    @ColorInt
    public int color() {
        return color;
    }

    @NonNull
    public PorterDuff.Mode mode() {
        return mode;
    }

    @NonNull
    private static PorterDuff.Mode forValue(int value) {
        final PorterDuff.Mode out;
        final PorterDuff.Mode[] modes = PorterDuff.Mode.values();
        if (value < 0
                || value >= modes.length) {
            out = DEFAULT_MODE;
        } else {
            out = modes[value];
        }
        return out;
    }
}
