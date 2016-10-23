package com.recipie.rahulweather.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.recipie.rahulweather.R;
import com.recipie.rahulweather.activity.BaseActivity;
import com.recipie.rahulweather.databinding.MainActivityBinding;
import com.recipie.rahulweather.fragment.MainFragment;
import com.recipie.rahulweather.viewmodel.MainActivityViewModel;

public class MainActivity extends BaseActivity {

    /**
     * Main Fragment
     */
    private MainFragment fragment;

    /**
     * ViewModel
     */
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Data binding
        MainActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityViewModel = new MainActivityViewModel();
        binding.setViewModel(mainActivityViewModel);

        if (savedInstanceState == null) {
            transactToChildFragment();
        }
    }

    public void transactToChildFragment() {
        try {
            fragment = new MainFragment();

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                    android.R.anim.fade_out, android.R.anim.fade_out, android.R.anim.fade_in);

            fragmentTransaction.add(R.id.container_fragment, fragment, "MAIN_FRAGMENT");

            fragmentTransaction.commit();
        } catch (Exception e) {
        }
    }

}
