package com.example.recitewords.Fragments;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fragment1 extends BaseFragment {
    View view;
    ImageView top,bot;
    Button button1,button2,button3;
    TextView word,pronunciation,translation,floatView;
    String Word,Translation;
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
            }
        }
    };
    OnWordListener onWordListener = new OnWordListener() {
        @Override
        public void onSuccess(String s) {
            Gson gson = new Gson();
            WordBean wordBean = gson.fromJson(s, WordBean.class);
            if (s.length()>100){
            //word.setText(wordBean.getReturnPhrase().get(0));
            //translation.setText(wordBean.getBasic().getExplains().get(0));
            Word = wordBean.getReturnPhrase().get(0);
            Translation = wordBean.getBasic().getExplains().get(0);
               /* String str = s;
                String regex = "explains\":(.*),\"us-speech\"";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(str);
                while (matcher.find()) {
                    String mTranslation = matcher.group(1);
                    mTranslation=mTranslation.replace("[","");
                    mTranslation=mTranslation.replace("]","");
                    mTranslation=mTranslation.replace("\"","");
                    Log.d("<<<>>>",mTranslation);
                }*/
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
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        final String wordToKnow;
        ReadText();

        wordToKnow = InfoName.get(index);
        final PostWord postWord = new PostWord();
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
