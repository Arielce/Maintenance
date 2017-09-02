package ren.solid.library.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.shuyu.gsyvideoplayer.GSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import ren.solid.library.R;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.manager.EventCenter;
import ren.solid.library.net.NetUtils;

public class VideoViewActivity extends BaseActivity {
    public static String URL = "Url";
    public static String TITLE = "Title";
    public static String THUMB = "Thumb";
    private String mUrl;
    private String mTitle;
    private String mThumb;

    protected StandardGSYVideoPlayer detail_player;

    public  static void  openActivity(Context context, String title, String url,String thumb)
    {
        Intent intent = new Intent(context, VideoViewActivity.class);
        intent.putExtra(VideoViewActivity.URL, url);
        intent.putExtra(VideoViewActivity.TITLE, title);
        intent.putExtra(VideoViewActivity.THUMB, thumb);
        context.startActivity(intent);
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
        mUrl = getIntent().getExtras().getString(URL);
        mTitle = getIntent().getExtras().getString(TITLE);
        mThumb = getIntent().getExtras().getString(THUMB);
        detail_player= (StandardGSYVideoPlayer) findViewById(R.id.detail_player);
        detail_player.setUp(mUrl , true, "");

        //增加封面
        ImageView imageView = new ImageView(this);
        Glide.with(mContext).load(mThumb).centerCrop().into(imageView);
        detail_player.setThumbImageView(imageView);


        detail_player.setBottomProgressBarDrawable(getResources().getDrawable(R.drawable.video_new_progress));
        detail_player.setDialogVolumeProgressBar(getResources().getDrawable(R.drawable.video_new_volume_progress_bg));
        detail_player.setDialogProgressBar(getResources().getDrawable(R.drawable.video_new_progress));
        detail_player.setBottomShowProgressBarDrawable(getResources().getDrawable(R.drawable.video_new_seekbar_progress),
                getResources().getDrawable(R.drawable.video_new_seekbar_thumb));
        detail_player.setDialogProgressColor(getResources().getColor(R.color.colorAccent), -11);

        //是否可以滑动调整
        detail_player.setIsTouchWiget(false);

        detail_player.getTitleTextView().setVisibility(View.VISIBLE);
        detail_player.getTitleTextView().setText(mTitle);
        detail_player.getBackButton().setVisibility(View.VISIBLE);

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
        return R.layout.activity_video_view;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoPlayer.releaseAllVideos();
    }
}
