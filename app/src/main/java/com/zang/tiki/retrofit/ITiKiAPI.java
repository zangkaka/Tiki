package com.zang.tiki.retrofit;

import com.zang.tiki.model.BannerDto;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Rang on 18-Aug-20.
 */
public interface ITiKiAPI {
    @GET("v2/home/banners/v2")
    Observable<BannerDto> getBanner();
}
