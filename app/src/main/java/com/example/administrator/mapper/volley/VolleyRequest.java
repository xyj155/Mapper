package com.example.administrator.mapper.volley;

import com.android.volley.VolleyError;

/**
 * Created by Adaministrator on 2018/4/17.
 */

public interface VolleyRequest {
    void onsuccess(String jsonObject);
    void onerror(VolleyError error) throws Exception;
}
