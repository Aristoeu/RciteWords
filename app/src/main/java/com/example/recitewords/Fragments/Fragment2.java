package com.example.recitewords.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recitewords.R;
import com.example.recitewords.Activities.WordsActivity;

public class Fragment2 extends BaseFragment {
    View view;
    TextView textView;


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment2, null);
        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment2, null);
        textView = view.findViewById(R.id.myWords);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WordsActivity.class);
                startActivity(intent);
            }
        });
    }


}