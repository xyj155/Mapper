package com.example.administrator.mapper.model;

import com.example.administrator.mapper.contact.UsageContract;
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
//    @Override
//    public Observable<Entity> getEntityList() {
//        return RetrofitUtil.
//                getInstance().
//                getServerices().
//                getListService();
//    }
//
//    @Override
//    public Observable<User> getUser() {
//        return RetrofitUtil.
//                getInstance().
//                getServerices().
//                getUser();
//    }
}
