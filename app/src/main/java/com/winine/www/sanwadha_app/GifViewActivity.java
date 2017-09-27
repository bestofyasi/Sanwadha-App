package com.winine.www.sanwadha_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GifViewActivity extends AppCompatActivity {

    private GifView gifView;
    private EditText typedData;
    private int index = 0;

    public String typedWords;

    List<gifImage> list = new ArrayList<>();
    List<gifImage> written = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);



        typedData = (EditText) findViewById(R.id.typedData);
        gifView = (GifView) findViewById(R.id.gif1);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_sendGif);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Sending New Message.........", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
//                Intent i = new Intent(MainTabActivity.this,Model.class);
//                startActivity(i);
               // Toast.makeText(GifViewActivity.this,"Sending......",Toast.LENGTH_LONG).show();
                NotificationGenerator.openActivityNotification(getApplicationContext());
            }
        });

        typedData.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                String[] parts = typedData.getText().toString().split(" ");
                index = 0;
                written.clear();
                for (int i = 0; i < list.size(); i++) {
                    for (int j = 0; j < parts.length; j++) {
                        Log.i("#", parts[j] + " " + list.get(i).getId());

                        if (parts[j].contains(list.get(i).getId())) {

                            written.add(list.get(i));
                        }

                    }

                }
            }
        });

        LoadImages();
        gifPlayWatcher();
        gifView.setGifResource(R.drawable.gif8);


    }

    public void LoadImages() {

        gifImage gifImage1=new gifImage("අහිංසක",R.drawable.ahinsa);
        gifImage gifImage2=new gifImage("අමාරුයි",R.drawable.amarui);
        gifImage gifImage3=new gifImage("දඟකාර",R.drawable.dagakarai);
        gifImage gifImage4=new gifImage("දුකයි",R.drawable.dukai);
        gifImage gifImage5=new gifImage("නටන්න",R.drawable.dance);
        gifImage gifImage6=new gifImage("හිතනවා",R.drawable.hithanawa);
        gifImage gifImage7=new gifImage("කැමති",R.drawable.kemathi);
        gifImage gifImage8=new gifImage("සතුටු",R.drawable.sathtu);
        gifImage gifImage9=new gifImage("සිනා",R.drawable.sina);
        gifImage gifImage10=new gifImage("තරහා",R.drawable.tharaha);
        gifImage gifImage11=new gifImage("අම්මා",R.drawable.mom);
        gifImage gifImage12=new gifImage("කන්න",R.drawable.eat);
        gifImage gifImage13=new gifImage("යන්න",R.drawable.go);
        gifImage gifImage14=new gifImage("බලන්න",R.drawable.look);
        gifImage gifImage15=new gifImage("කියවන්න",R.drawable.read);
        gifImage gifImage16=new gifImage("කියන්න",R.drawable.say);
        gifImage gifImage17=new gifImage("එන්න",R.drawable.come);
        gifImage gifImage18=new gifImage("අක්කා",R.drawable.akka);
        gifImage gifImage19=new gifImage("අතුගානවා",R.drawable.sweep);
        gifImage gifImage20=new gifImage("නැන්දා",R.drawable.nenda);
        gifImage gifImage21=new gifImage("උයනවා",R.drawable.cook);
        gifImage gifImage22=new gifImage("සුභ උදෑසනක්",R.drawable.goodmorning);
        gifImage gifImage23=new gifImage("දැන්",R.drawable.now);
        gifImage gifImage24=new gifImage("හෙට",R.drawable.tomorrow);



        list.add(gifImage1);
        list.add(gifImage2);
        list.add(gifImage3);
        list.add(gifImage4);
        list.add(gifImage5);
        list.add(gifImage6);
        list.add(gifImage7);
        list.add(gifImage8);
        list.add(gifImage9);
        list.add(gifImage10);
        list.add(gifImage11);
        list.add(gifImage12);
        list.add(gifImage13);
        list.add(gifImage14);
        list.add(gifImage15);
        list.add(gifImage16);
        list.add(gifImage17);
        list.add(gifImage18);
        list.add(gifImage19);
        list.add(gifImage20);
        list.add(gifImage21);
        list.add(gifImage22);
        list.add(gifImage23);
        list.add(gifImage24);

    }


    public void NextGif() {

        Log.i("##", String.valueOf(index));
        gifView.pause();
        gifView.play();

        try {
            if (index < written.size()) {


                gifView.setGifResource(written.get(index).getValue());
                index++;


            } else {
                index = 0;

            }


        } catch (Exception e) {

//            gifView.setGifResource(0);
        }


    }

    public void gifPlayWatcher() {

        final CountDownTimer start = new CountDownTimer(30000, 2) {

            public void onTick(long millisUntilFinished) {

                if (gifView.isStopped() == true) {
                    Log.i("-----", String.valueOf(gifView.isStopped()));
                    NextGif();
                }


            }

            public void onFinish() {
                gifPlayWatcher();
            }
        }.start();
    }
}