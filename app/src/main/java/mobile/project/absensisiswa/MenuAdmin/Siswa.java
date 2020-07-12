package mobile.project.absensisiswa.MenuAdmin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import mobile.project.absensisiswa.Akun.Registrasi;
import mobile.project.absensisiswa.MenuGuru.HomeGuru;
import mobile.project.absensisiswa.R;

public class Siswa extends AppCompatActivity {

    //loginsession
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Pass = "passKey";
    public static final String Emaill = "emailKey";
    SharedPreferences sharedpreferences;

    EditText Email, Password;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListner;
    FirebaseUser mUser;
    String email,displayName, password;
    ProgressDialog dialog;
    public static final String userEmail="";

    public static final String TAG="LOGIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siswa);

    }

    public void inputSiswa(View view) {
        Intent intent = new Intent(Siswa.this, InputSiswa.class);
        startActivity(intent);
        finish();
    }

    public void daftarSiswa(View view) {
        Intent intent = new Intent(Siswa.this, DaftarSiswa.class);
        startActivity(intent);
        finish();
    }

    public void inputPelajaran(View view) {
        Intent intent = new Intent(Siswa.this, InputPelajaran.class);
        startActivity(intent);
        finish();
    }

    public void daftarPelajaran(View view) {
        Intent intent = new Intent(Siswa.this, LihatPelajaran.class);
        startActivity(intent);
        finish();
    }
}
