package com.winine.www.sanwadha_app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {

    Button login;
    EditText username,password;
    ProgressBar progressBar;
    private static String Serviceurl ;
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    private static final String TAG_LoginResult = "LoginResult";
    private static final String TAG_UserID ="UserID";

    String un;
    String pw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupUI(findViewById(R.id.parent));

        login = (Button) findViewById(R.id.btnLogin);
        username =(EditText)findViewById(R.id.edtUsername);
        password =(EditText)findViewById(R.id.edtPass);

       // progressBar.setVisibility(View.GONE);

    }

  // keyboard hide
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(LoginActivity.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    public void Click_login(View v){
        un = username.getText().toString();
        pw = password.getText().toString();
        Serviceurl = "http://sanwadhawebservice.azurewebsites.net/Sanwadhaservice.svc/Login/"+un+"/"+pw+"";
        new LoginAsyn().execute();
//        Intent i = new Intent(LoginActivity.this,MainTabActivity.class);
//        startActivity(i);

    }
    public void Click_signup(View v){
        Intent i = new Intent(LoginActivity.this,SignupActivity.class);
        startActivity(i);


    }
    class LoginAsyn extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try {
                pDialog = new ProgressDialog(LoginActivity.this);
                pDialog.setMessage("Logging In. Please wait....");
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
            String result="false";
            try {
                // getting JSON Object
                // Note that create product url accepts POST method
                JSONObject json = jsonParser.makeHttpRequest(Serviceurl, "POST", params);

                // check log cat fro response
                Log.d("Create Response", json.toString());

                // check for success tag
                try {
                    JSONObject value = json.getJSONObject(TAG_LoginResult);
                    String  UserID=value.getString(TAG_UserID);

                    if (value !=null) {

                        // successfully created product
                        result="true";
                        Intent i = new Intent(getApplicationContext(), MainTabActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("PS_Username", un);
                        i.putExtras(bundle);
                        un = null;
                        startActivity(i);

                        // closing this screen
                        finish();
                    } else {
                        result="false";
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

