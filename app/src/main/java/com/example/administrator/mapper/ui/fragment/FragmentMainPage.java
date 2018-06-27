package com.example.administrator.mapper.ui.fragment;

import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.MyLocationStyle;
import com.example.administrator.mapper.R;
import com.example.administrator.mapper.contact.UsageContract;
import com.example.administrator.mapper.present.UsagePresenter;
import com.example.administrator.mapper.weight.LoadingDialog;
import com.example.administrator.mapper.weight.VerticalDrawerLayout;
import com.example.administrator.mapper.weight.card.CardItem;
import com.example.administrator.mapper.weight.card.CardPagerAdapter;
import com.example.administrator.mapper.weight.card.ShadowTransformer;

/**
 * Created by Administrator on 2018/6/24.
 */

public class FragmentMainPage extends Fragment implements AMap.OnMyLocationChangeListener, LocationSource, AMapLocationListener, View.OnClickListener, UsageContract.View {
    MapView mMapView = null;
    AMap aMap;
    MyLocationStyle myLocationStyle;
    LocationSource.OnLocationChangedListener mListener;
    AMapLocationClient mlocationClient;
    AMapLocationClientOption mLocationOption;
    VerticalDrawerLayout mDrawerLayout;
    LinearLayout mArrow;
    private RecyclerView mp_ry_user;
    private UsagePresenter usagePresenter;
    private UsagePresenter usageMarker;

    private ViewPager mViewPager;
    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
    private LoadingDialog.Builder builder;
    LoadingDialog d;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_mainpage, container, false);
        builder = new LoadingDialog.Builder(getActivity())
                .setCancelable(true)
                .setMessage("加载中")
                .setShowMessage(true);

        mViewPager = (ViewPager) inflate.findViewById(R.id.viewPager);
        mMapView = (MapView) inflate.findViewById(R.id.map);
        mp_ry_user = inflate.findViewById(R.id.mp_ry_user);
        mp_ry_user.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.setMyLocationEnabled(true);
        aMap.setOnMyLocationChangeListener(this);
        // 设置定位监听
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory
                .fromResource(R.mipmap.ic_map_location));
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.setLocationSource(this);
        aMap.setMyLocationEnabled(true);
        aMap.getUiSettings().setZoomControlsEnabled(false);
        aMap.getUiSettings().setLogoBottomMargin(-50);
        CameraUpdate cameraUpdate = CameraUpdateFactory.zoomTo(17);
        mMapView.getMap().moveCamera(cameraUpdate);
        mDrawerLayout = (VerticalDrawerLayout) inflate.findViewById(R.id.vertical);
        mArrow = (LinearLayout) inflate.findViewById(R.id.center);
        mDrawerLayout.setDrawerListener(new VerticalDrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                mArrow.setRotation(slideOffset * 180);
            }
        });
        usagePresenter = new UsagePresenter(this, getActivity(), mp_ry_user);
        usageMarker = new UsagePresenter(this, getActivity(), aMap);
        usagePresenter.showList();
        usageMarker.getUsageByLatin();
        usageMarker.addMarkers();
        mArrow.setOnClickListener(this);

        mCardAdapter = new CardPagerAdapter();
        mCardAdapter.addCardItem(new CardItem("今日话题", "Koltlin编程", "内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容"));
        mCardAdapter.addCardItem(new CardItem("优秀推荐", "我的父亲", "内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容"));
        mCardAdapter.addCardItem(new CardItem("散文专车", "父亲", "内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容"));
        mCardAdapter.addCardItem(new CardItem("校园专题", "我的野蛮女友", "内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容"));
        mCardAdapter.addCardItem(new CardItem("社会热点", "习大大发红包", "内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容"));
        mCardAdapter.addCardItem(new CardItem("学习氛围", "无理作业不想写", "内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容！！"));

        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);
        mCardShadowTransformer.enableScaling(true);
        return inflate;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        super.onDestroy();
        mMapView.onDestroy();
        if (null != mlocationClient) {
            mlocationClient.onDestroy();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onMyLocationChange(Location location) {

    }

    /**
     * 激活定位
     */
    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (mlocationClient == null) {
            //初始化定位
            mlocationClient = new AMapLocationClient(getActivity());
            //初始化定位参数
            mLocationOption = new AMapLocationClientOption();
            //设置定位回调监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();//启动定位
        }
    }

    /**
     * 停止定位
     */
    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {
            if (amapLocation != null
                    && amapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
            } else {
                String errText = "定位失败," + amapLocation.getErrorCode() + ": " + amapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.center:
                if (mDrawerLayout.isDrawerOpen()) {
                    mDrawerLayout.closeDrawer();
                } else {
                    mDrawerLayout.openDrawerView();
                }
                break;
        }
    }

    @Override
    public void showData() {
    }


    @Override
    public void showProgress() {
        d = builder.create();
        d.show();
        System.out.println("请求中....");
    }

    @Override
    public void showToast() {
        Toast.makeText(getActivity(), "请求成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void stoProgress() {
        d.dismiss();
        System.out.println("请求中....");
    }


}
