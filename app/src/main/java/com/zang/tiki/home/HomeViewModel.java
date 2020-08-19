package com.zang.tiki.home;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zang.tiki.model.BannerDto;
import com.zang.tiki.until.APIConstance;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Rang on 18-Aug-20.
 */
public class HomeViewModel extends ViewModel {
    private MutableLiveData<BannerDto> mBannerLiveData;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public HomeViewModel() {
        mBannerLiveData = new MutableLiveData<>();
        loadBanner();
    }

    public LiveData<BannerDto> getBanner() {
        return mBannerLiveData;
    }

    @SuppressLint("CheckResult")
    private void loadBanner() {
        mCompositeDisposable.add(
                APIConstance.getAPI().getBanner().subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<BannerDto>() {
                            @Override
                            public void accept(BannerDto bannerDto) throws Exception {
                                mBannerLiveData.setValue(bannerDto);
                            }
                        }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mCompositeDisposable.dispose();
    }
}
