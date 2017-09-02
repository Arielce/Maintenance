/*
 * Copyright (C) 2015 Krystian Drożdżyński
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ren.solid.library.widget.steppers;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import ren.solid.library.BuildConfig;
import ren.solid.library.R;

public class SteppersAdapter extends RecyclerView.Adapter<SteppersViewHolder> {

    private static final String TAG = "SteppersAdapter";
    private SteppersView steppersView;
    private Context context;
    private SteppersView.Config config;
    private List<SteppersItem> items;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private Map<Integer, Integer> frameLayoutIds = new HashMap<>();

    private int VIEW_COLLAPSED = 0;
    private int VIEW_EXPANDED = 1;

    private int removeStep = -1;
    private int beforeStep = -1;
    private int currentStep = 0;

    public SteppersAdapter(SteppersView steppersView, SteppersView.Config config, List<SteppersItem> items) {
        this.steppersView = steppersView;
        this.context = steppersView.getContext();
        this.config = config;
        this.items = items;
        this.fragmentManager = config.getFragmentManager();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == currentStep ? VIEW_EXPANDED : VIEW_COLLAPSED);
    }

    @Override
    public SteppersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = null;
        if (viewType == VIEW_COLLAPSED) {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.steppers_item, parent, false);
        } else {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.steppers_item_expanded, parent, false);
        }

        SteppersViewHolder vh = new SteppersViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final SteppersViewHolder holder, final int position) {
        final SteppersItem steppersItem = items.get(position);
        //如果条目的位置小于当前正在采集的位置，说明已经完成了，holder的isChecked置为真
        holder.setChecked(position < currentStep);
        if(holder.isChecked()) {//如果已经完成了
            //圆点显示对号
            holder.roundedView.setChecked(true);
        } else {//如果没完成或者正在执行
            //圆点显示序号
            holder.roundedView.setChecked(false);
            holder.roundedView.setText(position + 1 + "");
        }
        //如果当前位置是正在执行的步骤或者该步骤已经完成了，圆点显示彩色
        if(position == currentStep || holder.isChecked()) holder.roundedView.setCircleAccentColor();
        //否则显示灰色
        else holder.roundedView.setCircleGrayColor();
        //设置步骤标题
        holder.textViewLabel.setText(steppersItem.getLabel());
        //设置副标题
        holder.textViewSubLabel.setText(steppersItem.getSubLabel());
        //继续与取消按钮组的隐藏和显示：如果是正在执行的步骤或者是正在执行的步骤的前一步  那么显示，否则隐藏
        holder.linearLayoutContent.setVisibility(position == currentStep || position == beforeStep ? View.VISIBLE : View.GONE);
        //根据steppersItem的状态确定继续按钮是否可用
        holder.buttonContinue.setEnabled(steppersItem.isPositiveButtonEnable());
        steppersItem.addObserver(new Observer() {
            @Override
            public void update(Observable observable, Object data) {
                if(observable != null) {
                    SteppersItem item = (SteppersItem) observable;
                    holder.buttonContinue.setEnabled(item.isPositiveButtonEnable());
                }
            }
        });
        if (position == getItemCount() - 1) holder.buttonContinue.setText(context.getResources().getString(R.string.step_finish));
        else holder.buttonContinue.setText(context.getResources().getString(R.string.step_continue));

        holder.buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position == getItemCount() - 1) {
                    config.getOnFinishAction().onFinish();
                    nextStep();
                }else {
                    nextStep();
                }
            }
        });

        if(config.getOnCancelAction() != null)
            holder.buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    config.getOnCancelAction().onCancel();
                }
            });

        if(config.getOnResultClickAction() != null)
            holder.textViewSubLabel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(position < currentStep){
                        config.getOnResultClickAction().onResultClick(position);
                    }
                }
            });

        if(frameLayoutIds.get(position) == null) frameLayoutIds.put(position, findUnusedId(holder.itemView));
        if(config.getFragmentManager() != null && steppersItem.getFragment() != null) {
            holder.frameLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.transparent));
            holder.frameLayout.setTag(frameLayoutName());

            if (fragmentTransaction == null) {
                fragmentTransaction = fragmentManager.beginTransaction();
            }

            String name = makeFragmentName(steppersView.getId(), position);
            Fragment fragment = fragmentManager.findFragmentByTag(name);

            if(position < beforeStep) {
                if (fragment != null) {
                    if(BuildConfig.DEBUG) Log.v(TAG, "Removing item #" + position + ": f=" + fragment);
                    fragmentTransaction.detach(fragment);
                }
            } else if(position == beforeStep || position == currentStep) {
                if (fragment != null) {
                    if(BuildConfig.DEBUG) Log.v(TAG, "Attaching item #" + position + ": f=" + fragment + " d=" + fragment.isDetached());
                    fragmentTransaction.attach(fragment);
                } else {
                    fragment = steppersItem.getFragment();
                    if(BuildConfig.DEBUG) Log.v(TAG, "Adding item #" + position + ": f=" + fragment + " n=" + name);
                    fragmentTransaction.add(steppersView.getId(), fragment,name);

                }
            }

            if (fragmentTransaction != null) {
                fragmentTransaction.commitAllowingStateLoss();
                fragmentTransaction = null;
                fragmentManager.executePendingTransactions();
            }

            if(fragmentManager.findFragmentByTag(name) != null &&
                    fragmentManager.findFragmentByTag(name).getView() != null) {

                View fragmentView = fragmentManager.findFragmentByTag(name).getView();

                if(fragmentView.getParent() != null && frameLayoutName() != ((View) fragmentView.getParent()).getTag()) {
                    steppersView.removeViewInLayout(fragmentView);
                    holder.frameLayout.removeAllViews();
                    holder.frameLayout.addView(fragmentView);
                }
            }
        }

        if(beforeStep == position) {
            AnimationUtils.hide(holder.linearLayoutContent);
        }
        if(currentStep == position && !steppersItem.isDisplayed()) {
            steppersItem.setDisplayed(true);
        }
    }

    private void nextStep() {
        this.removeStep = currentStep - 1 > -1 ? currentStep - 1 : currentStep;
        this.beforeStep = currentStep;
        this.currentStep = this.currentStep + 1;
        notifyItemRangeChanged(removeStep, currentStep);
    }

    protected void setItems(List<SteppersItem> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private int fID = 87352142;

    public int findUnusedId(View view) {
        while( view.findViewById(++fID) != null );
        return fID;
    }

    private static String frameLayoutName() {
        return "android:steppers:framelayout";
    }

    private static String makeFragmentName(int viewId, long id) {
        return "android:steppers:" + viewId + ":" + id;
    }
}
