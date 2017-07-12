package com.winine.www.sanwadha_app;

import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

/**
 * Created by Yasintha on 7/9/2017.
 */

public class TabNearBy extends Fragment{
    TextToSpeech toSpeech;
    int result;
    EditText editText;
    String text;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_tab_nearby, container, false);
        return rootView;
    }
}
