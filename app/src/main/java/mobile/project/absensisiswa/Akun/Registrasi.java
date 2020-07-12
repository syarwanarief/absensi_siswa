package mobile.project.absensisiswa.Akun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import mobile.project.absensisiswa.R;

public class Registrasi extends AppCompatActivity {

    EditText name,email,password, noTlp;
    Button mRegisterbtn;
    FirebaseAuth mAuth;
    DatabaseReference mdatabase;
    String Name,Email,Password, NoTelp;
    ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);
        name = (EditText)findViewById(R.id.fullName);
        noTlp = (EditText) findViewById(R.id.noHP);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        mRegisterbtn = (Button)findViewById(R.id.register);

        // for authentication using FirebaseAuth.
        mAuth = FirebaseAuth.getInstance();
        mDialog = new ProgressDialog(this);
        mdatabase = FirebaseDatabase.getInstance().getReference().child("Users");
    }

    public void register(View view) {
        Name = name.getText().toString().trim();
        NoTelp = noTlp.getText().toString().trim();
        Email = email.getText().toString().trim();
        Password = password.getText().toString().trim();

        if (TextUtils.isEmpty(Name)){
            Toast.makeText(Registrasi.this, "Masukkan Nama", Toast.LENGTH_SHORT).show();
            return;
        }else if (TextUtils.isEmpty(Email)){
            Toast.makeText(Registrasi.this, "Masukkan Email", Toast.LENGTH_SHORT).show();
            return;
        }else if (TextUtils.isEmpty(NoTelp)){
            Toast.makeText(Registrasi.this, "Masukkan No Telp", Toast.LENGTH_SHORT).show();
            return;
        }else if (TextUtils.isEmpty(Password)){
            Toast.makeText(Registrasi.this, "Masukkan Password", Toast.LENGTH_SHORT).show();
            return;
        }else if (Password.length()<6){
            Toast.makeText(Registrasi.this,"Masukkan Password Minimal 6 Karakter",Toast.LENGTH_SHORT).show();
            return;
        }
        mDialog.setMessage("Creating User please wait...");
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
        mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //sendEmailVerification();
                    mDialog.dismiss();
                    OnAuth(task.getResult().getUser());
                    mAuth.signOut();
                    Intent intent = new Intent(Registrasi.this, Login.class);
                    startActivity(intent);
                    finish();
                } else if (!task.isSuccessful()) {
                    Toast.makeText(Registrasi.this, "Format Email Salah atau Email Sudah Terdaftar", Toast.LENGTH_SHORT).show();
                    mDialog.dismiss();
                } else {
                    Toast.makeText(Registrasi.this, "Email Atau Password Sudah Ada", Toast.LENGTH_SHORT).show();
                    mDialog.dismiss();
                }
            }
        });
    }

    private void OnAuth(FirebaseUser user) {
        createAnewUser(user.getUid());
    }

    private void createAnewUser(String uid) {
        User user = BuildNewuser();
        mdatabase.child(uid).setValue(user);
    }

    private User BuildNewuser() {
        return new User(
               getFullName(),
                getNoTelp(),
                getUserEmail(),
                getPassword()
        );
    }

    public String getFullName() {

        return Name;
    }

    public String getNoTelp() {

        return NoTelp;
    }

    public String getUserEmail() {

        return Email;
    }

    public String getPassword() {

        return Password;
    }
}
