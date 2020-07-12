package mobile.project.absensisiswa.MenuGuru;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import mobile.project.absensisiswa.R;

public class AbsensiAction extends AppCompatActivity {
    EditText nama, mataPelajaran;
    Spinner kelas, bidangStudi;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private EditText viewTgl;
    private Button btnTgl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absensi_action);

        nama = (EditText) findViewById(R.id.Abnama);
        mataPelajaran = (EditText) findViewById(R.id.AbmataPelajaran);
        kelas = (Spinner) findViewById(R.id.Abkelas);
        bidangStudi = (Spinner) findViewById(R.id.AbbidangStudi);

        viewTgl = (EditText) findViewById(R.id.tglAbsen);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        btnTgl = (Button) findViewById(R.id.btnTgl);
        btnTgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });
    }


    private void showDateDialog(){

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                viewTgl.setText(dateFormatter.format(newDate.getTimeInMillis()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }

    public void alpha(View view) {

        String stringNama = nama.getText().toString();
        String stringKelas = kelas.getSelectedItem().toString();
        String stringPelajaran = mataPelajaran.getText().toString();
        String stringStudi = bidangStudi.getSelectedItem().toString();
        String stringTgl = viewTgl.getText().toString();

        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("Absensi_siswa").child(stringKelas).child(stringTgl).push();

        DatabaseReference keyKelas = mRef.child("Kelas");
        DatabaseReference keyNama = mRef.child("Nama");
        DatabaseReference keyBS = mRef.child("Bidang_Studi");
        DatabaseReference keyMP = mRef.child("Mata_Pelajaran");
        DatabaseReference keyKet = mRef.child("Keterangan");

        keyNama.setValue(stringNama);
        keyKelas.setValue(stringKelas);
        keyBS.setValue(stringStudi);
        keyMP.setValue(stringPelajaran);
        keyKet.setValue("Alpha");

        Toast.makeText(getApplicationContext(), "Berhasil Menambah", Toast.LENGTH_SHORT).show();

        nama.setText("");

    }

    public void izin(View view) {
        String stringNama = nama.getText().toString();
        String stringKelas = kelas.getSelectedItem().toString();
        String stringPelajaran = mataPelajaran.getText().toString();
        String stringStudi = bidangStudi.getSelectedItem().toString();
        String stringTgl = viewTgl.getText().toString();

        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("Absensi_siswa").child(stringKelas).child(stringTgl).push();

        DatabaseReference keyKelas = mRef.child("Kelas");
        DatabaseReference keyNama = mRef.child("Nama");
        DatabaseReference keyBS = mRef.child("Bidang_Studi");
        DatabaseReference keyMP = mRef.child("Mata_Pelajaran");
        DatabaseReference keyKet = mRef.child("Keterangan");

        keyNama.setValue(stringNama);
        keyKelas.setValue(stringKelas);
        keyBS.setValue(stringStudi);
        keyMP.setValue(stringPelajaran);
        keyKet.setValue("Izin");

        Toast.makeText(getApplicationContext(), "Berhasil Menambah", Toast.LENGTH_SHORT).show();

        nama.setText("");
    }

    public void hadir(View view) {
        String stringNama = nama.getText().toString();
        String stringKelas = kelas.getSelectedItem().toString();
        String stringPelajaran = mataPelajaran.getText().toString();
        String stringStudi = bidangStudi.getSelectedItem().toString();
        String stringTgl = viewTgl.getText().toString();

        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("Absensi_siswa").child(stringKelas).child(stringTgl).push();

        DatabaseReference keyKelas = mRef.child("Kelas");
        DatabaseReference keyNama = mRef.child("Nama");
        DatabaseReference keyBS = mRef.child("Bidang_Studi");
        DatabaseReference keyMP = mRef.child("Mata_Pelajaran");
        DatabaseReference keyKet = mRef.child("Keterangan");

        keyNama.setValue(stringNama);
        keyKelas.setValue(stringKelas);
        keyBS.setValue(stringStudi);
        keyMP.setValue(stringPelajaran);
        keyKet.setValue("Hadir");

        Toast.makeText(getApplicationContext(), "Berhasil Menambah", Toast.LENGTH_SHORT).show();

        nama.setText("");
    }
}
