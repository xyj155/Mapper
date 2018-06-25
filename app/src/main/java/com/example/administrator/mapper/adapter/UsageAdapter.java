package com.example.administrator.mapper.adapter;

import android.content.Context;

import com.example.administrator.mapper.BaseAdapter.CommonViewHolder;
import com.example.administrator.mapper.BaseAdapter.RecycleAdapter;
import com.example.administrator.mapper.R;
import com.example.administrator.mapper.entity.Usage;

import java.util.List;

public class UsageAdapter extends RecycleAdapter<Usage.DataBean> {

    private Context context;

    public UsageAdapter(Context context, List<Usage.DataBean> dataList) {
        super(context, dataList, R.layout.mp_ry_user_local_item);
        this.context = context;
    }

    @Override
    public void bindData(CommonViewHolder holder, Usage.DataBean data, int postion) {
        holder.setText(R.id.tv_content, data.getContent())
                .setText(R.id.tv_username, data.getUsername())
                .setText(R.id.tv_location, data.getLocation())
                .setImageResourceURL(R.id.img_head, data.getUserhead());
    }
}