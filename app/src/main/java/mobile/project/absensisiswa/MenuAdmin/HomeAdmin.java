package mobile.project.absensisiswa.MenuAdmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import mobile.project.absensisiswa.Akun.Login;
import mobile.project.absensisiswa.MenuGuru.ProfileSekolah;
import mobile.project.absensisiswa.R;

public class HomeAdmin extends AppCompatActivity {

    //loginsession
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Pass = "passKey";
    public static final String Emaill = "emailKey";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
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
        Intent intent = new Intent(HomeAdmin.this, Login.class);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.remove(Emaill);
        editor.remove(Pass);
        editor.commit();
        startActivity(intent);
        finish();
    }

    public void siswa(View view) {
        Intent intent = new Intent(HomeAdmin.this, Siswa.class);
        startActivity(intent);
    }

    public void profil(View view) {
        Intent intent = new Intent(HomeAdmin.this, ProfileSekolah.class);
        startActivity(intent);
    }

    public void laporan(View view) {
        Intent intent = new Intent(HomeAdmin.this, Laporan.class);
        startActivity(intent);
    }
}
