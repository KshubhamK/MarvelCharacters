package com.shubham.marvel.activity;

import static com.shubham.marvel.utils.AppConstants.API_KEY;
import static com.shubham.marvel.utils.AppConstants.HASH;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shubham.marvel.R;
import com.shubham.marvel.adapter.MarvelCharacterAdapter;
import com.shubham.marvel.customListener.PaginationScrollListener;
import com.shubham.marvel.models.apiResponse.ResponseModel;
import com.shubham.marvel.models.character.CharacterModel;
import com.shubham.marvel.models.character.Data;
import com.shubham.marvel.networks.APIClient;
import com.shubham.marvel.networks.APIInterface;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MarvelCharacterAdapter.OnClickInterface {
    private APIInterface apiInterface;
    private RecyclerView rv_characters;
    private List<CharacterModel> characterModelList = new ArrayList<>();
    private MarvelCharacterAdapter marvelCharacterAdapter;
    private int recordPerPage = 20;
    private int pageNumber = 0;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int totalPages = 0;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initController();
    }

    private void initController() {
        activity = MainActivity.this;
        rv_characters = findViewById(R.id.rv_characters);
        setViewToCharacterList();
        getCharactersList(pageNumber, false);
    }

    private void getCharactersList(int page, final boolean isLoadMore) {
        apiInterface = APIClient.getClient(activity).create(APIInterface.class);
        isLoading = true;
        Call<ResponseModel> call = apiInterface.callGetCharactersList(recordPerPage,
                page,
                "1",
                API_KEY,
                HASH);

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.body() != null && response.body().getData() != null) {
                    isLoading = false;
                    try {
                        String strJsonOfBody = new Gson().toJson(response.body().getData());
                        Data data = new Gson().fromJson(strJsonOfBody, Data.class);
                        totalPages = Integer.parseInt(data.getTotal());
                        if (!isLoadMore) {
                            characterModelList.clear();
                        }
                        characterModelList.addAll(getStringCharacterResultModel(new Gson().toJson(data.getResults())));
                        Log.e("strJsonOfBody1", new Gson().toJson(characterModelList));
                        marvelCharacterAdapter.notifyDataSetChanged();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                call.cancel();
            }
        });
    }

    /**
     * response.toString converted into model
     * @param jsonArray
     * @return
     */
    List<CharacterModel> getStringCharacterResultModel(String jsonArray) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<CharacterModel>>() {
        }.getType();
        List<CharacterModel> myModelList = gson.fromJson(jsonArray, listType);
        return myModelList;
    }

    private void setViewToCharacterList() {
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(activity, 2);
        rv_characters.setLayoutManager(mLayoutManager);
        rv_characters.setItemAnimator(new DefaultItemAnimator());
        marvelCharacterAdapter = new MarvelCharacterAdapter(characterModelList, activity, this);
        rv_characters.setAdapter(marvelCharacterAdapter);

        /**
         * scroll listener attached to recycler view for pagination
         */
        rv_characters.addOnScrollListener(new PaginationScrollListener(mLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                pageNumber += 1;
                if (pageNumber == totalPages) {
                    if (pageNumber != 1) {
                        Log.e("Records ", "no more");
                    }
                }
                else {
                    getCharactersList(pageNumber, true);
                }
            }

            @Override
            public int getTotalPageCount() {
                return totalPages;
            }

            @Override
            public boolean isLastPage() {
                if(pageNumber<=totalPages-1){
                    isLastPage=false;
                }else {
                    isLastPage=true;
                }
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
    }

    @Override
    public void clickedOnCharacter(View v, int position) {

    }
}