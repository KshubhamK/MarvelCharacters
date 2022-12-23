package com.shubham.marvel.activity;

import static com.shubham.marvel.utils.AppConstants.API_KEY;
import static com.shubham.marvel.utils.AppConstants.HASH;
import static com.shubham.marvel.utils.AppConstants.SET_CHARACTER_ID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shubham.marvel.R;
import com.shubham.marvel.adapter.MarvelCharacterAdapter;
import com.shubham.marvel.adapter.MarvelComicsAdapter;
import com.shubham.marvel.models.apiResponse.ResponseModel;
import com.shubham.marvel.models.character.CharacterModel;
import com.shubham.marvel.models.character.Comics;
import com.shubham.marvel.models.character.Data;
import com.shubham.marvel.models.character.Items;
import com.shubham.marvel.models.comics.ComicsModel;
import com.shubham.marvel.networks.APIClient;
import com.shubham.marvel.networks.APIInterface;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterDetailsActivity extends AppCompatActivity {
    private APIInterface apiInterface;
    private ImageView iv_image;
    private TextView tv_title;
    private TextView tv_description;
    private RecyclerView rv_comics;
    private ProgressBar pb_progress;
    private LinearLayout ll_details;
    private List<CharacterModel> characterModelList;
    private List<ComicsModel> tempComicList;
    private List<ComicsModel> comicsModelList = new ArrayList<>();
    private MarvelComicsAdapter marvelComicsAdapter;
    private String characterId;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_details);
        initController();
    }

    private void initController() {
        activity = CharacterDetailsActivity.this;
        iv_image = findViewById(R.id.iv_image);
        tv_title = findViewById(R.id.tv_title);
        tv_description = findViewById(R.id.tv_description);
        rv_comics = findViewById(R.id.rv_comics);
        pb_progress = findViewById(R.id.pb_progress);
        ll_details = findViewById(R.id.ll_details);
        characterId = getIntent().getStringExtra(SET_CHARACTER_ID);
        Log.e("id", characterId);
        if (characterId != null) {
            getCharactersDetails(characterId);
        }
    }

    /**
     * character details' API called with character Id
     * @param id
     */
    private void getCharactersDetails(String id) {
        apiInterface = APIClient.getClient(activity).create(APIInterface.class);
        int charId = Integer.parseInt(id);
        Call<ResponseModel> call = apiInterface.callGetCharactersDetails(charId,
                "1",
                API_KEY,
                HASH);
        Log.e("id1", call.request().toString());

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.body() != null && response.body().getData() != null) {
                    try {
                        String strJsonOfBody = new Gson().toJson(response.body().getData());
                        Data data = new Gson().fromJson(strJsonOfBody, Data.class);
                        characterModelList = new ArrayList<>();
                        characterModelList.addAll(getStringCharacterResultModel(new Gson().toJson(data.getResults())));
                        Log.e("strJsonOfBody1", new Gson().toJson(characterModelList));
                        setViewToScreen();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                pb_progress.setVisibility(View.GONE);
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

    /**
     * image and description is set to the screen
     */
    private void setViewToScreen() {
        if (characterModelList.size() > 0) {
            String photo_url = characterModelList.get(0).getThumbnail().getPath() + "/landscape_xlarge.jpg";
            if (activity != null && iv_image != null) {
                Glide.with(activity).load(photo_url)
                        .into(iv_image);
            }
            tv_title.setText(characterModelList.get(0).getName());
            if (!characterModelList.get(0).getDescription().isEmpty()) {
                tv_description.setText(characterModelList.get(0).getDescription());
            }
            else {
                tv_description.setText(R.string.no_description);
            }
        }
        forComics();
    }

    /**
     * for each and every comics item url is fetched and called
     */
    private void forComics() {
        if (characterModelList.size() > 0) {
            for (CharacterModel characterModel : characterModelList) {
                for (Items items : characterModel.getComics().getItems()) {
                    String[] url = items.getResourceURI().split("/");
                    getComicsList(url[6]);
                }
            }
        }
    }

    /**
     * comics list API is called with comics ID
     * @param id
     */
    private void getComicsList(String id) {
        apiInterface = APIClient.getClient(activity).create(APIInterface.class);
        int comicId = Integer.parseInt(id);
        Call<ResponseModel> call = apiInterface.callGetComicsList(comicId,
                "1",
                API_KEY,
                HASH);
        Log.e("id2", call.request().toString());

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.body() != null && response.body().getData() != null) {
                    try {
                        String strJsonOfBody = new Gson().toJson(response.body().getData());
                        Data data = new Gson().fromJson(strJsonOfBody, Data.class);
                        tempComicList = new ArrayList<>();
                        tempComicList.addAll(getStringComicsResultModel(new Gson().toJson(data.getResults())));
                        Log.e("tempDasta", new Gson().toJson(tempComicList));
                        comicsModelList.add(tempComicList.get(0));
                        setViewToComicsList();
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
    List<ComicsModel> getStringComicsResultModel(String jsonArray) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<ComicsModel>>() {
        }.getType();
        List<ComicsModel> myModelList = gson.fromJson(jsonArray, listType);
        return myModelList;
    }

    /**
     * comics list is set to the recyclerview
     */
    private void setViewToComicsList() {
        pb_progress.setVisibility(View.GONE);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        rv_comics.setLayoutManager(mLayoutManager);
        rv_comics.setItemAnimator(new DefaultItemAnimator());
        marvelComicsAdapter = new MarvelComicsAdapter(comicsModelList, activity);
        rv_comics.setAdapter(marvelComicsAdapter);

        if (comicsModelList.size() > 0) {
            ll_details.setVisibility(View.VISIBLE);
        }
        else {
            ll_details.setVisibility(View.GONE);
        }
    }
}