package com.example.recitewords.Utils;


import android.content.SharedPreferences;
import android.util.Log;

import com.example.recitewords.Bean.WordBean;
import com.example.recitewords.Bean.Wording;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
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
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .build();
        final Request request = new Request.Builder()
                .url("http://dict-co.iciba.com/api/dictionary.php?w="+word+"&type=xml&key=186EAC39722EE1E622DB00BCFA009E55")
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
                XStream xStream = new XStream(new DomDriver("UTF-8"));
                xStream.processAnnotations(Wording.class);
                if(response.isSuccessful()) {
                    String d = response.body().string();
                    //Wording wording = (Wording) xStream.fromXML(d);
                    Log.d(TAG,"<<<<d="+d);
                    //Log.d(TAG,d+"<<<>>>");
                   // onWordListener.onSuccess(d);

                   /* JSONObject jsonObject = null;
                    try {
                        jsonObject = XML.toJSONObject(d);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("<<<>>>","exception");
                    }
                    Log.d("<<<>>>",jsonObject.toString());*/
                   onWordListener.onSuccess(d);

                }else onWordListener.onError();
            }
        });

    }
    public static void parseJinshanEnglishToChineseXMLWithPull(String result) {

        try {

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(result));
            int eventType = xmlPullParser.getEventType();

            String queryText = "";      //查询文本
            String voiceText = "";      //发音信息
            String voiceUrlText = "";   //发音地址信息
            String meanText = "";       //基本释义信息
            String exampleText = "";    //例句信息

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    //开始解析
                    case XmlPullParser.START_TAG: {
                        switch (nodeName) {
                            case "key":
                                queryText += xmlPullParser.nextText();
                                break;
                            case "ps":
                                voiceText += xmlPullParser.nextText() + "|";
                                break;
                            case "pron":
                                voiceUrlText += xmlPullParser.nextText() + "|";
                                break;
                            case "pos":
                                meanText += xmlPullParser.nextText() + "  ";
                                break;
                            case "acceptation":
                                meanText += xmlPullParser.nextText();
                                break;
                            case "orig":
                                exampleText += xmlPullParser.nextText();
                                exampleText = exampleText.substring(0,exampleText.length()-1);
                                break;
                            case "trans":
                                exampleText += xmlPullParser.nextText();
                                break;
                            default:
                                break;
                        }
                    }
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }

            String[] voiceArray = voiceText.split("\\|");
            String[] voiceUrlArray = voiceUrlText.split("\\|");

            meanText = meanText.substring(0,meanText.length()-1);
            exampleText = exampleText.substring(1,exampleText.length());

            //创建SharedPreferences.Editor对象，指定文件名为


            Log.d("queryText",queryText);
            Log.d("voiceEnText","["+voiceArray[0]+"]");
            Log.d("voiceEnUrlText",voiceUrlArray[0]);
            Log.d("voiceAmText","["+voiceArray[1]+"]");
            Log.d("voiceAmUrlText",voiceUrlArray[1]);
            Log.d("meanText",meanText);
            Log.d("exampleText",exampleText);



        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "解析过程中出错！！！");
        }

    }

}
