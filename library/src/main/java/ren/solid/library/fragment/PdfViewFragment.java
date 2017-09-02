package ren.solid.library.fragment;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnPageChangeListener;
import java.io.File;
import ren.solid.library.R;
import ren.solid.library.fragment.base.BaseFragment;
import ren.solid.library.manager.EventCenter;
import ren.solid.library.utils.FileTools;
import ren.solid.library.utils.LogUtils;

/**
 * 作者：e430 on 2016/8/26 15:16
 * <p/>
 * 邮箱：chengzehao@163.com
 */
public class PdfViewFragment extends BaseFragment implements OnPageChangeListener {
    protected PDFView pdfview;
    protected String mPdfname;

    public static PdfViewFragment newInstance(final String pdfname) {
        PdfViewFragment fragment = new PdfViewFragment();
        fragment.mPdfname = pdfname;
        return fragment;
    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }


    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_pdf_view;
    }

    @Override
    protected void setUpView() {
        pdfview=$(R.id.pdfview);
    }

    @Override
    protected void setUpData() {
        /*pdfview.fromAsset("material.pdf")
                .defaultPage(1)
                .onPageChange(this)
                .load();*/
        LogUtils.i("zhoul",getDocFile(mPdfname).getAbsolutePath());
        /*pdfview.fromFile(getDocFile(mPdfname))
                .defaultPage(1)
                .onPageChange(this)
                .load();*/
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        showMsgShortTime("当前页："+page+",共"+pageCount+"页");
    }

    public File getDocFile(String packageName) {
        String dir = FileTools.getDir("download");
        return new File(dir, packageName);
    }
}
