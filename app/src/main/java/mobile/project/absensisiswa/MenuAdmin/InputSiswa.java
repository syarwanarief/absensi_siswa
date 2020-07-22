package mobile.project.absensisiswa.MenuAdmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import mobile.project.absensisiswa.R;

public class InputSiswa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_siswa);

        final Button button = (Button) findViewById(R.id.save);
        final Spinner kelas = (Spinner) findViewById(R.id.kelas);
        final EditText nama = (EditText) findViewById(R.id.nama);
        final Spinner bidangStudi = (Spinner) findViewById(R.id.bidangStudi);
        final EditText mataPelajaran = (EditText) findViewById(R.id.mataPelajaran);
//        final EditText noTelp = (EditText) findViewById(R.id.noTelp);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stringNama = nama.getText().toString();
                String stringKelas = kelas.getSelectedItem().toString();
                String stringPelajaran = mataPelajaran.getText().toString();
//                String stringNoTelp = noTelp.getText().toString();
                String stringStudi = bidangStudi.getSelectedItem().toString();

                DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("Daftar_siswa").child(stringKelas).child(stringStudi).push();

                DatabaseReference keyKelas = mRef.child("Kelas");
                DatabaseReference keyNama = mRef.child("Nama");
                DatabaseReference keyBS = mRef.child("Bidang_Studi");
                DatabaseReference keyMP = mRef.child("Mata_Pelajaran");

                keyNama.setValue(stringNama);
                keyKelas.setValue(stringKelas);
                keyBS.setValue(stringStudi);
                keyMP.setValue(stringPelajaran);

//                DatabaseReference mRef1 = FirebaseDatabase.getInstance().getReference("No Telp Wali Murid").child(stringKelas).child(stringNama).push();
//
//                DatabaseReference keynoTelp = mRef1.child("No Telp");
//
//                keynoTelp.setValue(stringNoTelp);

                Toast.makeText(getApplicationContext(), "Berhasil Menambah", Toast.LENGTH_SHORT).show();

                nama.setText("");
//                noTelp.setText("");

            }
        });
    }

    public void save(View view) {

    }
}
