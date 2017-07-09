package com.winine.www.sanwadha_app;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Yasintha on 7/9/2017.
 */

public class TabChats extends Fragment {
    Button btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_tab_chats, container, false);

        btn = (Button) rootView.findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StickersActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
        //return inflater.inflate(R.layout.activity_tab_chats, container, false);
    }

//    public void click_Public(View v){
//            Intent intent = new Intent(getActivity(), LoginActivity.class);
//            startActivity(intent);
//
//    }
}

