package com.example.administrator.mapper.present;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.administrator.mapper.R;
import com.example.administrator.mapper.adapter.UsageAdapter;
import com.example.administrator.mapper.contact.UsageContract;
import com.example.administrator.mapper.entity.SingleUsage;
import com.example.administrator.mapper.entity.Usage;
import com.example.administrator.mapper.model.UsageModel;
import com.example.administrator.mapper.ui.activity.UsageDetailActivity;
import com.example.administrator.mapper.view.UsageView;
import com.example.administrator.mapper.weight.CircleImageView;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Administrator on 2018/6/25.
 */


public class UsagePresenter implements UsageContract.Presenter {
    private static final String TAG = "EntityPresenter";
    private final UsageModel mModel = new UsageModel();


    private Context context;
    private RecyclerView recyclerView;
    private BitmapDescriptor bitmapDescriptor;
    private AMap map;
    private UsageContract.View mView = new UsageView();

    public UsagePresenter(UsageContract.View mView, Context context, AMap map) {
        this.mView = mView;
        this.context = context;
        this.map = map;
    }

    public UsagePresenter(UsageContract.View mView, Context context, RecyclerView recyclerView) {
        this.mView = mView;
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @Override
    public void showList() {
        mView.showProgress();
        mModel.getEntityList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Usage>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: 请求完成");
                        mView.stoProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e);
                        Toast.makeText(context, "请求出错" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        mView.stoProgress();
                    }

                    @Override
                    public void onNext(Usage usage) {
                        if (usage.getStatus() == 200) {
                            mView.stoProgress();
                            Log.d(TAG, "onNext() called with: usage = [" + usage + "]" + usage.getData().get(0).getContent());
                            UsageAdapter adapter = new UsageAdapter(context, usage.getData());
                            recyclerView.setAdapter(adapter);
                        }

                    }
                });
    }

    @Override
    public void addMarkers() {
        mView.showProgress();
        mModel.addMarkers().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Usage>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "marker: 请求完成");
                        mView.stoProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e);
                        Toast.makeText(context, "请求出错" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        mView.stoProgress();
                    }

                    @Override
                    public void onNext(final Usage usage) {
                        System.out.println("Makeradd");
                        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();//存放所有点的经纬度
                        if (usage.isIssuccess()) {
                            mView.stoProgress();
                            final List<Usage.DataBean> data = usage.getData();
                            for (int i = 0; i < data.size(); i++) {
                                addMarker(data.get(i));
                                System.out.println(data.get(i).getLongitude() + "经度");
                                boundsBuilder.include(new LatLng(data.get(i).getLatitude(),data.get(i).getLongitude()));//把所有点都include进去（LatLng类型）
                            }
                            map.animateCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 15));//第二个参数为四周留空宽度
                        }
                    }
                });
    }

    @Override
    public void getUsageByLatin() {
        mView.showProgress();
        map.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Log.d(TAG, "onMarkerClick() called with: marker = [" + marker + "]");
                Log.d(TAG, "onMarkerClick: " + marker.getId().replace("Marker", ""));
                mModel.getUsageByLatin(marker.getPosition().latitude, marker.getPosition().longitude)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<SingleUsage>() {
                            @Override
                            public void onCompleted() {
                                mView.stoProgress();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i(TAG, "onError: " + e.getMessage());
                                Toast.makeText(context, "请求出错" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                mView.stoProgress();
                            }

                            @Override
                            public void onNext(SingleUsage singleUsage) {
                                if (singleUsage.getStatus() == 200) {
                                    mView.stoProgress();
                                    dialogShow2(singleUsage.getData().get(0));
                                }

                            }

                        });

                return true;
            }
        });

    }


    /**
     * 自定义布局
     * setView()只会覆盖AlertDialog的Title与Button之间的那部分，而setContentView()则会覆盖全部，
     * setContentView()必须放在show()的后面
     *
     * @param
     */
    private void dialogShow2(SingleUsage.DataBean dataBean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.custom_dialog);
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.main_popwindow_layout, null);
        CircleImageView imgUserHead = v.findViewById(R.id.img_user_head);
        TextView tvUsername = v.findViewById(R.id.tv_username);
        TextView tvLocation = v.findViewById(R.id.tv_location);
        ImageView imgBanner = v.findViewById(R.id.img_banner);
        TextView tvContent = v.findViewById(R.id.tv_content);

        Glide.with(context).load(dataBean.getUserhead()).into(imgUserHead);
        Glide.with(context).load(dataBean.getImg()).into(imgBanner);
        tvUsername.setText(dataBean.getUsername());
        tvLocation.setText(dataBean.getLocation());
        tvContent.setText(dataBean.getContent());
        final Dialog dialog = builder.create();
        dialog.show();
        v.findViewById(R.id.img_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setContentView(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, UsageDetailActivity.class));
            }
        });
    }

    /**
     * func:view转bitmap
     *
     * @param view
     * @return
     */
    public static Bitmap convertViewToBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    /**
     * func:定制化marker的图标
     *
     * @param url
     * @param listener
     */
    private void customizeMarkerIcon(String url, final OnMarkerIconLoadListener listener) {
        final View markerView = LayoutInflater.from(context).inflate(R.layout.map_location_marker, null);
        final CircleImageView icon = (CircleImageView) markerView.findViewById(R.id.img_user_head);
        Glide.with(context)
                .load(url)
                .asBitmap()
                .thumbnail(0.2f)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                        //待图片加载完毕后再设置bitmapDes
                        icon.setImageBitmap(bitmap);
                        bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(convertViewToBitmap(markerView));
                        listener.markerIconLoadingFinished(markerView);
                    }
                });
    }

    public interface OnMarkerIconLoadListener {
        void markerIconLoadingFinished(View view);
    }


    /**
     * 解析数据添加到地图上面
     *
     * @param bean
     */
    private void addMarker(final Usage.DataBean bean) {
        double lon = bean.getLongitude();
        double lat = bean.getLatitude();
        LatLng latLng = new LatLng(lat, lon);
        String url = bean.getUserhead();
        final MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        customizeMarkerIcon(url, new OnMarkerIconLoadListener() {
            @Override
            public void markerIconLoadingFinished(View view) {
                markerOptions.icon(bitmapDescriptor);
                map.addMarker(markerOptions);
            }
        });


    }

}