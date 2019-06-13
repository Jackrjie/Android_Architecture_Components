package com.tim.countries.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.tim.countries.R;
import com.tim.countries.adapter.CountryAdapter;
import com.tim.countries.model.Info;
import com.tim.countries.model.Result;
import com.tim.countries.service.GetCountryDataService;
import com.tim.countries.service.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Result> mResults;
    private RecyclerView mRvCountry;
    private CountryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getCountries();

    }

    private void findView() {
        mRvCountry = findViewById(R.id.rv_country);
        mAdapter = new CountryAdapter(mResults);
        mRvCountry.setLayoutManager(new LinearLayoutManager(this));
        mRvCountry.setAdapter(mAdapter);
        mRvCountry.setHasFixedSize(true);
    }

    public Object getCountries() {

        GetCountryDataService service = RetrofitInstance.getService();
        Call<Info> call = service.getResults();

        call.enqueue(new Callback<Info>() {
            @Override
            public void onResponse(Call<Info> call, Response<Info> response) {
                Info info = response.body();
                if (info!=null && info.getRestResponse()!=null){
                    mResults = (ArrayList<Result>) info.getRestResponse().getResult();
                    findView();
                }
            }

            @Override
            public void onFailure(Call<Info> call, Throwable t) {
                Log.i("testing", "onFailure: " + t.getLocalizedMessage());
            }
        });
        return mResults;
    }
}
