package com.winine.www.sanwadha_app;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SignupActivity extends AppCompatActivity {

    Button _btnSignup;
    EditText username,password,fname,phone,email,con;
    ProgressBar progressBar;
    private static String Serviceurl ;
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    private static final String TAG_SignUpResult = "RegisterResult";
    private static final String TAG_UserID ="UserID";

    String un,pw,ph,fn,eml;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        _btnSignup = (Button) findViewById(R.id.btnSignup);

        username =(EditText)findViewById(R.id.edtTxt_signUp_username);
        password =(EditText)findViewById(R.id.edtTxt_signUp_password);
        fname =(EditText)findViewById(R.id.edtTxt_signUp_fName);
        phone =(EditText)findViewById(R.id.edtTxt_signUp_phone);
        email =(EditText)findViewById(R.id.edtTxt_signUp_email);
        con =(EditText)findViewById(R.id.edtTxt_signUp_com_password);
        //back button
//        getActionBar().setDisplayHomeAsUpEnabled(true);
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

    public void ClickSignUp(View v){
        un = username.getText().toString();
        pw = password.getText().toString();
        fn = fname.   getText().toString();
        eml= email.   getText().toString();
        ph = phone.   getText().toString();

        Serviceurl = "http://sanwadhawebservice.azurewebsites.net/Sanwadhaservice.svc/Register/"+un+"/"+pw+"/"+fn+"/"+eml+"/"+ph+"";
        new LoginAsyn().execute();
//        Intent i = new Intent(LoginActivity.this,MainTabActivity.class);
//        startActivity(i);

        Toast.makeText(getApplicationContext(), "Successfully Registered ....!", Toast.LENGTH_SHORT).show();
                username.setText("");
                password.setText("");
                fname.setText("");
                email.setText("");
                phone.setText("");
                con.setText("");

    }
    class LoginAsyn extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try {
                pDialog = new ProgressDialog(SignupActivity.this);
                pDialog.setMessage("Please wait....");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(true);
                pDialog.show();
            }catch (Exception e) {

            }
        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {


            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            String result="true";
            try {
                // getting JSON Object
                // Note that create product url accepts POST method
                JSONObject json = jsonParser.makeHttpRequest(Serviceurl, "POST", params);

                // check log cat fro response
                Log.d("Create Response", json.toString());

                // check for success tag
                try {
                    JSONObject value = json.getJSONObject(TAG_SignUpResult);
                    String  RU =value.getString(TAG_UserID);

                    if (value !=null) {
                        result="false";
                        // successfully created product


                        // closing this screen

                    } else {
                        result="true";
                        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("PS_Username", un);
                        i.putExtras(bundle);
                        un = null;
                        startActivity(i);
                        finish();
                    }
                } catch (JSONException e) {
                    Log.d("Create exc", e.toString());
                }
            }catch (Exception e) {
                Log.d("Create exccc", e.toString());
            }
            return result;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String result) {
            // dismiss the dialog once done
            if (result=="false"){
                pDialog.hide();
                Toast.makeText(getApplicationContext(), "Login failed ....!", Toast.LENGTH_SHORT).show();

            }else {
                pDialog.dismiss();
            }
        }

    }
}
