package ren.solid.library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2017/1/12.
 */

public class SquaredFrameLayout extends FrameLayout {
    public SquaredFrameLayout(Context context) {
        super(context);
    }

    public SquaredFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.setMeasuredDimension(this.getMeasuredWidth(), this.getMeasuredWidth()/4*3);
    }
}
