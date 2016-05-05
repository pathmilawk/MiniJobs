package com.example.pathmilawk.minijobs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
//import org.apache.http.client.HttpClient;
//import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

public class Login extends AppCompatActivity {

    EditText email,password;
    Button login, register;

    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();

    private static final String LOGIN_URL = "http://176.32.230.51/pathmila.com/minijobs/login.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final boolean status=false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=(EditText)findViewById(R.id.email);
        password= (EditText) findViewById(R.id.password);
        login= (Button) findViewById(R.id.login);
        register= (Button) findViewById(R.id.signup);

        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.login:
                        new AttemptLogin().execute();
                        break;
                    case R.id.signup:
                        Intent i = new Intent(Login.this, HomeScreen.class);
                        startActivity(i);
                        break;

                    default:
                        break;
                }
            }
        });
        register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //Toast.makeText(Login.this, "Invalid login details", Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String getEmail(){
        return email.getText().toString();
    }
    public String getPassword(){
        return password.getText().toString();
    }

    // Class with extends AsyncTask class
    class AttemptLogin extends AsyncTask<String, String, String> {

        boolean failure = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Login.this);
            pDialog.setMessage("Attempting login...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag
            int success;
            String emailVal = getEmail();
            String passwordVal = getPassword();
            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("email", emailVal));
                params.add(new BasicNameValuePair("password", passwordVal));

                Log.d("request!", "starting");
                // getting product details by making HTTP request
                JSONObject json = jsonParser.makeHttpRequest(
                        LOGIN_URL, "POST", params);

                // check your log for json response
                Log.d("Login attempt", json.toString());

                // json success tag
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    Log.d("Login Successful!", json.toString());
                    Intent i = new Intent(Login.this, HomeScreen.class);
                    finish();
                    startActivity(i);
                    return json.getString(TAG_MESSAGE);
                }else{
                    Log.d("Login Failure!", json.getString(TAG_MESSAGE));
                    //Toast.makeText(Login.this, "Invalid login details", Toast.LENGTH_LONG).show();
                    return json.getString(TAG_MESSAGE);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            if (file_url != null){
                Toast.makeText(Login.this, file_url, Toast.LENGTH_LONG).show();
            }

        }
    }
}

