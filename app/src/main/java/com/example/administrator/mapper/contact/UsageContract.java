package com.example.administrator.mapper.contact;

import com.example.administrator.mapper.entity.SingleUsage;
import com.example.administrator.mapper.entity.Usage;

import rx.Observable;


/**
 * Created by Administrator on 2018/6/25.
 */

public class UsageContract {
    public interface Model {
        Observable<Usage> getEntityList();
        Observable<Usage> addMarkers();
        Observable<SingleUsage> getUsageByLatin(double latitude, double longitude);
    }
    public interface View {
        void showData();
        void showProgress();
        void showToast();
        void stoProgress();
    }
    public interface Presenter {
        void showList();
        void addMarkers();
        void getUsageByLatin();
    }
}
