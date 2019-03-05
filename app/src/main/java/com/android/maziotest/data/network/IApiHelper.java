package com.android.maziotest.data.network;

import com.android.maziotest.data.model.Pizza;
import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.http.GET;

import java.util.ArrayList;

/**
 * Created by zack_barakat
 */

public interface IApiHelper {


    @GET("tests/pizzas.json")
    Observable<ArrayList<Pizza>> getPizzas();

    class Factory {
        public static final int NETWORK_CALL_TIMEOUT = 30;

        public static IApiHelper create(Retrofit retrofit) {

            return retrofit.create(IApiHelper.class);
        }
    }
}
