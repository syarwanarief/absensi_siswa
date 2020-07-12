package mobile.project.absensisiswa.Akun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import mobile.project.absensisiswa.MenuAdmin.HomeAdmin;
import mobile.project.absensisiswa.MenuGuru.HomeGuru;
import mobile.project.absensisiswa.R;

public class Login extends AppCompatActivity {

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
        setContentView(R.layout.activity_login);

        Email = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.password);
        dialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (mUser != null) {
                    email = Email.getText().toString().trim();
                    if (email.equals("databasesmkn1bu@gmail.com") || email.equals("adminsman1bu@gmail.com")){
                        Intent intent = new Intent(Login.this, HomeAdmin.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }else{
                        Intent intent = new Intent(Login.this, HomeGuru.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                }
                else
                {
                    Log.d(TAG,"AuthStateChanged:Keluar");
                }

            }
        };
    }

    public void register(View view) {
        Intent intent = new Intent( Login.this, Registrasi.class);
        startActivity(intent);
    }


    public void login(View view) {
        userSign();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Emaill, email);
        editor.putString(Pass, password);
        editor.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //removeAuthSateListner is used  in onStart function just for checking purposes,it helps in logging you out.
        mAuth.removeAuthStateListener(mAuthListner);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListner != null) {
            mAuth.removeAuthStateListener(mAuthListner);
        }

    }

    private void userSign() {
        email = Email.getText().toString().trim();
        displayName = Email.getText().toString().trim();
        password = Password.getText().toString().trim();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(displayName)) {
            Toast.makeText(Login.this, "Email/Nama Pengguna Masih Kosong", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(Login.this, "Password Masih Kosong", Toast.LENGTH_SHORT).show();
            return;
        }


        dialog.setMessage("Mohon Tunggu...");
        dialog.setIndeterminate(true);
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    dialog.dismiss();
                    Toast.makeText(Login.this, "Email/Password Salah", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }else {
                    dialog.dismiss();

                    email = Email.getText().toString().trim();
                    if (email.equals("databasesmkn1bu@gmail.com") || email.equals("adminsman1bu@gmail.com")){
                        Intent intent = new Intent(Login.this, HomeAdmin.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }else{
                        Intent intent = new Intent(Login.this, HomeGuru.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
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
}
