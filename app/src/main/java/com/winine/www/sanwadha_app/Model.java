package com.winine.www.sanwadha_app;

import android.content.ComponentName;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class Model extends AppCompatActivity {
    TextToSpeech toSpeech;
    int result;
    EditText editTextVoice;
    String text="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model);

        editTextVoice =(EditText)findViewById(R.id.edtTxtVoice);
        toSpeech= new TextToSpeech(Model.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS){
                    result=toSpeech.setLanguage(Locale.getDefault());
                }
                else{
                    Toast.makeText(getApplicationContext(),"Feature not supported in your device",Toast.LENGTH_LONG).show();
                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_mic);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "New Message", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//
                text = editTextVoice.getText().toString();

                if(text=="")
                {
                    Snackbar.make(view, "Please input something to speak!", Snackbar.LENGTH_LONG)
                       .setAction("Action", null).show();
                }
                else {

                    toSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
                    text="";
                }

            }
        });
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab_mic2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setComponent(new ComponentName("com.google.cloud.android.speech","com.google.cloud.android.speech.SpeechActivity"));
                startActivity(intent);
            }
        });
    }
}
