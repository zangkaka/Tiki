package com.zang.tiki.retrofit;

import com.zang.tiki.model.BannerDto;
import com.zang.tiki.model.QuickLinkDto;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Rang on 18-Aug-20.
 */
public interface ITiKiAPI {
    @GET("v2/home/banners/v2")
    Observable<BannerDto> getBanner();

    @GET("shopping/v2/widgets/quick_link")
    Observable<QuickLinkDto> getQuickLink();
}
