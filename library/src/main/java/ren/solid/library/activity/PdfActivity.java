package ren.solid.library.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import ren.solid.library.fragment.PdfViewFragment;
import ren.solid.library.manager.EventCenter;
import ren.solid.library.net.NetUtils;

public class PdfActivity extends ToolbarActivity {
    public static String PDFNAME = "pdfname";
    private String title;

    public static void  openActivity(Context context, String pdfname)
    {
        Intent intent = new Intent(context, PdfActivity.class);
        intent.putExtra(PdfActivity.PDFNAME, pdfname);
        context.startActivity(intent);
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected Fragment setFragment() {
        return PdfViewFragment.newInstance(title);
    }

    @Override
    protected String getToolbarTitle() {
        title= getIntent().getExtras().getString(PDFNAME);
        return title;
    }
}
