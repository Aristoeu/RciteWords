package com.example.recitewords.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recitewords.DetailWords.BaseFragment;
import com.example.recitewords.DetailWords.Fragment1;
import com.example.recitewords.DetailWords.Fragment2;
import com.example.recitewords.DetailWords.Fragment3;
import com.example.recitewords.DetailWords.Fragment4;
import com.example.recitewords.DetailWords.PagerSlideAdapter;
import com.example.recitewords.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static int i;
    @BindView(R.id.page_0) TextView text0;
    @BindView(R.id.page_1) TextView text1;
    @BindView(R.id.page_2) TextView text2;
    @BindView(R.id.page_3) TextView text3;
    @BindView(R.id.main_tab_line) ImageView tab_line;
    @BindView(R.id.main_pager) ViewPager mViewPager;
    @BindView(R.id.back)ImageView back;
    @BindView(R.id.wordText)TextView word;
    private int screenWidth;
    private List<BaseFragment> mFragmentList = new ArrayList<>();
    private PagerSlideAdapter adapter;
    public ArrayList<String> InfoName = new ArrayList<String>();
    public ArrayList<ArrayList<String>> Info = new ArrayList<ArrayList<String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        i=getIntent().getIntExtra("index",0);
        Toast.makeText(this,i+"",Toast.LENGTH_LONG).show();
        Log.d("<<<>>>",i+"");
        ButterKnife.bind(this);
        initData();
        initWidth();
        setListener();
        ReadText();
        word.setText(InfoName.get(i));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void initData() {
        mFragmentList.add(new Fragment1());
        mFragmentList.add(new Fragment2());
        mFragmentList.add(new Fragment3());
        mFragmentList.add(new Fragment4());
        adapter = new PagerSlideAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);
        text0.setTextColor(Color.WHITE);
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
            Toast.makeText(this, "not exist!", Toast.LENGTH_LONG).show();
        }
    }

    private void setListener() {

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) tab_line.getLayoutParams();
                lp.leftMargin = screenWidth/4*position + positionOffsetPixels/4;
                tab_line.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position) {
                    case 0:
                        word.setText(InfoName.get(i));
                        break;
                    case 1:
                        word.setText(InfoName.get(i+1));
                        break;
                    case 2:
                        word.setText(InfoName.get(i+2));
                        break;
                    case 3:
                        word.setText(InfoName.get(i+3));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        /*text0.setOnClickListener(this);
        text1.setOnClickListener(this);
        text2.setOnClickListener(this);
        text3.setOnClickListener(this);*/

    }

    private void resetTextView() {
        text0.setTextColor(Color.BLACK);
        text1.setTextColor(Color.BLACK);
        text2.setTextColor(Color.BLACK);
        text3.setTextColor(Color.BLACK);
    }

    private void initWidth() {
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) tab_line.getLayoutParams();
        lp.width = screenWidth / 4;
        tab_line.setLayoutParams(lp);
    }

    /*@Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.page_0:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.page_1:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.page_2:
                mViewPager.setCurrentItem(2);
                break;
            case R.id.page_3:
                mViewPager.setCurrentItem(3);
                break;
        }
    }
*/
}
