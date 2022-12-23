package com.shubham.marvel.activity;

import static com.shubham.marvel.utils.AppConstants.API_KEY;
import static com.shubham.marvel.utils.AppConstants.HASH;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shubham.marvel.R;
import com.shubham.marvel.adapter.MarvelCharacterAdapter;
import com.shubham.marvel.customListener.PaginationScrollListener;
import com.shubham.marvel.database.repositoryDB.CharacterRepository;
import com.shubham.marvel.models.apiResponse.ResponseModel;
import com.shubham.marvel.models.character.CharacterModel;
import com.shubham.marvel.models.character.Data;
import com.shubham.marvel.networks.APIClient;
import com.shubham.marvel.networks.APIInterface;
import com.shubham.marvel.utils.AppCommonMethods;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MarvelCharacterAdapter.OnClickInterface {
    private APIInterface apiInterface;
    private RecyclerView rv_characters;
    private List<CharacterModel> characterModelList = new ArrayList<>();
    private MarvelCharacterAdapter marvelCharacterAdapter;
    private CharacterRepository characterRepository;
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        activity = MainActivity.this;
        initController();
    }

    private void initController() {
        characterRepository = new CharacterRepository(activity);
        rv_characters = findViewById(R.id.rv_characters);
        checkForNetworkConnection();
    }

    private void checkForNetworkConnection() {
        setViewToCharacterList();
        if (AppCommonMethods.isNetworkAvailable(activity)) {
            characterRepository.deleteAllCharacters();
            getCharactersList(pageNumber, false);
            Log.e("Network", "available");
        }
        else {
            getLiveData();
        }
    }

    private void getLiveData() {
        characterModelList.clear();
        characterRepository.getAllCharacters().observe(MainActivity.this, new Observer<List<CharacterModel>>() {
            @Override
            public void onChanged(List<CharacterModel> characterModelList1) {
                for (CharacterModel characterModel : characterModelList1) {
                    characterModelList.add(characterModel);
                    Log.e("character", new Gson().toJson(characterModel));
                }
                setViewToCharacterList();
            }
        });
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
                        saveImageFileToStorage();
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
        marvelCharacterAdapter = new MarvelCharacterAdapter(characterModelList, activity, this, characterRepository);
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
        Intent intent = new Intent(activity, CharacterDetailsActivity.class);
        intent.putExtra("characterId", characterModelList.get(position).getId());
        startActivity(intent);
    }

    @Override
    public void longClickListener(View v, int position, CharacterModel characterModel, String booked) {
        characterRepository.updateCharacterData(booked, characterModel.getId());
    }

    private void saveImageFileToStorage() {
        String photo_url;
        if (characterModelList.size() > 0) {
            for (CharacterModel characterModel : characterModelList) {
                if (characterModel.getThumbnail().getPath() != null) {
                    photo_url = characterModel.getThumbnail().getPath() + "/landscape_xlarge.jpg";
                    String[] imageName = photo_url.split("/");
                    Glide.with(activity)
                            .asBitmap()
                            .load(photo_url)
                            .into(new SimpleTarget<Bitmap>() {
                                @Override
                                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                    saveToInternalStorage(resource, imageName[10], characterModel);
                                }
                            });
                }
            }
        }
    }

    private String saveToInternalStorage(Bitmap bitmapImage, String imageName, CharacterModel characterModel){
        ContextWrapper cw = new ContextWrapper(activity);
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("Marvel Photos", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,imageName + ".jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        addDataToRoomDataBase(characterModel, mypath.getAbsolutePath());
        return directory.getAbsolutePath();
    }

    private void addDataToRoomDataBase(CharacterModel characterModel, String imagePath) {
        characterModel.setPhotos(imagePath);
        characterModel.setIsSelected("no");
        characterRepository.insertCharacterData(characterModel);
        Log.e("character", "added");
    }
}