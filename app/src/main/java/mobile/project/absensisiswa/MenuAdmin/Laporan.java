package mobile.project.absensisiswa.MenuAdmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import mobile.project.absensisiswa.R;
import mobile.project.absensisiswa.RecyclerView.AdapterDaftarSiswa;
import mobile.project.absensisiswa.RecyclerView.AdapterLaporan;
import mobile.project.absensisiswa.RecyclerView.ModelDaftarSiswa;
import mobile.project.absensisiswa.RecyclerView.ModelLaporan;

public class Laporan extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private EditText viewTgl;
    private Button btnTgl, tampil;
    List<ModelLaporan> list;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan);

        viewTgl = (EditText) findViewById(R.id.viewTanggal);
        tampil = (Button) findViewById(R.id.tampilkan);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        btnTgl = (Button) findViewById(R.id.btnTgl);
        btnTgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        tampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog = new ProgressDialog(Laporan.this);
                dialog.setMessage("Memuat Data...");
                dialog.setIndeterminate(true);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

                Runnable progressRunnable = new Runnable() {

                    @Override
                    public void run() {
                        dialog.cancel();
                    }
                };

                Handler pdCanceller = new Handler();
                pdCanceller.postDelayed(progressRunnable, 30000);

                Spinner kelas = (Spinner) findViewById(R.id.pilihKelas);

                String keyKelas = kelas.getSelectedItem().toString();
                String keyTgl = viewTgl.getText().toString();

                final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvLaporan);
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Absensi_siswa").child(keyKelas).child(keyTgl);
                reference.keepSynced(true);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list = new ArrayList<ModelLaporan>();

                        for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                            ModelLaporan model = new ModelLaporan();
                            ModelLaporan value = dataSnapshot1.getValue(ModelLaporan.class);
                            String vkelas = value.getKelas();
                            String vnama = value.getNama();
                            String vBS = value.getBidang_Studi();
                            String vPelajaran = value.getMata_Pelajaran();
                            String vKet = value.getKeterangan();
                            model.setNama(vnama);
                            model.setKelas(vkelas);
                            model.setBidang_Studi(vBS);
                            model.setMata_Pelajaran(vPelajaran);
                            model.setKeterangan(vKet);
                            list.add(model);
                            AdapterLaporan adapter = new AdapterLaporan(list, Laporan.this);
                            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(Laporan.this, 1);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(adapter);
                            dialog.dismiss();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(), "Data Tidak Ditemukan ", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
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

    public void Tampilkan(View view) {

    }
}
