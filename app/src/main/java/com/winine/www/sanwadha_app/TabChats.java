package com.winine.www.sanwadha_app;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Yasintha on 7/9/2017.
 */

public class TabChats extends Fragment {
    Button btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_tab_chats, container, false);

//        btn = (Button) rootView.findViewById(R.id.button);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), StickersActivity.class);
//                startActivity(intent);
//            }
//        });

        String[] names = {"Shenali","Nelunika","Yasintha","Poorvi","Samantha Sir","Indika","Prabhath","Kalana","Chanaka"};
        ListAdapter bukysAdapter = new CustomAdapter(getActivity(),names);
        ListView buckysListView = (ListView) rootView.findViewById(R.id.buckysListView);
        buckysListView.setAdapter(bukysAdapter);

        buckysListView.setOnItemClickListener(

                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String name = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(getActivity(),name,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getActivity(), GifViewActivity.class);
                        startActivity(intent);
                    }
                }
        );
        return rootView;
        //return inflater.inflate(R.layout.activity_tab_chats, container, false);
    }

//    public void click_Public(View v){
//            Intent intent = new Intent(getActivity(), LoginActivity.class);
//            startActivity(intent);
//
//    }
}

