package com.shubham.marvel.networks;

import static com.shubham.marvel.networks.Urls.APP_BASE_URL;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.shubham.marvel.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit retrofit = null;
    public static Activity activity = null;

    public static Retrofit getClient(Activity activity) {
        activity = activity;
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.callTimeout(120, TimeUnit.SECONDS);
        httpClient.readTimeout(120, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            if(activity != null) {
                httpClient.addInterceptor(interceptor);
            }
        }

        httpClient.addInterceptor(new Interceptor() {
            @NonNull
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Response response = null;
                Request request = chain.request();
                try {
                    Request original = chain.request();

                    request = original.newBuilder()
//                            .header("Authorization", token)
                            .method(original.method(), original.body())
                            .build();
                    if (chain.request() != null) {
                        response = chain.proceed(chain.request());
                        if (response.code() == 401) {
                        }
                    }

                } catch (Exception e) {
                }
                try {
                    return chain.proceed(request);
                } catch (Exception e) {
                    if(response!=null) {
                        response.close();
                    }
                    return chain.proceed(request);
                }
            }
        });

        OkHttpClient client = httpClient.build();
        try {
            if (client != null && client.cache() != null) {
                client.cache().flush();
                client.cache().delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        retrofit = new Retrofit.Builder()
                .baseUrl(APP_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }
}
