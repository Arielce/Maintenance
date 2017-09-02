package ren.solid.library.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.List;
import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;
import ren.solid.library.R;
import ren.solid.library.imageloader.ImageLoader;
import ren.solid.library.widget.ProgressCircleView;
import ren.solid.library.widget.steppers.RoundedView;


/**
 * Created by zhoul
 * Date:2016/4/5
 * Time:11:18
 * <p/>
 * 通用的RecyclerView的适配器
 * <p/>
 * 思想上参考了Hongyang的 http://blog.csdn.net/lmj623565791/article/details/38902805这篇博客
 */
public abstract class SolidRVBaseAdapter<T> extends RecyclerView.Adapter<SolidRVBaseAdapter.SolidCommonViewHolder> {

    protected List<T> mBeans;
    protected Context mContext;
    protected boolean mAnimateItems = false;
    protected int mLastAnimatedPosition = -1;

    public SolidRVBaseAdapter(Context context, List<T> beans) {
        mContext = context;
        mBeans = beans;
    }

    @Override
    public SolidRVBaseAdapter.SolidCommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(getItemLayoutID(viewType), parent, false);
        SolidCommonViewHolder holder = new SolidCommonViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(SolidRVBaseAdapter.SolidCommonViewHolder holder, int position) {
        runEnterAnimation(holder.itemView, position);
        onBindDataToView(holder, mBeans.get(position), position);

    }

    /**
     * 绑定数据到Item的控件中去
     *
     * @param holder
     * @param bean
     */
    protected abstract void onBindDataToView(SolidCommonViewHolder holder, T bean, int position);

    /**
     * 取得ItemView的布局文件
     *
     * @return
     */
    public abstract int getItemLayoutID(int viewType);


    @Override
    public int getItemCount() {
        return mBeans.size();
    }

    public void add(T bean) {
        mBeans.add(bean);
        notifyDataSetChanged();
    }

    public void addAll(List<T> beans) {
        addAll(beans, false);
    }

    public void addAll(List<T> beans, boolean clearPrevious) {
        if (clearPrevious) mBeans.clear();
        mBeans.addAll(beans);
        notifyDataSetChanged();
    }

    public void clear() {
        mBeans.clear();
        notifyDataSetChanged();
    }

    public List<T> getDatas() {
        return mBeans;
    }

    /***
     * item的加载动画
     *
     * @param view
     * @param position
     */
    private void runEnterAnimation(final View view, int position) {
        if (!mAnimateItems) {
            return;
        }
        if (position > mLastAnimatedPosition) {
            mLastAnimatedPosition = position;
            view.setAlpha(0);
            view.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Animation animation = AnimationUtils.loadAnimation(view.getContext(),
                            R.anim.slide_in_right);
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            view.setAlpha(1);
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    view.startAnimation(animation);
                }
            }, 100);
        }
    }


    public class SolidCommonViewHolder extends
            RecyclerView.ViewHolder {
        private final SparseArray<View> mViews;
        public View itemView;

        public SolidCommonViewHolder(View itemView) {
            super(itemView);
            this.mViews = new SparseArray<>();
            this.itemView = itemView;
            //添加Item的点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemLongClick(getAdapterPosition());
                    return false;
                }
            });
        }


        public <T extends View> T getView(int viewId) {

            View view = mViews.get(viewId);
            if (view == null) {
                view = itemView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }

        public void setDisplay(int viewId) {
            View v = getView(viewId);
            v.setVisibility(View.VISIBLE);
        }

        public void setUnDisplay(int viewId) {
            View v = getView(viewId);
            v.setVisibility(View.INVISIBLE);
        }

        public void setHide(int viewId) {
            View v = getView(viewId);
            v.setVisibility(View.GONE);
        }

        public void setText(int viewId, CharSequence text) {
            TextView tv = getView(viewId);
            tv.setText(text);
        }

        public void setProgress(int viewId, int progress) {
            ProgressBar progressBar = getView(viewId);
            progressBar.setProgress(progress);
        }


        public void setBackgroundColor(int viewId, int color) {
            View v = getView(viewId);
            v.setBackgroundColor(color);
        }

        public void setRoundViewCheck(int viewId, boolean check) {
            RoundedView v = getView(viewId);
            v.setChecked(check);
        }

        public void setRoundViewText(int viewId, String text) {
            RoundedView v = getView(viewId);
            v.setText(text);
        }

        public void setRoundViewBackgroundColor(int viewId, int color) {
            RoundedView v = getView(viewId);
            v.setCircleColor(color);
        }

        public void removeAllTags(int viewId) {
            TagContainerLayout v = getView(viewId);
            v.removeAllTags();
        }

        public void addTag(int viewId, String tag) {
            TagContainerLayout v = getView(viewId);
            v.addTag(tag);
        }

        public void setOnTagClickListener(int viewId, TagView.OnTagClickListener listener) {
            TagContainerLayout v = getView(viewId);
            v.setOnTagClickListener(listener);
        }


        public void setOnClickListener(int viewId, View.OnClickListener listener) {
            View v = getView(viewId);
            v.setOnClickListener(listener);
        }

        /**
         * 加载drawable中的图片
         *
         * @param viewId
         * @param resId
         */
        public void setImage(int viewId, int resId) {
            ImageView iv = getView(viewId);
            iv.setImageResource(resId);
        }

        /**
         * 加载网络上的图片
         *
         * @param viewId
         * @param url
         */
        public void setImageFromInternet(int viewId, String url) {
            ImageView iv = getView(viewId);
            ImageLoader.displayImage(iv, url);
        }

        public void setProgressText(int viewId, String str) {
            ProgressCircleView pcv = getView(viewId);
            pcv.setProgressText(str);
        }

        public void setProgressIcon(int viewId, int res) {
            ProgressCircleView pcv = getView(viewId);
            pcv.setProgressIcon(res);
        }

        public void setProgressEnable(int viewId, boolean b) {
            ProgressCircleView pcv = getView(viewId);
            pcv.setProgressEnable(b);
        }

        public void setCirProgress(int viewId, int len) {
            ProgressCircleView pcv = getView(viewId);
            pcv.setProgress(len);
        }

        public void setMax(int viewId, int len) {
            ProgressCircleView pcv = getView(viewId);
            pcv.setMax(len);
        }
    }

    /**
     * ItemView的单击事件(如果需要，重写此方法就行)
     *
     * @param position
     */
    protected void onItemClick(int position) {

    }

    protected void onItemLongClick(int position) {

    }

}
