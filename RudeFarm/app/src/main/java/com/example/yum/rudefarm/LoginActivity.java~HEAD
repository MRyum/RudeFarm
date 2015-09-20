package com.example.yum.rudefarm;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends ActionBarActivity {
    private Button logbtn;
    private EditText idE;
    public String id;
    public String pass;
    public TextView txt;
    public EditText passE;
    String a;
    WebServerSender webServerSender;
    TextView Join;
    ImageView loginlogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        TranslateAnimation moveAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        moveAnimation.setRepeatCount(Animation.INFINITE);
        moveAnimation.setDuration(4000);
        //loginlogo.setAnimation(moveAnimation);



        logbtn = (Button) findViewById(R.id.Loginbtn);
        Drawable buttonBg = logbtn.getBackground();
        buttonBg.setAlpha(50);
        webServerSender = new WebServerSender("http://linux.kim82536.pe.kr:5000", "login", "POST");
        Join = (TextView) findViewById(R.id.join);
        Join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent;
                myintent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(myintent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

            }
        });
        logbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Sendmsg sendmsg = new Sendmsg();
                idE = (EditText) findViewById(R.id.id_input);
                id = idE.getText().toString();
                passE = (EditText) findViewById(R.id.pw_input);
                pass = passE.getText().toString();
                txt = (TextView) findViewById(R.id.text);
                webServerSender.add("email", id);
                webServerSender.add("password", pass);

                sendmsg.execute();

            }
        });
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

    private class Sendmsg extends AsyncTask<Void, Void, String> {


        @Override
        protected String doInBackground(Void... param) {

            try {
                webServerSender.send();
                a = webServerSender.receiveResult();
                System.out.println("request : " + a);
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
            return a;

        }

        protected void onPostExecute(String s) {
            json(s);


        }

        void json(String s) {

            boolean check = false;
            Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
            try {
                JSONObject jsonObject = new JSONObject(s);
                check = jsonObject.getBoolean("type");
            } catch (JSONException e) {
                e.printStackTrace();
            }


            if (check == true) {
                Toast.makeText(getApplicationContext(), "성공적", Toast.LENGTH_LONG).show();
                finish();
                startActivity(mainIntent);

            } else {
                Toast.makeText(getApplicationContext(), "실패적", Toast.LENGTH_LONG).show();
                finish();
                startActivity(mainIntent);

            }

            webServerSender.clearArg();
        }


    }
}