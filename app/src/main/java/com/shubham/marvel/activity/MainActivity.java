package com.shubham.marvel.activity;

import static com.shubham.marvel.utils.AppConstants.API_KEY;
import static com.shubham.marvel.utils.AppConstants.HASH;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shubham.marvel.R;
import com.shubham.marvel.models.apiResponse.ResponseModel;
import com.shubham.marvel.models.character.CharacterModel;
import com.shubham.marvel.networks.APIClient;
import com.shubham.marvel.networks.APIInterface;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv_characters;
    private List<CharacterModel> characterModelList = new ArrayList<>();
    private APIInterface apiInterface;
    private int pageNumber = 1;
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
        getCharactersList(pageNumber, false);
    }

    private void getCharactersList(int page, final boolean isLoadMore) {
        apiInterface = APIClient.getClient(activity).create(APIInterface.class);
        isLoading = true;
        Call<ResponseModel> call = apiInterface.callGetCharactersList(1,
                2,
                "1",
                API_KEY,
                HASH);

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.body() != null && response.body().getData() != null) {
                    isLoading = false;
                    if (!isLoadMore) {
                        characterModelList.clear();
                    }
                    characterModelList.addAll(getStringCharacterResultModel(new Gson().toJson(response.body().
                            getData().getResults())));
                    Log.e("strJsonOfBody1", new Gson().toJson(characterModelList));
                    //latestMoviesAdapter.notifyDataSetChanged();
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
}