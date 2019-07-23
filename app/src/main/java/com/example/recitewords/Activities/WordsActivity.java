package com.example.recitewords.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recitewords.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordsActivity extends AppCompatActivity {
    public ArrayList<String> InfoName = new ArrayList<String>();
    public ArrayList<ArrayList<String>> Info = new ArrayList<ArrayList<String>>();
    public ListView listView;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        ReadText();
        listView = findViewById(R.id.StdInfo);
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,R.layout.item_layout,R.id.chosen_word,InfoName
        );*/
        MyAdapter adapter = new MyAdapter(InfoName);
        listView.setAdapter(adapter);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
            Toast.makeText(this, "not exist!", Toast.LENGTH_LONG);
        }
    }
    public class MyAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<String> list;
        private Map<Integer,Boolean> map=new HashMap<>();
        private int position;

        public MyAdapter(ArrayList<String> list) {
            this.context = getApplicationContext();
            this.list = list;

        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            position = i;
            ViewHolder holder = null;
            if (view == null) {
                holder = new ViewHolder();
                //引入布局
                view = View.inflate(context, R.layout.item_layout, null);
                //实例化对象
                holder.word = view.findViewById(R.id.chosen_word);
                holder.mCheckBox =view.findViewById(R.id.tv_upper);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            //给控件赋值
            holder.word.setText(list.get(i));
            holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked==true){
                        map.put(position,true);
                    }else {
                        map.remove(position);
                    }
                }
            });
            holder.word.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(WordsActivity.this,DetailActivity.class);
                    intent.putExtra("index",i);
                    startActivity(intent);
                }
            });

            if(map!=null&&map.containsKey(position)){
                holder.mCheckBox.setChecked(true);
            }else {
                holder.mCheckBox.setChecked(false);
            }

            return view;
        }

        class ViewHolder {
            TextView word;
            CheckBox mCheckBox;
        }

    }

}
