package com.example.zzong.rxjavapractice;


import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;

import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zzong.rxjavapractice.Adapter.MovieAdapter;
import com.example.zzong.rxjavapractice.Model.MovieData;
import com.example.zzong.rxjavapractice.Model.items;
import com.example.zzong.rxjavapractice.Retrofit.NaverInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public String Naver_id = "";
    public String Naver_pw = "";
    public String keyword;
    public String TAG = "RETROFIT_CALL";

    private View mainLayout;

    EditText editText;
    Button searchBtn;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.activity_main);
        editText = (EditText)findViewById(R.id.search_bar);
        searchBtn = (Button)findViewById(R.id.search_button);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://openapi.naver.com")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        final NaverInterface naverInterface = retrofit.create(NaverInterface.class);

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    keyword = editText.getText().toString();
                    editText.onEditorAction(EditorInfo.IME_ACTION_DONE);

                    Call<MovieData> call = naverInterface.getMovieData(Naver_id, Naver_pw, keyword);
                    call.enqueue(new Callback<MovieData>() {
                        @Override
                        public void onResponse(Call<MovieData> call, Response<MovieData> response) {
                            if (response.code() == 400) {
                                Log.d(TAG, "WRONG QUERY");
                                Snackbar.make(mainLayout.getRootView(), "검색창이 비어있습니다. 키워드를 입력해 주세요", Snackbar.LENGTH_LONG).show();
                                return;
                            } else {
                                try {
                                    List<items> itemsListInRetrofit = response.body().getItems();
                                    MovieAdapter adapter = new MovieAdapter(MainActivity.this, itemsListInRetrofit);
                                    recyclerView.setAdapter(adapter);
                                } catch (Exception e) {
                                    Snackbar.make(mainLayout.getRootView(), "오류가 생겼습니다 다시 시도해보세요", Snackbar.LENGTH_LONG).show();
                                    return;
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<MovieData> call, Throwable t) {
                            Log.d(TAG, "Error on connection");
                            Log.d(TAG, t.getMessage());
                        }
                    });
                    return true;
                }
                return false;
            }

        });

        searchBtn.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                keyword = editText.getText().toString();
                editText.onEditorAction(EditorInfo.IME_ACTION_DONE);

                Call<MovieData> call = naverInterface.getMovieData(Naver_id, Naver_pw, keyword);
                call.enqueue(new Callback<MovieData>() {
                    @Override
                    public void onResponse(Call<MovieData> call, Response<MovieData> response) {
                        if (response.code() == 400) {
                            Log.d(TAG, "WRONG QUERY");
                            Snackbar.make(mainLayout.getRootView(), "검색창이 비어있습니다. 키워드를 입력해 주세요", Snackbar.LENGTH_LONG).show();
                            return;
                        } else {
                            try {
                                List<items> itemsListInRetrofit = response.body().getItems();
                                MovieAdapter adapter = new MovieAdapter(MainActivity.this, itemsListInRetrofit);
                                recyclerView.setAdapter(adapter);
                            } catch (Exception e) {
                                Snackbar.make(mainLayout.getRootView(), "오류가 생겼습니다 다시 시도해보세요", Snackbar.LENGTH_LONG).show();
                                return;
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieData> call, Throwable t) {
                        Log.d(TAG, "Error on connection");
                        Log.d(TAG, t.getMessage());
                    }
                });
            }
        });

    }
}
