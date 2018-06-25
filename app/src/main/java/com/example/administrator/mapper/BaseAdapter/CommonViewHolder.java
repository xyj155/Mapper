package com.example.administrator.mapper.BaseAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;

/**
 * Created by Microsoft on 2017/11/25.
 */

public class CommonViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {

    private SparseArray<View> viewSparseArray;
    private OnClickListener onClickListener;
    private Context context;
    private ImageOnClickListener clickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public CommonViewHolder(View itemView, Context context) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        this.context = context;
        viewSparseArray = new SparseArray<>();
    }

    /**
     * 根据 ID 来获取 View
     *
     * @param viewId viewID
     * @param <T>    泛型
     * @return 将结果强转为 View 或 View 的子类型
     */
    public <T extends View> T getView(int viewId) {
        // 先从缓存中找，找打的话则直接返回
        // 如果找不到则 findViewById ，再把结果存入缓存中
        View view = viewSparseArray.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            viewSparseArray.put(viewId, view);
        }
        return (T) view;
    }

    public CommonViewHolder setText(int viewId, CharSequence text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }
    public CommonViewHolder Visibility(int viewId, int text) {
        View tv = getView(viewId);
        tv.setVisibility(text);
        return this;
    }

    public CommonViewHolder setViewVisibility(int viewId, int visibility) {
        getView(viewId).setVisibility(visibility);
        return this;
    }

    public CommonViewHolder setImageResource(int viewId, int resourceId) {

        ImageView imageView = getView(viewId);
        Glide.with(context).load(resourceId).into(imageView);
        return this;
    }

    public CommonViewHolder setImageResourceURL(int viewId, String resourceId) {

        ImageView imageView = getView(viewId);
        Glide.with(context).load(resourceId).into(imageView);
        return this;
    }

    public CommonViewHolder setTextColor(int viewId, int color) {
        TextView imageView = getView(viewId);
        imageView.setTextColor(color);
        return this;
    }

    public CommonViewHolder setImageResourceURLandClick(int viewId, String resourceId, View.OnClickListener listener) {

        final ImageView imageView = getView(viewId);
        Glide.with(context).load(resourceId).into(imageView);
        imageView.setOnClickListener(listener);
        return this;
    }
    public CommonViewHolder setImagebitmap(int viewId, Bitmap resourceId) {

        final ImageView imageView = getView(viewId);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        resourceId.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes=baos.toByteArray();
        Glide.with(context).load(bytes).into(imageView);
        return this;
    }

    public CommonViewHolder setImageClickListner(int viewId, View.OnClickListener listener) {

        final ImageView imageView = getView(viewId);
        imageView.setOnClickListener(listener);
        return this;
    }

    public CommonViewHolder setTextViewListener(int viewId, View.OnClickListener listener) {

        final TextView textView = getView(viewId);
        textView.setOnClickListener(listener);
        return this;
    }

    public CommonViewHolder setImageResourceUrl(int viewId, String url) {

        ImageView imageView = getView(viewId);
        Glide.with(context).load(url).into(imageView);
        return this;
    }

    public void setImageOnClickListener(int viewId) {
        ImageView imageView = getView(viewId);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    clickListener.onImageClickListner(getAdapterPosition());
                }
            }
        });
    }

    public interface ImageOnClickListener {
        void onImageClickListner(int postion);
    }

    public void setImageOnClick(ImageOnClickListener listener) {
        this.clickListener = listener;
    }

    public interface OnClickListener {
        void onItemClickListener(int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClickListener(int position);
    }

    public void setOnClickListener(OnClickListener commonClickListener) {
        this.onClickListener = commonClickListener;
    }

    public void setOnClickListener(OnItemLongClickListener commonClickListener) {
        this.onItemLongClickListener = commonClickListener;
    }

    @Override
    public void onClick(View v) {
        if (onClickListener != null) {
            onClickListener.onItemClickListener(getAdapterPosition());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (onItemLongClickListener != null) {
            onItemLongClickListener.onItemLongClickListener(getAdapterPosition());
        }
        return false;
    }
}
