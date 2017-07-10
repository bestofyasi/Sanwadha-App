package com.winine.www.sanwadha_app;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Yasintha on 7/9/2017.
 */

public class TabContacts extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_tab_contact, container, false);
        String[] names = {"Shenali","Nelunika","Yasintha","Poorvi","Samantha Sir","Indika","Prabhath","Kalana","Chanaka"};
        ListAdapter bukysAdapter = new CustomAdapter(getActivity(),names);
        ListView buckysListView = (ListView) rootView.findViewById(R.id.buckysListView);
        buckysListView.setAdapter(bukysAdapter);

        buckysListView.setOnItemClickListener(

                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String name = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(getActivity(),"Creating new message to "+name,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getActivity(), StickersActivity.class);
                        startActivity(intent);
                    }
                }
        );
        return rootView;
    }
}
