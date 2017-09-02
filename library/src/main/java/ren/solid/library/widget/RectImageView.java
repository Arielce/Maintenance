package ren.solid.library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;


public class RectImageView extends ImageView {
    public RectImageView(Context context) {
        super(context);
    }

    public RectImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.setMeasuredDimension(this.getMeasuredWidth(), this.getMeasuredWidth()/2);
    }
}
