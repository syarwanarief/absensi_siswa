package mobile.project.absensisiswa.MenuGuru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import mobile.project.absensisiswa.Akun.Login;
import mobile.project.absensisiswa.R;

public class HomeGuru extends AppCompatActivity {

    //loginsession
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Pass = "passKey";
    public static final String Emaill = "emailKey";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_guru);
    }

    //close app
    boolean doubleBackToExitPressedOnce = false;
    public void onBackPressed(){
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tekan kembali untuk keluar aplikasi", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    public void logout(View view) {
        Intent intent = new Intent(HomeGuru.this, Login.class);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.remove(Emaill);
        editor.remove(Pass);
        editor.commit();
        startActivity(intent);
        finish();
    }

    public void tentangSekolah(View view) {
        Intent intent = new Intent(HomeGuru.this,ProfileSekolah.class);
        startActivity(intent);
    }

    public void TentangAplikasi(View view) {
        Intent intent = new Intent(HomeGuru.this,TentangApp.class);
        startActivity(intent);
    }

    public void ActionAbsen(View view) {
        Intent intent = new Intent(HomeGuru.this,AbsensiAction.class);
        startActivity(intent);
    }
}
