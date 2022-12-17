package com.shubham.marvel.networks;

import static com.shubham.marvel.networks.Urls.GET_CHARACTER_LIST;

import com.shubham.marvel.models.apiResponse.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET(GET_CHARACTER_LIST)
    Call<ResponseModel> callGetCharactersList(@Query("limit") int limit,
                                              @Query("offset") int offset,
                                              @Query("ts") String ts,
                                              @Query("apikey") String apikey,
                                              @Query("hash") String hash);
}
