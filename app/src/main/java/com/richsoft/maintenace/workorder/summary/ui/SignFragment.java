package com.richsoft.maintenace.workorder.summary.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.richsoft.maintenace.R;
import com.richsoft.maintenace.bean.workorder.SummaryBean;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;
import ren.solid.library.fragment.base.BaseFragment;
import ren.solid.library.listener.OnNavigationBarListener;
import ren.solid.library.manager.EventCenter;

/**
 * 作者：e430 on 2017/3/6 10:39
 * <p>
 * 邮箱：chengzehao@163.com
 */

public class SignFragment extends BaseFragment implements Step {
    SignaturePad signaturePad;
    FloatingActionButton fab_clear;
    SummaryBean mData;
    private boolean isCanNext = false;

    public SignaturePad getSignaturePad() {
        return signaturePad;
    }

    @Nullable
    private OnNavigationBarListener onNavigationBarListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnNavigationBarListener) {
            onNavigationBarListener = (OnNavigationBarListener) context;
        }
    }

    public static SignFragment newInstance(SummaryBean data) {
        Bundle args = new Bundle();
        SignFragment fragment = new SignFragment();
        args.putSerializable("data",data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mData= (SummaryBean) getArguments().getSerializable("data");
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
        return R.layout.fragment_signature;
    }

    @Override
    protected void setUpView() {
        signaturePad=$(R.id.signature_pad);
        fab_clear=$(R.id.fab_clear);
        fab_clear.setVisibility(View.GONE);
        signaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
                showLog("the pad start signed");
            }

            @Override
            public void onSigned() {
                showLog("the pad is signed");
                fab_clear.setVisibility(View.VISIBLE);
                isCanNext=true;
                updateNavigationBar();
            }

            @Override
            public void onClear() {
                showLog("the pad is cleared");
                fab_clear.setVisibility(View.GONE);
                isCanNext=false;
                updateNavigationBar();
            }
        });

        fab_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signaturePad.clear();
            }
        });
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public VerificationError verifyStep() {
        return isCanNext ? null : new VerificationError("请您完成签名，并确认！");
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    public void updateNavigationBar() {
        if (onNavigationBarListener != null) {
            onNavigationBarListener.onChangeEndButtonsEnabled(isCanNext);
        }
    }
}
