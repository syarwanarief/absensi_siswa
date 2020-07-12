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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import mobile.project.absensisiswa.R;
import mobile.project.absensisiswa.RecyclerView.AdapterPelajaran;
import mobile.project.absensisiswa.RecyclerView.ModelPelajaran;
import mobile.project.absensisiswa.RecyclerView.ModelPelajaran;

public class LihatPelajaran extends AppCompatActivity {

    private Button btnTgl, tampil;
    List<ModelPelajaran> list;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_pelajaran);


        tampil = (Button) findViewById(R.id.tampilkanPelajaran);

        tampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog = new ProgressDialog(LihatPelajaran.this);
                dialog.setMessage("Memuat Data...");
                dialog.setIndeterminate(true);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

                final Runnable progressRunnable = new Runnable() {

                    @Override
                    public void run() {
                        dialog.cancel();
                    }
                };

                Handler pdCanceller = new Handler();
                pdCanceller.postDelayed(progressRunnable, 30000);

                Spinner kelas = (Spinner) findViewById(R.id.pilihKelasPel);

                String keyKelas = kelas.getSelectedItem().toString();

                final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvPelajaran);
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Daftar_Pelajaran").child(keyKelas);
                reference.keepSynced(true);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list = new ArrayList<ModelPelajaran>();

                        for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                            ModelPelajaran model = new ModelPelajaran();
                            ModelPelajaran value = dataSnapshot1.getValue(ModelPelajaran.class);
                            String vkelas = value.getKelas();
                            String vPelajaran = value.getMata_Pelajaran();
                            model.setKelas(vkelas);
                            model.setMata_Pelajaran(vPelajaran);
                            list.add(model);
                            AdapterPelajaran adapter = new AdapterPelajaran(list, LihatPelajaran.this);
                            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(LihatPelajaran.this, 1);
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
}
