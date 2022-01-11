
package com.apps.akkaber_provider.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;

import com.apps.akkaber_provider.R;
import com.apps.akkaber_provider.databinding.SpinnerRowBinding;
import com.apps.akkaber_provider.model.VehicleModel;

import java.util.List;

public class SpinnerVehicleAdapter extends BaseAdapter {
    private List<VehicleModel> list;
    private Context context;

    public SpinnerVehicleAdapter(Context context) {
        this.context = context;

    }

    @Override
    public int getCount() {
        if (list == null)
            return 0;
        else
            return list.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        @SuppressLint("ViewHolder") SpinnerRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.spinner_row, viewGroup, false);

        binding.setTitle(list.get(i).getTitle());

        return binding.getRoot();
    }

    public void updateData(List<VehicleModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
