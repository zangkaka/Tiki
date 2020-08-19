package com.zang.tiki.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.zang.tiki.R;
import com.zang.tiki.model.BannerDto;
import com.zang.tiki.model.Datum;
import com.zang.tiki.retrofit.ITiKiAPI;
import com.zang.tiki.until.APIConstance;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private SliderLayout sliderLayout;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sliderLayout = view.findViewById(R.id.daimajia_slider_image);
        homeViewModel.getBanner().observe(getViewLifecycleOwner(), new Observer<BannerDto>() {
            @Override
            public void onChanged(BannerDto bannerDto) {
                displayBanner(bannerDto);
                for (int i = 0; i < bannerDto.getData().size(); i++) {
                    Log.d("TAG", "onChanged: " + bannerDto.getData().get(i).getTitle());
                }
            }
        });

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

    }

    private void displayBanner(BannerDto bannerDto) {
        HashMap<Integer, String> bannerMap = new HashMap<>();
        for (Datum item: bannerDto.getData()) {
            bannerMap.put(item.getId(), item.getMobileUrl());
        }

        for (String name: bannerMap.values()){
            TextSliderView textSliderView = new TextSliderView(getContext());
            textSliderView
                    .image(bannerMap.get(name))
//                    .image("https://salt.tikicdn.com/cache/w750/ts/banner/ab/b9/e2/b8db4535cd4da6d9f1acc2267cde8c12.jpg")
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            sliderLayout.addSlider(textSliderView);
        }
    }
}