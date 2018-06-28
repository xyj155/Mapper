package com.example.administrator.mapper.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.administrator.mapper.BaseAdapter.CommonViewHolder;
import com.example.administrator.mapper.BaseAdapter.RecycleAdapter;
import com.example.administrator.mapper.R;
import com.example.administrator.mapper.entity.Usage;
import com.example.administrator.mapper.volley.VolleyRequest;
import com.example.administrator.mapper.volley.VolleyRequstUtil;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/24.
 */

public class FragmentMyUsage extends Fragment {
    private RecyclerView ry_myusage;
    private RequestQueue queue;
    private SwipeRefreshLayout swip;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_my_usage, container, false);
        ry_myusage = inflate.findViewById(R.id.ry_myusage);
        swip = inflate.findViewById(R.id.swip);
        Toolbar toolbar = inflate.findViewById(R.id.toolbar);
        toolbar.setSubtitle("我的文集");
        queue = Volley.newRequestQueue(getActivity());

        swip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        final Map<String, String> map = new HashMap<>();
                        map.put("userid", String.valueOf(1));
                        queue.add(VolleyRequstUtil.RequestWithParams("http://182.254.147.87/Usage/public/index.php/index/Usage/getUsageByUsername", map, new VolleyRequest() {
                            @Override
                            public void onsuccess(String jsonObject) {
                                System.out.println(jsonObject);
                                Gson gson = new Gson();
                                Usage usage = gson.fromJson(jsonObject, Usage.class);
                                ry_myusage.setLayoutManager(new LinearLayoutManager(getActivity()));
                                UsageAdapter adapter = new UsageAdapter(getActivity(), usage.getData());
                                ry_myusage.setAdapter(adapter);
                                swip.setRefreshing(false);
                            }

                            @Override
                            public void onerror(VolleyError error) throws Exception {
                                swip.setRefreshing(false);
                                Toast.makeText(getActivity(), "请求出错", Toast.LENGTH_SHORT).show();
                            }
                        }));

                    }
                }, 800);
            }
        });
        return inflate;
    }

    private class UsageAdapter extends RecycleAdapter<Usage.DataBean> {

        private Context context;

        public UsageAdapter(Context context, List<Usage.DataBean> dataList) {
            super(context, dataList, R.layout.mu_ry_user_local_item);
            this.context = context;
        }

        @Override
        public void bindData(CommonViewHolder holder, Usage.DataBean data, int postion) {
            holder.setText(R.id.tv_content, data.getContent())
                    .setText(R.id.tv_username, data.getTitle())
                    .setText(R.id.tv_location, data.getLocation())
                    .setImageResourceURL(R.id.img_head, data.getImg());
        }
    }
}
