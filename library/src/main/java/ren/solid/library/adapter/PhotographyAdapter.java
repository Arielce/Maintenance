package ren.solid.library.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;
import ren.solid.library.R;
import ren.solid.library.imageloader.ImageLoader;
import ren.solid.library.utils.LogUtils;
import ren.solid.library.utils.Url;

/**
 * 作者：e430 on 2016/7/21 22:12
 * <p/>
 * 邮箱：chengzehao@163.com
 */
public class PhotographyAdapter extends RecyclerView.Adapter<PhotographyAdapter.ViewHolder>{
    private List<String> list = new ArrayList<String>();

    private Context mContext;

    private LayoutInflater mInflater;

    private OnItmeClickListener mlistener;

    public int maxNum=6;

    public void setOnItemClickListener(OnItmeClickListener mlistener){
        this.mlistener=mlistener;
    }

    public  interface OnItmeClickListener{
        void onItemClicked(int position);

    }

    public PhotographyAdapter(Context mContext,int maxNum) {
        this.mContext = mContext;
        this.maxNum=maxNum;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        list.add(createAddEntry());
    }

    /**
     * 重新加载数据
     * @param data
     */
    public void reloadList(ArrayList<String> data) {
        if (data != null) {
            list.clear();
            list.addAll(data);
            list.add(createAddEntry());
        } else {
            list.clear();
        }
        notifyDataSetChanged();
    }


    public void appendPhoto(String entry) {
        if (entry != null) {
            list.add(list.size() - 1, entry);
        }
        notifyDataSetChanged();
    }

    public List<String> getData(){
        return list.subList(0,list.size()-1);
    }

    private String createAddEntry(){
        return "Add";
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewHolder vh = new ViewHolder(mInflater.inflate(R.layout.item_selected_photography, viewGroup, false), i);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (i==list.size()-1){
            if(list.size()>=maxNum+1){
                viewHolder.mImageView.setVisibility(View.GONE);
            }else{
                viewHolder.mImageView.setImageResource(R.drawable.add);
            }
        }else {
            LogUtils.i("zhoul",list.get(i));
            if(list.get(i).endsWith("webp")&&!list.get(i).startsWith("http")){
                ImageLoader.displayImage(viewHolder.mImageView, Url.SERVER_URL+list.get(i));
            }else{
                ImageLoader.displayImage(viewHolder.mImageView, list.get(i));
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageView;

        private int position;

        public ViewHolder(View itemView, int position) {
            super(itemView);
            this.position = position;
            mImageView = (ImageView) itemView.findViewById(R.id.image);
            mImageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mlistener!=null){
                mlistener.onItemClicked(position);
            }
        }
    }
}
