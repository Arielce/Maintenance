package ren.solid.library.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.io.File;
import java.util.ArrayList;
import ren.solid.library.R;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.imageloader.ImageLoader;
import ren.solid.library.manager.EventCenter;
import ren.solid.library.net.NetUtils;
import ren.solid.library.utils.FileUtils;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by zhoul
 * Date:2016/4/22
 * Time:14:28
 * <p/>
 * view full picture
 */
public class ViewPicActivity extends BaseActivity {
    public final static String IMG_URLS = "ImageUrls";
    public final static String IMG_INDEX = "ImageIndex";
    protected ViewPager mViewPager;
    private TextView mTvIndex;
    private AppCompatImageView mIvDownload;
    private ArrayList<String> mUrlList;
    private int mCurrentIndex = 0;
    private String mSavePath;


    public static void openActivity(Activity activity, View view, ArrayList<String> urls, int position) {
        Intent intent = new Intent(activity, ViewPicActivity.class);
        intent.putStringArrayListExtra(IMG_URLS, urls);
        intent.putExtra(IMG_INDEX, position);
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeScaleUpAnimation(view,
                view.getWidth() / 2, view.getHeight() / 2, 0, 0);
        ActivityCompat.startActivity(activity, intent,
                compat.toBundle());
    }

    public static void openActivity(Activity activity, View view, String url) {
        ArrayList<String> urls = new ArrayList<>();
        urls.add(url);
        openActivity(activity, view, urls, 0);
    }
    public static void openActivity(Context context, View view, ArrayList<String> urls, int position) {
        Intent intent = new Intent(context, ViewPicActivity.class);
        intent.putStringArrayListExtra(IMG_URLS, urls);
        intent.putExtra(IMG_INDEX, position);
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeScaleUpAnimation(view,
                view.getWidth() / 2, view.getHeight() / 2, 0, 0);
        ActivityCompat.startActivity(context, intent,
                compat.toBundle());
    }

    public static void openActivity(Context context, View view, String url) {
        ArrayList<String> urls = new ArrayList<>();
        urls.add(url);
        openActivity(context, view, urls, 0);
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return true;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return TransitionMode.FADE;
    }

    @Override
    protected void initViewsAndEvents() {
        mUrlList = getIntent().getExtras().getStringArrayList(IMG_URLS);
        mCurrentIndex = getIntent().getExtras().getInt(IMG_INDEX);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTvIndex = (TextView) findViewById(R.id.tv_index);
        mIvDownload = (AppCompatImageView) findViewById(R.id.iv_download);
        mTvIndex.setText((mCurrentIndex + 1) + "/" + mUrlList.size());
        mIvDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadPicture(0);
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentIndex = position;
                mTvIndex.setText((mCurrentIndex + 1) + "/" + mUrlList.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setAdapter(new MyViewPager(this));
        mViewPager.setCurrentItem(mCurrentIndex);

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
    protected int getContentViewLayoutID() {
        return R.layout.activity_view_pic;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    class MyViewPager extends PagerAdapter {
        Context context;

        public MyViewPager(Context context) {
            this.context = context;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            PhotoView photoView = new PhotoView(context);

            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            photoView.setLayoutParams(layoutParams);

            ImageLoader.displayImage(photoView, mUrlList.get(position));
            container.addView(photoView);

            return photoView;
        }

        @Override
        public int getCount() {
            return mUrlList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


    public void downloadPicture(final int action) {
        mSavePath = FileUtils.getSaveImagePath(this) + File.separator + FileUtils.getFileName(mUrlList.get(0));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCompat.finishAfterTransition(this);
    }
}
