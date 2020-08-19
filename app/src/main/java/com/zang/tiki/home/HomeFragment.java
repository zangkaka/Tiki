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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.squareup.picasso.Picasso;
import com.zang.tiki.R;
import com.zang.tiki.adapter.QuickLinkAdapter;
import com.zang.tiki.model.BannerDto;
import com.zang.tiki.model.Datum;
import com.zang.tiki.model.QuickLink;
import com.zang.tiki.model.QuickLinkDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private SliderLayout sliderLayout;
    private RecyclerView quickLinkRclView;
    private List<QuickLink> quickLinkList;
    private QuickLinkAdapter quickLinkAdapter;

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
        initView(view);

        homeViewModel.getBanner().observe(getViewLifecycleOwner(), new Observer<BannerDto>() {
            @Override
            public void onChanged(BannerDto bannerDto) {
                displayBanner(bannerDto);
            }
        });
        homeViewModel.getQuickLink().observe(getViewLifecycleOwner(), new Observer<QuickLinkDto>() {
            @Override
            public void onChanged(QuickLinkDto quickLinkDto) {
                displayQuickLink(quickLinkDto);
            }
        });

    }

    private void initView(View view) {
        sliderLayout = view.findViewById(R.id.daimajia_slider_image);
        quickLinkRclView = view.findViewById(R.id.home_quick_link_rcl_view);
        quickLinkList = new ArrayList<>();

        RecyclerView.LayoutManager layoutManager =
                new GridLayoutManager(getContext(), 2, LinearLayoutManager.HORIZONTAL, false);
        quickLinkRclView.setLayoutManager(layoutManager);
        quickLinkAdapter = new QuickLinkAdapter(quickLinkList, getContext());
        quickLinkRclView.setAdapter(quickLinkAdapter);
    }

    private void displayQuickLink(QuickLinkDto quickLinkDto) {
        for (int i = 0; i < quickLinkDto.getData().size(); i++) {
            quickLinkList.addAll(quickLinkDto.getData().get(i));
        }
        quickLinkAdapter.addData(quickLinkList);
    }

    private void displayBanner(BannerDto bannerDto) {
        HashMap<Integer, String> bannerMap = new HashMap<>();
        for (Datum item : bannerDto.getData()) {
            bannerMap.put(item.getId(), item.getMobileUrl());
        }

        for (String name : bannerMap.values()) {
            DefaultSliderView defaultSliderView = new DefaultSliderView(getContext());
            Picasso picasso = Picasso.with(getContext());
            defaultSliderView.setPicasso(picasso);
            defaultSliderView.image(name);
            sliderLayout.addSlider(defaultSliderView);
        }
    }
}