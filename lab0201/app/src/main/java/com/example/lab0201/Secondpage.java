package com.example.lab0201;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Secondpage extends AppCompatActivity {
    private String[] str = {
            "Nizhny Novgorog", "Moscow",
            "Vladivostok", "Yekaterinburg",
            "Novosibirsk", "Omsk",
            "Kirov", "Yeysk", "Shanghai", "Pekin", "Almata"};
    String key = "a8117d83b4bcd54e1f70c15f306d992a";
    String[] string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondpage);
        RecyclerView rv = findViewById(R.id.recyclerView);
        getweather(str);
        RVadapter adapter = new RVadapter(this, string);
        // устанавливаем для списка адаптер
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
int i=0;
    public void getweather(String[] cities) {
        for (String city : cities) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/data/2.5/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            weatherapi myapi = retrofit.create(weatherapi.class);
            Call<Example> ex = myapi.getweather(city, key);
            ex.enqueue(new Callback<Example>() {
                @Override
                public void onResponse(Call<Example> call, Response<Example> response) {
                    Example mydata = response.body();
                    Main main = mydata.getMain();
                    Double temp = main.getTemp();
                    Integer tempp = (int) (temp - 273);
                    string[i]="Текущая температура в"+city+ ": " + String.valueOf(tempp) + "C";
                    i++;
                }

                @Override
                public void onFailure(Call<Example> call, Throwable t) {
                    Toast.makeText(Secondpage.this, t.getMessage(), Toast.LENGTH_LONG).show();

                }

            });
        }
    }
}