package com.richsoft.maintenace.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.richsoft.maintenace.R;
import ren.solid.library.widget.Roll3DView;

/**
 * Created by zhangyu on 2017/1/19.
 */

public class SwitchCabinetView extends LinearLayout{
    private Context context;
    private Roll3DView roll3DView;
    private BitmapDrawable bgDrawable1, bgDrawable2,bgDrawable3;

    public SwitchCabinetView(Context context) {
        super(context);
        init(context);
    }

    public SwitchCabinetView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SwitchCabinetView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        View.inflate(context, R.layout.view_switch_cabinet,this);
        roll3DView = (Roll3DView) findViewById(R.id.three_d_view);
        bgDrawable1 = (BitmapDrawable) getResources().getDrawable(R.drawable.front);
        bgDrawable2 = (BitmapDrawable) getResources().getDrawable(R.drawable.side);
        bgDrawable3 = (BitmapDrawable) getResources().getDrawable(R.drawable.back);

        Bitmap bitmap1 = bgDrawable1.getBitmap();
        Bitmap bitmap2 = bgDrawable2.getBitmap();
        Bitmap bitmap3 = bgDrawable3.getBitmap();

        roll3DView.addImageBitmap(bitmap1);
        roll3DView.addImageBitmap(bitmap2);
        roll3DView.addImageBitmap(bitmap3);

        roll3DView.setRollMode(Roll3DView.RollMode.Whole3D);
    }

    private int sideTag=0;//0-前 1-侧 2-后

    public int toSideRoll(){
        roll3DView.setRollDirection(0);

        switch (sideTag){
            case 0:
                sideTag=1;
                roll3DView.toNext();
                break;
            case 1:
                sideTag=2;
                roll3DView.toNext();
                break;
            case 2:
                sideTag=0;
                roll3DView.toNext();
                break;
        }
        return sideTag;
    }

    public Roll3DView getRoll3DView() {
        return roll3DView;
    }

}
