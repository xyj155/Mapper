package com.example.administrator.mapper.model;

import com.example.administrator.mapper.contact.UsageContract;
import com.example.administrator.mapper.entity.SingleUsage;
import com.example.administrator.mapper.entity.Usage;
import com.example.administrator.mapper.util.RetrofitUtil;

import rx.Observable;


/**
 * Created by Administrator on 2018/6/25.
 */

public class UsageModel implements UsageContract.Model {
    @Override
    public Observable<Usage> getEntityList() {
        return RetrofitUtil.
                getInstance().
                getServerices().
                getUsageList();
    }

    @Override
    public Observable<Usage> addMarkers() {
        return RetrofitUtil.
                getInstance().
                getServerices().
                addMarkers();
    }

    @Override
    public Observable<SingleUsage> getUsageByLatin(double latitude, double longitude) {
        return RetrofitUtil.
                getInstance().
                getServerices().
                getUsageByLatin(latitude,longitude);
    }
}
