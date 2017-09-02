package com.richsoft.maintenace.workorder.summary.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import com.richsoft.maintenace.R;
import com.richsoft.maintenace.bean.workorder.SummaryBean;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;
import ren.solid.library.fragment.base.BaseFragment;
import ren.solid.library.listener.OnNavigationBarListener;
import ren.solid.library.manager.EventCenter;
import ren.solid.library.utils.StringUtils;

/**
 * 作者：e430 on 2017/3/6 10:39
 * <p>
 * 邮箱：chengzehao@163.com
 */

public class ImplementationEvaluationFragment extends BaseFragment implements Step {
    EditText et_input;
    SummaryBean mData;
    private boolean isCanNext = false;

    @Nullable
    private OnNavigationBarListener onNavigationBarListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnNavigationBarListener) {
            onNavigationBarListener = (OnNavigationBarListener) context;
        }
    }

    public static ImplementationEvaluationFragment newInstance(SummaryBean data) {
        Bundle args = new Bundle();
        ImplementationEvaluationFragment fragment = new ImplementationEvaluationFragment();
        args.putSerializable("data", data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mData = (SummaryBean) getArguments().getSerializable("data");
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
        return R.layout.fragment_implementation_evaluation;
    }

    @Override
    protected void setUpView() {
        et_input= $(R.id.et_input);
        et_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mData.implementationEvaluation=charSequence.toString();
                updateNavigationBar();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public VerificationError verifyStep() {
        return isCanNext ? null : new VerificationError("请您完成评价，并确认！");
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    public void updateNavigationBar() {
        if (onNavigationBarListener != null) {
            if(StringUtils.isNullOrEmpty(mData.implementationEvaluation)){
                isCanNext=false;
            }else{
                isCanNext=true;
            }
            onNavigationBarListener.onChangeEndButtonsEnabled(isCanNext);
        }
    }
}
