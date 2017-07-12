package com.winine.www.sanwadha_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class StickersActivity extends AppCompatActivity {
    byte simage[];
    ImageView imageupload,imageupload2,imageupload3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stickers);

        imageupload = (ImageView)findViewById(R.id.imageView);
        imageupload2 = (ImageView)findViewById(R.id.imageView2);
        imageupload3 = (ImageView)findViewById(R.id.imageView3);

        imageupload.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select story image"), 1);

            }
        });
        imageupload2.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select story image"), 2);

            }
        });
        imageupload3.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select story image"), 3);

            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int reqCode, int resCode, Intent data) {

        if (resCode == RESULT_OK) {
            if (reqCode == 1) {
                imageupload.setImageURI(data.getData());
            }
        }
        if (resCode == RESULT_OK) {
            if (reqCode == 2) {
                imageupload2.setImageURI(data.getData());
            }
        }
        if (resCode == RESULT_OK) {
            if (reqCode == 3) {
                imageupload3.setImageURI(data.getData());
            }
        }

        Bitmap bitmap = ((BitmapDrawable) imageupload.getDrawable()).getBitmap();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        simage = bos.toByteArray();
    }


    public void click_model(View v){
        Intent i = new Intent(StickersActivity.this,gifPlaying.class);
        startActivity(i);
    }
}
