package com.winine.www.sanwadha_app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


class CustomAdapter extends ArrayAdapter<String>{
    public CustomAdapter(Context context, String[] names) {
        super(context,R.layout.custom_row ,names);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater buckysInflater = LayoutInflater.from(getContext());
        View customView = buckysInflater.inflate(R.layout.custom_row,parent,false);

        String singleNameItem = getItem(position);
        TextView buckyText =(TextView) customView.findViewById(R.id.textView);
        ImageView buckysImage = (ImageView) customView.findViewById(R.id.imageView);

        buckyText.setText(singleNameItem);
        buckysImage.setImageResource(R.drawable.ic_person);
        return customView;

    }
}
