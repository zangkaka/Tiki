package com.zang.tiki.until;

import com.zang.tiki.retrofit.ITiKiAPI;
import com.zang.tiki.retrofit.RetrofitClient;

/**
 * Created by Rang on 18-Aug-20.
 */
public class APIConstance {
    public static final String BASE_URL = "https://api.tiki.vn/";

    public static ITiKiAPI getAPI() {
        return RetrofitClient.getRetrofit(BASE_URL).create(ITiKiAPI.class);
    }
}
