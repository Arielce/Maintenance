package com.richsoft.maintenace.workorder.sendworkorder.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.richsoft.maintenace.R;
import com.richsoft.maintenace.bean.workorder.WorkOrderBean;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import ren.solid.library.fragment.base.BaseFragment;
import ren.solid.library.listener.OnNavigationBarListener;
import ren.solid.library.manager.EventCenter;
import ren.solid.library.utils.DateUtils;
import ren.solid.library.utils.StringUtils;

/**
 * 作者：e430 on 2017/2/17 13:30
 * <p>采集工单日期
 * 邮箱：chengzehao@163.com
 */

public class DateChooseFragment extends BaseFragment implements Step,OnDateSelectedListener, OnMonthChangedListener {
    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();
    private MaterialCalendarView calendarView;
    private TextView tv_date;
    private WorkOrderBean mWorkOrderBean;
    private boolean isCanNext = false;
    @Nullable
    private OnNavigationBarListener onNavigationBarListener;

    public static DateChooseFragment newInstance(WorkOrderBean data) {
        Bundle args = new Bundle();
        DateChooseFragment fragment = new DateChooseFragment();
        args.putSerializable("data",data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWorkOrderBean= (WorkOrderBean) getArguments().getSerializable("data");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnNavigationBarListener) {
            onNavigationBarListener = (OnNavigationBarListener) context;
        }
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
        return R.layout.fragment_date_choose;
    }

    @Override
    protected void setUpView() {
        calendarView=$(R.id.calendarView);
        tv_date=$(R.id.tv_date);
        calendarView.setOnDateChangedListener(this);
        calendarView.setOnMonthChangedListener(this);
        Calendar calendar = Calendar.getInstance ();
        calendarView.state().edit()
                .setMinimumDate(CalendarDay.from(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH)))
                .setMaximumDate(CalendarDay.from(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 2, calendar.get(Calendar.DAY_OF_MONTH)))
                .commit();
    }

    @Override
    protected void setUpData() {
        tv_date.setText("【工单日期】"+getSelectedDatesString());
    }

    @Override
    public VerificationError verifyStep() {
        return isCanNext ? null : new VerificationError("请您选择工单日期");
    }

    @Override
    public void onSelected() {
        updateNavigationBar();
    }

    @Override
    public void onError(@NonNull VerificationError error) {
        calendarView.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.shake_error));
    }

    public void updateNavigationBar() {
        if (onNavigationBarListener != null) {
            if(StringUtils.isNullOrEmpty(mWorkOrderBean.getOrderDate())){
                isCanNext=false;
            }else{
                isCanNext=true;
            }
            onNavigationBarListener.onChangeEndButtonsEnabled(isCanNext);
        }
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        tv_date.setText("【工单日期】"+getSelectedDatesString());
        mWorkOrderBean.setOrderDate(getSelectedDatesString());
        updateNavigationBar();
    }

    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {

    }

    private String getSelectedDatesString() {
        CalendarDay date = calendarView.getSelectedDate();
        if (date == null) {
            return "未选择";
        }
        return DateUtils.formatDate(date.getDate());
    }
}
