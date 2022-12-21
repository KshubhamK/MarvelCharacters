package com.shubham.marvel.networks;

import static com.shubham.marvel.networks.Urls.APP_BASE_URL;
import static com.shubham.marvel.networks.Urls.GET_CHARACTER_DETAILS;
import static com.shubham.marvel.networks.Urls.GET_CHARACTER_LIST;
import static com.shubham.marvel.networks.Urls.GET_COMICS;

import com.shubham.marvel.models.apiResponse.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {
    @GET(GET_CHARACTER_LIST)
    Call<ResponseModel> callGetCharactersList(@Query("limit") int limit,
                                              @Query("offset") int offset,
                                              @Query("ts") String ts,
                                              @Query("apikey") String apikey,
                                              @Query("hash") String hash);

    @GET(GET_CHARACTER_DETAILS)
    Call<ResponseModel> callGetCharactersDetails(@Path("characterId") int characterId,
                                              @Query("ts") String ts,
                                              @Query("apikey") String apikey,
                                              @Query("hash") String hash);

    @GET(GET_COMICS)
    Call<ResponseModel> callGetComicsList(@Path("comicId") int comicId,
                                          @Query("ts") String ts,
                                          @Query("apikey") String apikey,
                                          @Query("hash") String hash);
}
