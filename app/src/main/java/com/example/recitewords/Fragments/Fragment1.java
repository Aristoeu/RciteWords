package com.example.recitewords.Fragments;

import android.content.Intent;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recitewords.Activities.WordsActivity;
import com.example.recitewords.Bean.WordBean;
import com.example.recitewords.R;
import com.example.recitewords.Utils.OnWordListener;
import com.example.recitewords.Utils.PostWord;
import com.google.gson.Gson;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fragment1 extends BaseFragment {
    View view;
    ImageView top,bot,pronounce;
    Button button1,button2,button3;
    TextView word,pronunciation,translation,floatView,sentences;
    String Word,Translation,Pronunciation,MusicUrl="0",Sentences;
    private static final int COMPLETED = 0;
    public ArrayList<String> InfoName = new ArrayList<String>();
    public ArrayList<ArrayList<String>> Info = new ArrayList<ArrayList<String>>();

    int index = 0;

    private Handler handler = new Handler(){
        @Override
        public  void handleMessage(Message msg){
            if (msg.what==COMPLETED){
                word.setText(Word);
                translation.setText(Translation);
                pronunciation.setText("/"+Pronunciation+"/");
                sentences.setText(Sentences);
                sentences.setMovementMethod(ScrollingMovementMethod.getInstance());
            }
        }
    };
    OnWordListener onWordListener = new OnWordListener() {
        @Override
        public void onSuccess(String result) {
            if (result.length()>100){
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
                    Word = queryText;
                    Pronunciation = "["+voiceArray[0]+"]";
                    MusicUrl = voiceUrlArray[0];
                    Translation = meanText;
                    Sentences = exampleText;
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("<<<>>>", "解析过程中出错！！！");
                }
                Message msg = new Message();
                msg.what = COMPLETED;
                handler.sendMessage(msg);
            }
            else translation.setText("没查到翻译");
        }

        @Override
        public void onError() {

        }

        @Override
        public void onFailed() {

        }
    };
    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment1, null);
        return view;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1, null);
        top = view.findViewById(R.id.rev_top);
        bot = view.findViewById(R.id.rev_bot);
        button1 = view.findViewById(R.id.btn_know);
        button2 = view.findViewById(R.id.btn_half_know);
        button3 = view.findViewById(R.id.btn_not_know);
        word = view.findViewById(R.id.word);
        pronunciation = view.findViewById(R.id.pronunciation);
        translation = view.findViewById(R.id.translation);
        floatView = view.findViewById(R.id.Float);
        pronounce = view.findViewById(R.id.pronounce);
        sentences = view.findViewById(R.id.sentences);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        final String wordToKnow;
        ReadText();

        wordToKnow = InfoName.get(index);
        final PostWord postWord = new PostWord();
        postWord.post_word(InfoName.get(0),onWordListener);
        word.setText(wordToKnow);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postWord.post_word(InfoName.get(++index),onWordListener);
                floatView.setVisibility(View.VISIBLE);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postWord.post_word(InfoName.get(++index),onWordListener);
                floatView.setVisibility(View.VISIBLE);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postWord.post_word(InfoName.get(++index),onWordListener);
                floatView.setVisibility(View.VISIBLE);
            }
        });
        floatView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatView.setVisibility(View.INVISIBLE);
            }
        });
        pronounce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MusicUrl!="0"){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.d("<<<>>>",MusicUrl);
                                MediaPlayer mediaPlayer = new MediaPlayer();
                                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                                mediaPlayer.setDataSource(MusicUrl);
                                mediaPlayer.prepare();
                                mediaPlayer.start();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                }
            }
        });
    }
    public void ReadText() {
        Resources resources = this.getResources();
        InputStream StdInfo = null;
        try {
            StdInfo = resources.openRawResource(R.raw.ielts);
            if (StdInfo.available() == 0)
                return;
            if (StdInfo != null) {
                //用utf-8读取文件
                Scanner input = new Scanner(StdInfo, "utf-8");
                while (input.hasNext()) {
                    //将读取出来的数据文件
                    ArrayList<String> SubInfo = new ArrayList<String>();
                    String word = input.next();
                    //String pronunciation = input.next();
                    //String translation = input.next();
                    SubInfo.add(word);
                    //SubInfo.add(pronunciation);
                    //SubInfo.add(translation);
                    InfoName.add(word);
                    Info.add(SubInfo);
                }

            }
        } catch (IOException e) {
            Toast.makeText(getContext(), "not exist!", Toast.LENGTH_LONG);
        }
    }

}
