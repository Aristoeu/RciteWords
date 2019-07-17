package com.example.recitewords;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
