package mobile.project.absensisiswa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import mobile.project.absensisiswa.Akun.Login;
import mobile.project.absensisiswa.MenuAdmin.HomeAdmin;
import mobile.project.absensisiswa.MenuGuru.HomeGuru;

public class MainActivity extends Activity {

    public static int SPLASH_TIME_OUT = 2300;
    //loginsession
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Pass = "passKey";
    public static final String Emaill = "emailKey";
    public static final String Akses = "aksesKey";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        final String email = sharedpreferences.getString("emailKey", "");

        if (sharedpreferences.contains(Emaill) && sharedpreferences.contains(Pass)) {
            new Handler().postDelayed(new Runnable() {
                public void run() {

                    if (email.equals("databasesmkn1bu@gmail.com") || email.equals("adminsman1bu@gmail.com")){
                        Intent homeIntent = new Intent(MainActivity.this, HomeAdmin.class);
                        startActivity(homeIntent);
                        finish();
                    }else{
                        Intent homeIntent = new Intent(MainActivity.this, HomeGuru.class);
                        startActivity(homeIntent);
                        finish();
                    }
                }
            },SPLASH_TIME_OUT);

        }else {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    Intent homeIntent = new Intent(MainActivity.this, Login.class);
                    startActivity(homeIntent);
                    finish();
                }
            },SPLASH_TIME_OUT);
        }
    }
}
