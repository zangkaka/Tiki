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
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.squareup.picasso.Picasso;
import com.zang.tiki.R;
import com.zang.tiki.model.BannerDto;
import com.zang.tiki.model.Datum;

import java.util.HashMap;

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
            DefaultSliderView defaultSliderView = new DefaultSliderView(getContext());
            Picasso picasso = Picasso.with(getContext());
            defaultSliderView.setPicasso(picasso);
            defaultSliderView.image(name);
            sliderLayout.addSlider(defaultSliderView);
        }
    }
}