package com.example.administrator.mapper.BaseAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Microsoft on 2017/11/25.
 */

public abstract class RecycleAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {
    private boolean animationsLocked = false;
    private boolean delayEnterAnimation = true;
    private int itemCount = 0;
    private int lastAnimatedPosition = -1;
    protected LayoutInflater layoutInflater;

    protected List<T> dataList;

    protected int layoutId;
    private Context context;

    public RecycleAdapter(Context context, List<T> dataList, int layoutId) {
        this.layoutInflater = LayoutInflater.from(context);
        this.dataList = dataList;
        this.layoutId = layoutId;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(layoutId, parent, false);
        return new CommonViewHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        bindData(holder, dataList.get(position), position);
    }


    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    public abstract void bindData(CommonViewHolder holder, T data, int postion);

}
