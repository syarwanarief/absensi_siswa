package mobile.project.absensisiswa.MenuAdmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import mobile.project.absensisiswa.R;

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
        intent.putExtra("kelas", "1");
        startActivity(intent);
    }

    public void kelas2(View view) {
        Intent intent = new Intent(DaftarSiswa.this, DaftarProdi.class);
        intent.putExtra("kelas", "2");
        startActivity(intent);
    }

    public void kelas3(View view) {
        Intent intent = new Intent(DaftarSiswa.this, DaftarProdi.class);
        intent.putExtra("kelas", "3");
        startActivity(intent);
    }
}
