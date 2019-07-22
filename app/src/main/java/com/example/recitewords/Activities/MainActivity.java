package com.example.recitewords.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.recitewords.Fragments.BottomBar;
import com.example.recitewords.Fragments.Fragment1;
import com.example.recitewords.Fragments.Fragment2;
import com.example.recitewords.Fragments.Fragment3;
import com.example.recitewords.Fragments.Fragment4;
import com.example.recitewords.R;
import com.example.recitewords.Utils.OnWordListener;
import com.example.recitewords.Utils.PostWord;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "<<<>>>";

    public static int i =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Log.d(TAG,InfoName.get(0));
        BottomBar bottomBar = findViewById(R.id.bottom_bar);
        bottomBar.setContainer(R.id.fl_container)
                .setTitleBeforeAndAfterColor("#999999", "#008577")
                .addItem(Fragment1.class,
                        "复习",
                        R.drawable.tab_review_unselected,
                        R.drawable.tab_review_selected)
                .addItem(Fragment2.class,
                        "选词",
                        R.drawable.tab_book_unselected,
                        R.drawable.tab_book_selected)
                .addItem(Fragment3.class,
                        "统计",
                        R.drawable.tab_graph_unselected,
                        R.drawable.tab_graph_selected)
                .addItem(Fragment4.class,
                        "设置",
                        R.drawable.tab_settings_unselected,
                        R.drawable.tab_settings_selected)
                .build();
    }

}
