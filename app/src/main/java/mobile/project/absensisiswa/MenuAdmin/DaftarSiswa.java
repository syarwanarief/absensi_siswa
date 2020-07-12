package mobile.project.absensisiswa.MenuAdmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import mobile.project.absensisiswa.R;
import mobile.project.absensisiswa.RecyclerView.AdapterDaftarSiswa;
import mobile.project.absensisiswa.RecyclerView.ModelDaftarSiswa;

public class DaftarSiswa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_siswa);
    }

    public void backHome(View view) {
        Intent intent = new Intent(DaftarSiswa.this, HomeAdmin.class);
        startActivity(intent);
        finish();
    }

    public void kelas1(View view) {
        Intent intent = new Intent(DaftarSiswa.this, SiswaKelas1.class);
        startActivity(intent);
    }

    public void kelas2(View view) {
        Intent intent = new Intent(DaftarSiswa.this, SiswaKelas2.class);
        startActivity(intent);
    }

    public void kelas3(View view) {
        Intent intent = new Intent(DaftarSiswa.this, SIswaKelas3.class);
        startActivity(intent);
    }
}
