package mobile.project.absensisiswa.MenuAdmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import mobile.project.absensisiswa.R;

public class InputPelajaran extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_pelajaran);

        final Button button = (Button) findViewById(R.id.savePel);
        final Spinner kelas = (Spinner) findViewById(R.id.kelasPel);
        final EditText mataPelajaran = (EditText) findViewById(R.id.mataPelajaranPel);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stringKelas = kelas.getSelectedItem().toString();
                String stringPelajaran = mataPelajaran.getText().toString();

                DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("Daftar_Pelajaran").child(stringKelas).push();

                DatabaseReference keyKelas = mRef.child("Kelas");
                DatabaseReference keyMP = mRef.child("Mata_Pelajaran");

                keyKelas.setValue(stringKelas);
                keyMP.setValue(stringPelajaran);

                DatabaseReference mRef1 = FirebaseDatabase.getInstance().getReference("Daftar_Pelajaran").child("All").push();

                DatabaseReference keyPel = mRef1.child("Mata_Pelajaran");

                keyPel.setValue(stringPelajaran);

                Toast.makeText(getApplicationContext(), "Berhasil Menambah", Toast.LENGTH_SHORT).show();

                mataPelajaran.setText("");

            }
        });
    }

    public void save(View view) {

    }
}
