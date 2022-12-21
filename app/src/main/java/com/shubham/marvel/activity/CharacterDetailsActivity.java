package com.shubham.marvel.activity;

import static com.shubham.marvel.utils.AppConstants.API_KEY;
import static com.shubham.marvel.utils.AppConstants.HASH;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shubham.marvel.R;
import com.shubham.marvel.models.apiResponse.ResponseModel;
import com.shubham.marvel.models.character.CharacterModel;
import com.shubham.marvel.models.character.Data;
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
    private List<CharacterModel> characterModelList;
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
        characterId = getIntent().getStringExtra("characterId");
        Log.e("id", characterId);
        if (characterId != null) {
            getCharactersDetails(characterId);
        }
    }

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
    }
}