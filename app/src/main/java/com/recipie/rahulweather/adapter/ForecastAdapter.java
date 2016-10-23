package com.recipie.rahulweather.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.recipie.rahulweather.R;
import com.recipie.rahulweather.databinding.ItemWeatherForecastBinding;
import com.recipie.rahulweather.fragment.MainFragment;
import com.recipie.rahulweather.model.Condition;
import com.recipie.rahulweather.model.Units;
import com.recipie.rahulweather.viewmodel.ItemWeatherForecastViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by developers on 23/10/16.
 */

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.BindingHolder> {

    /**
     * Fragment
     */
    private MainFragment fragment;

    /**
     * List
     */
    private List<Condition> list = Collections.emptyList();

    /**
     * Unit
     */
    private Units units;

    /**
     * Hashmap of viewModel
     */
    private HashMap<Integer, ItemWeatherForecastViewModel> viewModelMapper;

    /**
     * Constructor
     */
    public ForecastAdapter(MainFragment fragment, ArrayList<Condition> list, Units units) {
        this.fragment = fragment;
        this.list = list;
        this.units = units;
        viewModelMapper = new HashMap<>();
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_weather_forecast, parent, false);
        BindingHolder holder = new BindingHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        ItemWeatherForecastViewModel itemWeatherForecastViewModel;
        if (viewModelMapper.containsKey(position)) {
            itemWeatherForecastViewModel = viewModelMapper.get(position);
        } else {
            itemWeatherForecastViewModel = new ItemWeatherForecastViewModel(fragment,list.get(position),units);
            viewModelMapper.put(position, itemWeatherForecastViewModel);
        }
        holder.getBinding().setViewModel(itemWeatherForecastViewModel);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * Contains the custom ViewHolder for this adapter
     */
    public static class BindingHolder extends RecyclerView.ViewHolder {
        private ItemWeatherForecastBinding binding;

        public BindingHolder(View itemView) {
            super(itemView);

            // data binding
            binding = DataBindingUtil.bind(itemView);
        }

        public ItemWeatherForecastBinding getBinding() {
            return binding;
        }

        public void setBinding(ItemWeatherForecastBinding binding) {
            this.binding = binding;
        }
    }
}
