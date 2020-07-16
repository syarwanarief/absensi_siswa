package mobile.project.absensisiswa.MenuGuru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import mobile.project.absensisiswa.R;
import mobile.project.absensisiswa.SmsGateway;

public class AbsensiAction extends AppCompatActivity {
    EditText nama, mataPelajaran, NoHP;
    Spinner kelas, bidangStudi;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private EditText viewTgl;
    private Button btnTgl;

    private SmsGateway smsGateway;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absensi_action);

        nama = (EditText) findViewById(R.id.Abnama);
        NoHP = (EditText) findViewById(R.id.AbNoHP);
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


    private void showDateDialog() {

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

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

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
        String stringNoHP = NoHP.getText().toString();

        try{
            SmsManager smgr = SmsManager.getDefault();
            smgr.sendTextMessage(stringNoHP,null,"Informasi Absensi anak anda, yang bernama "+stringNama+
                    "di Mata Pelajaran "+stringPelajaran+" pada tgl "+stringTgl+" adalah 'Alpha' ",null,null);
            Toast.makeText(AbsensiAction.this, "Informasi Absensi Berhasil Dikirim Kepada Orangtua Wali", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(AbsensiAction.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
        }

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

        nama.setText("");

    }

    public void izin(View view) {

        String stringNama = nama.getText().toString();
        String stringKelas = kelas.getSelectedItem().toString();
        String stringPelajaran = mataPelajaran.getText().toString();
        String stringStudi = bidangStudi.getSelectedItem().toString();
        String stringTgl = viewTgl.getText().toString();
        String stringNoHP = NoHP.getText().toString();

        try{
            SmsManager smgr = SmsManager.getDefault();
            smgr.sendTextMessage(stringNoHP,null,"Informasi Absensi anak anda, yang bernama "+stringNama+
                    "di Mata Pelajaran "+stringPelajaran+" pada tgl "+stringTgl+" adalah 'Izin' ",null,null);
            Toast.makeText(AbsensiAction.this, "Informasi Absensi Berhasil Dikirim Kepada Orangtua Wali", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(AbsensiAction.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
        }

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

        nama.setText("");
    }

    public void hadir(View view) {

        String stringNama = nama.getText().toString();
        String stringKelas = kelas.getSelectedItem().toString();
        String stringPelajaran = mataPelajaran.getText().toString();
        String stringStudi = bidangStudi.getSelectedItem().toString();
        String stringTgl = viewTgl.getText().toString();
        String stringNoHP = NoHP.getText().toString();

        try{
            SmsManager smgr = SmsManager.getDefault();
            smgr.sendTextMessage(stringNoHP,null,"Informasi Absensi anak anda, yang bernama "+stringNama+
                    "di Mata Pelajaran "+stringPelajaran+" pada tgl "+stringTgl+" adalah 'Hadir' ",null,null);
            Toast.makeText(AbsensiAction.this, "Informasi Absensi Berhasil Dikirim Kepada Orangtua Wali", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(AbsensiAction.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
        }

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

        nama.setText("");
    }

    public void sakit(View view) {

        String stringNama = nama.getText().toString();
        String stringKelas = kelas.getSelectedItem().toString();
        String stringPelajaran = mataPelajaran.getText().toString();
        String stringStudi = bidangStudi.getSelectedItem().toString();
        String stringTgl = viewTgl.getText().toString();
        String stringNoHP = NoHP.getText().toString();

        try{
            SmsManager smgr = SmsManager.getDefault();
            smgr.sendTextMessage(stringNoHP,null,"Informasi Absensi anak anda, yang bernama "+stringNama+
                    "di Mata Pelajaran "+stringPelajaran+" pada tgl "+stringTgl+" adalah 'Sakit' ",null,null);
            Toast.makeText(AbsensiAction.this, "Informasi Absensi Berhasil Dikirim Kepada Orangtua Wali", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(AbsensiAction.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
        }

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
        keyKet.setValue("Sakit");

        nama.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        try {
            // hentikan sms server
            smsGateway.stop();
        } catch (IOException e) {
            Log.e(getClass().getName(), e.getMessage(), e);
        }
    }

}
