package com.richsoft.maintenace.msg.ui.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.richsoft.maintenace.R;
import com.richsoft.maintenace.bean.msg.GanHuoDataBean;

import java.util.ArrayList;

import me.drakeet.multitype.ItemViewProvider;
import ren.solid.library.activity.ViewPicActivity;
import ren.solid.library.activity.WebViewActivity;
import ren.solid.library.imageloader.ImageLoader;
import ren.solid.library.utils.DateUtils;

public class GanHuoImgViewProvider
        extends ItemViewProvider<GanHuoDataBean, GanHuoImgViewProvider.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_ganhuo_img, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(
            @NonNull final ViewHolder holder, @NonNull final GanHuoDataBean bean) {
        String date = bean.getPublishedAt().replace('T', ' ').replace('Z', ' ');
        holder.tv_title.setText(bean.getDesc());
        holder.tv_time.setText(DateUtils.friendlyTime(date));
        holder.tv_people.setText("via " + bean.getWho());
        ImageLoader.displayImage(holder.iv_png,bean.getImages().get(0));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebViewActivity.openActivity(holder.itemView.getContext(), bean.getDesc(), bean.getUrl());
            }
        });
        holder.iv_png.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewPicActivity.openActivity((Activity) holder.itemView.getContext(), holder.iv_png, (ArrayList<String>) bean.getImages(), 0);
            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        TextView tv_people;
        TextView tv_time;
        ImageView iv_png;

        ViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_people = (TextView) itemView.findViewById(R.id.tv_people);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            iv_png= (ImageView) itemView.findViewById(R.id.iv_png);
        }
    }
}