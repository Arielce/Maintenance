package com.richsoft.maintenace.workorder.filed.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.richsoft.maintenace.R;
import com.richsoft.maintenace.bean.workorder.SwitchRoomBean;
import com.richsoft.maintenace.bean.workorder.SwitchRoomDeviceBean;
import com.richsoft.maintenace.common.SwitchCabinetView;
import com.richsoft.maintenace.common.SwitchRoom;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import ren.solid.library.fragment.base.BaseFragment;
import ren.solid.library.manager.EventCenter;
import ren.solid.library.utils.LogUtils;

/**
 * 作者：e430 on 2017/2/17 13:30
 * <p>
 * 邮箱：chengzehao@163.com
 */

public class FieldRequireItemSwitchCabinetFragment extends BaseFragment {
    private SwitchRoom mSwitchRoomView;
    private SwitchRoomBean mData;
    private View.OnClickListener onClickListener;


    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


    public static FieldRequireItemSwitchCabinetFragment newInstance(SwitchRoomBean data) {
        Bundle args = new Bundle();
        FieldRequireItemSwitchCabinetFragment fragment = new FieldRequireItemSwitchCabinetFragment();
        args.putSerializable("data", data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mData = (SwitchRoomBean) getArguments().getSerializable("data");
    }


    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.item_switch_room;
    }

    @Override
    protected void setUpView() {
        mSwitchRoomView = $(R.id.switch_room_view);
        mSwitchRoomView.setSwitchCabinetChecker(new SwitchRoom.SwitchCabinetChecker() {
            @Override
            public void onSwitchCabinetClick(int row, int column) {
                SwitchRoomDeviceBean s = mData.getSwitchRoomDeviceRowBeans().get(row).getSwitchRoomDeviceBeans().get(column);
                switch (s.getSwitchRoomDeviceState()) {
                    case 0:
                        LogUtils.i("zhoul", "这个开关柜停运呢");
                        break;
                    case 1:
                        LogUtils.i("zhoul", "这个开关柜开启呢");
                        showSwitchCabinetDialog(s);
                        break;
                    case 2:
                        LogUtils.i("zhoul", "这个开关柜临时关闭呢");
                        break;
                    case 3:
                        LogUtils.i("zhoul", "这个开关柜采集完了");
                        break;
                    case 4:
                        LogUtils.i("zhoul", "这是个过道");
                        break;
                }
            }
        });
        mSwitchRoomView.setData(mData);
    }

    @Override
    protected void setUpData() {

    }

    MaterialDialog.Builder dialogBuilder;
    MaterialDialog dialog;
    SwitchCabinetView mSwitchCabinetView;
    Button bt_roll;
    TextView tv_cabinet_side;
    LinearLayout ll_front;
    TextView tv_front_up;
    TextView tv_front_mid;
    TextView tv_front_down;
    DiscreteSeekBar seek_front_up;
    DiscreteSeekBar seek_front_mid;
    DiscreteSeekBar seek_front_down;

    LinearLayout ll_side;
    TextView tv_side_up;
    TextView tv_side_mid;
    TextView tv_side_down;
    DiscreteSeekBar seek_side_up;
    DiscreteSeekBar seek_side_mid;
    DiscreteSeekBar seek_side_down;

    LinearLayout ll_back;
    TextView tv_back_up;
    TextView tv_back_mid;
    TextView tv_back_down;
    DiscreteSeekBar seek_back_up;
    DiscreteSeekBar seek_back_mid;
    DiscreteSeekBar seek_back_down;


