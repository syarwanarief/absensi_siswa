package mobile.project.absensisiswa.MenuAdmin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class SiswaIPSKelas1 extends AppCompatActivity {

    List<ModelDaftarSiswa> list;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siswa_ips_kelas1);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Memuat Data...");
        dialog.setIndeterminate(true);
        dialog.show();

        Runnable progressRunnable = new Runnable() {

            @Override
            public void run() {
                dialog.cancel();
            }
        };

        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progressRunnable, 30000);


        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvDaftarSiswaIPS);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Daftar_siswa").child("Kelas 1").child("IPS");
        reference.keepSynced(true);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<ModelDaftarSiswa>();

                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    ModelDaftarSiswa model = new ModelDaftarSiswa();
                    ModelDaftarSiswa value = dataSnapshot1.getValue(ModelDaftarSiswa.class);
                    String vkelas = value.getKelas();
                    String vnama = value.getNama();
                    String vBS = value.getBidang_Studi();
                    String vPelajaran = value.getMata_Pelajaran();
                    model.setNama(vnama);
                    model.setKelas(vkelas);
                    model.setBidang_Studi(vBS);
                    model.setMata_Pelajaran(vPelajaran);
                    list.add(model);
                    AdapterDaftarSiswa adapter = new AdapterDaftarSiswa(list, SiswaIPSKelas1.this);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(SiswaIPSKelas1.this, 1);
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

    public void backHome(View view) {

    }
}
