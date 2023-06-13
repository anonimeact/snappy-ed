package com.anonimeact.sqliteencryptor.jadwalsholat;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Base {

//    private static String uri = "http://sajak.anonimeact.com:3008/";
    private static String uri2 = "https://sincere-song-133723.firebaseio.com/";
    private static Retrofit retrofit = null;
    private static Retrofit retrofit2 = null;

//    public static Retrofit getRetrofitInstance() {
//        try {
//            if (retrofit == null) {
//                retrofit = new Retrofit.Builder()
//                        .baseUrl(uri)
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .build();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return retrofit;
//    }

    public static Retrofit getInstance2() {
        try {
            if (retrofit2 == null) {
                retrofit2 = new Retrofit.Builder()
                        .baseUrl(uri2)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retrofit2;
    }
}
