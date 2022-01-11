package com.apps.akkaber_provider.uis.activity_home.fragments_home_navigaion;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.apps.akkaber_provider.R;
import com.apps.akkaber_provider.adapter.CurrentOrderAdapter;
import com.apps.akkaber_provider.adapter.PreviousOrderAdapter;
import com.apps.akkaber_provider.adapter.SpinnerFilterAdapter;
import com.apps.akkaber_provider.databinding.FragmentPreviousReservationBinding;
import com.apps.akkaber_provider.mvvm.FragmentPreviousOrderMvvm;
import com.apps.akkaber_provider.uis.activity_base.BaseFragment;
import com.apps.akkaber_provider.uis.activity_home.HomeActivity;


public class FragmentPreviousReservation extends BaseFragment {
    private FragmentPreviousReservationBinding binding;
    private FragmentPreviousOrderMvvm fragmentPreviousOrderMvvm;
    private HomeActivity activity;
    private PreviousOrderAdapter adapter;

    public static FragmentPreviousReservation newInstance() {
        FragmentPreviousReservation fragment = new FragmentPreviousReservation();
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (HomeActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_previous_reservation, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();

    }

    @SuppressLint("ClickableViewAccessibility")
    private void initView() {
        fragmentPreviousOrderMvvm = ViewModelProviders.of(this).get(FragmentPreviousOrderMvvm.class);
        fragmentPreviousOrderMvvm.getIsLoading().observe(activity, isLoading -> {
            binding.swipeRefresh.setRefreshing(isLoading);
            if (isLoading) {
                binding.llNoData.setVisibility(View.GONE);
            }
        });
        fragmentPreviousOrderMvvm.onOrderDataSuccess().observe(activity, dataList -> {
            if (adapter != null && dataList != null) {
                adapter.updateList(dataList);
            }
        });
        fragmentPreviousOrderMvvm.setFilterBy(null);
        adapter = new PreviousOrderAdapter(activity, this);
        binding.recView.setLayoutManager(new LinearLayoutManager(activity));
        binding.recView.setAdapter(adapter);

        fragmentPreviousOrderMvvm.getOrder(getUserModel().getData().getApi_token(), fragmentPreviousOrderMvvm.getFilterBy().getValue());
        binding.swipeRefresh.setOnRefreshListener(() -> fragmentPreviousOrderMvvm.getOrder(getUserModel().getData().getApi_token(), fragmentPreviousOrderMvvm.getFilterBy().getValue()));

        binding.swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        binding.flFilter.setOnClickListener(v -> {
            if (binding.flFilterDialog.getVisibility() == View.VISIBLE) {
                binding.flFilterDialog.setVisibility(View.GONE);

            } else {
                binding.flFilterDialog.setVisibility(View.VISIBLE);

            }
        });

        binding.rbWeek.setOnClickListener(v -> {
            fragmentPreviousOrderMvvm.setFilterBy("week");
            binding.flFilterDialog.setVisibility(View.GONE);
            binding.tvFilter.setText(R.string.last_week);
            fragmentPreviousOrderMvvm.getOrder(getUserModel().getData().getApi_token(), fragmentPreviousOrderMvvm.getFilterBy().getValue());

        });
        binding.rbMonth.setOnClickListener(v -> {
            fragmentPreviousOrderMvvm.setFilterBy("month");
            binding.tvFilter.setText(R.string.last_month);
            binding.flFilterDialog.setVisibility(View.GONE);
            fragmentPreviousOrderMvvm.getOrder(getUserModel().getData().getApi_token(), fragmentPreviousOrderMvvm.getFilterBy().getValue());

        });
        binding.rbYear.setOnClickListener(v -> {
            fragmentPreviousOrderMvvm.setFilterBy("year");
            binding.tvFilter.setText(R.string.last_year);
            binding.flFilterDialog.setVisibility(View.GONE);
            fragmentPreviousOrderMvvm.getOrder(getUserModel().getData().getApi_token(), fragmentPreviousOrderMvvm.getFilterBy().getValue());

        });
        binding.rbAll.setOnClickListener(v -> {
            fragmentPreviousOrderMvvm.setFilterBy(null);
            binding.tvFilter.setText(R.string.all);
            binding.flFilterDialog.setVisibility(View.GONE);
            fragmentPreviousOrderMvvm.getOrder(getUserModel().getData().getApi_token(), fragmentPreviousOrderMvvm.getFilterBy().getValue());

        });

        binding.cons.setOnTouchListener((v, event) -> {
            binding.flFilterDialog.setVisibility(View.GONE);
            return false;
        });

        binding.swipeRefresh.setOnTouchListener((v, event) -> {
            binding.flFilterDialog.setVisibility(View.GONE);
            return false;
        });

        binding.recView.setOnTouchListener((v, event) -> {
            binding.flFilterDialog.setVisibility(View.GONE);
            return false;
        });

    }


}