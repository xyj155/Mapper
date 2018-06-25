package com.example.administrator.mapper.contact;

import com.example.administrator.mapper.entity.Usage;

import rx.Observable;


/**
 * Created by Administrator on 2018/6/25.
 */

public class UsageContract {
    public interface Model {
        Observable<Usage> getEntityList();
    }
    public interface View {
        void showData();
    }
    public interface Presenter {
        void showList();
    }
}