    private void showSwitchCabinetDialog(SwitchRoomDeviceBean s) {
        String tag = s.getSideCabinet() == 0 ? "【中柜】" : "【边柜】";

        dialogBuilder = new MaterialDialog.Builder(getMContext())
                .customView(R.layout.dialog_switch_cabinet, true)
                .title(s.getSwitchRoomDeviceNo() + s.getSwitchRoomDeviceName() + tag)
                .positiveText("保存数据");

        dialog = dialogBuilder.build();
        mSwitchCabinetView = (SwitchCabinetView) dialog.getCustomView().findViewById(R.id.mSwitchCabinetView);
        bt_roll = (Button) dialog.getCustomView().findViewById(R.id.bt_roll);

        tv_cabinet_side = (TextView) dialog.getCustomView().findViewById(R.id.tv_cabinet_side);
        ll_front = (LinearLayout) dialog.getCustomView().findViewById(R.id.ll_front);
        tv_front_up = (TextView) dialog.getCustomView().findViewById(R.id.tv_front_up);
        tv_front_mid = (TextView) dialog.getCustomView().findViewById(R.id.tv_front_mid);
        tv_front_down = (TextView) dialog.getCustomView().findViewById(R.id.tv_front_down);
        seek_front_up = (DiscreteSeekBar) dialog.getCustomView().findViewById(R.id.seek_front_up);
        seek_front_mid = (DiscreteSeekBar) dialog.getCustomView().findViewById(R.id.seek_front_mid);
        seek_front_down = (DiscreteSeekBar) dialog.getCustomView().findViewById(R.id.seek_front_down);

        ll_side = (LinearLayout) dialog.getCustomView().findViewById(R.id.ll_side);
        tv_side_up = (TextView) dialog.getCustomView().findViewById(R.id.tv_side_up);
        tv_side_mid = (TextView) dialog.getCustomView().findViewById(R.id.tv_side_mid);
        tv_side_down = (TextView) dialog.getCustomView().findViewById(R.id.tv_side_down);
        seek_side_up = (DiscreteSeekBar) dialog.getCustomView().findViewById(R.id.seek_side_up);
        seek_side_mid = (DiscreteSeekBar) dialog.getCustomView().findViewById(R.id.seek_side_mid);
        seek_side_down = (DiscreteSeekBar) dialog.getCustomView().findViewById(R.id.seek_side_down);

        ll_back = (LinearLayout) dialog.getCustomView().findViewById(R.id.ll_back);
        tv_back_up = (TextView) dialog.getCustomView().findViewById(R.id.tv_back_up);
        tv_back_mid = (TextView) dialog.getCustomView().findViewById(R.id.tv_back_mid);
        tv_back_down = (TextView) dialog.getCustomView().findViewById(R.id.tv_back_down);
        seek_back_up = (DiscreteSeekBar) dialog.getCustomView().findViewById(R.id.seek_back_up);
        seek_back_mid = (DiscreteSeekBar) dialog.getCustomView().findViewById(R.id.seek_back_mid);
        seek_back_down = (DiscreteSeekBar) dialog.getCustomView().findViewById(R.id.seek_back_down);


        if (s.getSwitchRoomDeviceState() == 3) {
            seek_front_up.setVisibility(View.GONE);
            seek_front_mid.setVisibility(View.GONE);
            seek_front_down.setVisibility(View.GONE);

            seek_side_up.setVisibility(View.GONE);
            seek_side_mid.setVisibility(View.GONE);
            seek_side_down.setVisibility(View.GONE);

            seek_back_up.setVisibility(View.GONE);
            seek_back_mid.setVisibility(View.GONE);
            seek_back_down.setVisibility(View.GONE);

            tv_front_up.setText(s.getFrontUp());
            tv_front_mid.setText(s.getFrontMiddle());
            tv_front_down.setText(s.getFrontDown());

            tv_side_up.setText(s.getSideUp());
            tv_side_mid.setText(s.getSideMiddle());
            tv_side_down.setText(s.getSideDown());

            tv_back_up.setText(s.getBackUp());
            tv_back_mid.setText(s.getBackMiddle());
            tv_back_down.setText(s.getBackDown());

        } else {
            seek_front_up.setVisibility(View.VISIBLE);
            seek_front_mid.setVisibility(View.VISIBLE);
            seek_front_down.setVisibility(View.VISIBLE);

            seek_side_up.setVisibility(View.VISIBLE);
            seek_side_mid.setVisibility(View.VISIBLE);
            seek_side_down.setVisibility(View.VISIBLE);

            seek_back_up.setVisibility(View.VISIBLE);
            seek_back_mid.setVisibility(View.VISIBLE);
            seek_back_down.setVisibility(View.VISIBLE);
        }


        bt_roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sideType = mSwitchCabinetView.toSideRoll();
                refreshBySide(sideType);
            }

            private void refreshBySide(int sideType) {
                switch (sideType) {
                    case 0:
                        tv_cabinet_side.setText("【正面】");
                        ll_front.setVisibility(View.VISIBLE);
                        ll_side.setVisibility(View.GONE);
                        ll_back.setVisibility(View.GONE);
                        break;
                    case 1:
                        tv_cabinet_side.setText("【侧面】");
                        ll_front.setVisibility(View.GONE);
                        ll_side.setVisibility(View.VISIBLE);
                        ll_back.setVisibility(View.GONE);
                        break;
                    case 2:
                        tv_cabinet_side.setText("【后面】");
                        ll_front.setVisibility(View.GONE);
                        ll_side.setVisibility(View.GONE);
                        ll_back.setVisibility(View.VISIBLE);
                        break;

                }
            }
        });

        seek_front_up.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                tv_front_up.setText(String.valueOf(value));
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });

        seek_front_mid.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                tv_front_mid.setText(String.valueOf(value));
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });

        seek_front_down.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                tv_front_down.setText(String.valueOf(value));
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });

        seek_side_up.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                tv_side_up.setText(String.valueOf(value));
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });

        seek_side_mid.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                tv_side_mid.setText(String.valueOf(value));
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });

        seek_side_down.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                tv_side_down.setText(String.valueOf(value));
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });

        seek_back_up.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                tv_back_up.setText(String.valueOf(value));
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });
        seek_back_mid.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                tv_back_mid.setText(String.valueOf(value));
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });

        seek_back_down.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                tv_back_down.setText(String.valueOf(value));
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });

        dialog.show();

    }

}
