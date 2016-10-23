package com.recipie.rahulweather.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.recipie.rahulweather.R;
import com.recipie.rahulweather.activity.BaseActivity;
import com.recipie.rahulweather.databinding.MainFragmentBinding;
import com.recipie.rahulweather.viewmodel.MainFragmentViewModel;


/**
 * Created by developers on 23/10/16.
 */

public class MainFragment extends BaseFragment {
    /**
     * View Model
     */
    private MainFragmentViewModel mainFragmentViewModel;

    /**
     * Toolbar
     */
    private Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        //databinding
        MainFragmentBinding binding = DataBindingUtil.bind(view);

        mainFragmentViewModel = new MainFragmentViewModel(this);
        mainFragmentViewModel.setTitle("Yahoo Weather");
        setMainFragmentViewModel(mainFragmentViewModel);
        binding.setViewModel(mainFragmentViewModel);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        constructToolbar();
        mainFragmentViewModel.fetchWeather();
    }



    /**
     * Construct the toolbar
     */
    public void constructToolbar() {
        try {
            Toolbar toolbar = (Toolbar) getView().findViewById(R.id.toolbar);
            ((BaseActivity) getActivity()).setSupportActionBar(toolbar);
            this.toolbar = toolbar;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MainFragmentViewModel getMainFragmentViewModel() {
        return mainFragmentViewModel;
    }

    public void setMainFragmentViewModel(MainFragmentViewModel mainFragmentViewModel) {
        this.mainFragmentViewModel = mainFragmentViewModel;
    }
}
