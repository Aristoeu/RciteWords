package com.example.recitewords.Utils;


import android.util.Log;

import com.example.recitewords.Bean.WordBean;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.UUID;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class PostWord implements WordModel {
    @Override
    public void post_word(final String word, final OnWordListener onWordListener) {
        long curtime = System.currentTimeMillis()/1000;
        Log.d(TAG,"<<<>>>"+curtime);
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        Sha256 sha256 = new Sha256();
        String sign = sha256.getSHA256("0bf7efd417f60436"+word+uuid+curtime+"sUUcjT0eZmqt3oc8xkNgfCZoAhWOYLyb");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("q",word)
                .add("from","en")
                .add("to","zh-CHS")
                .add("appKey","0bf7efd417f60436")
                .add("salt",uuid)
                .add("sign",sign)
                .add("signType","v3")
                .add("curtime",curtime+"").build();
        final Request request = new Request.Builder()
                .url("https://openapi.youdao.com/api")
                .post(body).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG,"<<<<e="+e);
                onWordListener.onFailed();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()) {
                    String d = response.body().string();
                    Log.d(TAG,"<<<<d="+d);
                    //Log.d(TAG,wordBean.getBasic().getExplains().get(0)+"<<<>>>");
                    onWordListener.onSuccess(d);
                }else onWordListener.onError();
            }
        });

    }
}
