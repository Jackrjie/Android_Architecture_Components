package com.tim.countries.adapter;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tim.countries.R;
import com.tim.countries.model.Result;

import java.util.ArrayList;

/**
 * create by Tim on 6/10/2019
 * email: jackrjie@gmail.com
 */
public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder>{

    private ArrayList<Result> mCountries;

    public CountryAdapter(ArrayList<Result> countries) {
        mCountries = countries;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_country,parent,false);
        return new CountryViewHolder(view);
    }

    // set values for the tv_country_name
    @Override
    public void onBindViewHolder(@NonNull final CountryViewHolder holder, int position) {
        holder.tvCountry.setText(mCountries.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = holder.tvCountry.getText().toString();
                Toast.makeText(v.getContext(),msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCountries.size();
    }

    // Connect with elements of the view
    class CountryViewHolder extends RecyclerView.ViewHolder{

        private TextView tvCountry;
        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCountry = itemView.findViewById(R.id.tv_country_name);
        }
    }
}
