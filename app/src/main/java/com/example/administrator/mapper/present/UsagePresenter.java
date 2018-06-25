package com.example.administrator.mapper.present;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.administrator.mapper.adapter.UsageAdapter;
import com.example.administrator.mapper.contact.UsageContract;
import com.example.administrator.mapper.entity.Usage;
import com.example.administrator.mapper.model.UsageModel;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/6/25.
 */

public class UsagePresenter implements UsageContract.Presenter {
    private static final String TAG = "EntityPresenter";
    private final UsageModel mModel = new UsageModel();
    private final UsageContract.View mView;
    private Context context;
    private RecyclerView recyclerView;

    public UsagePresenter(UsageContract.View mView, Context context, RecyclerView recyclerView) {
        this.mView = mView;
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @Override
    public void showList() {
        mModel.getEntityList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Usage>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: 请求完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e);
                    }

                    @Override
                    public void onNext(Usage usage) {
                        Log.d(TAG, "onNext() called with: usage = [" + usage + "]" + usage.getData().get(0).getContent());
                        UsageAdapter adapter = new UsageAdapter(context, usage.getData());
                        recyclerView.setAdapter(adapter);
                    }
                });
    }


}