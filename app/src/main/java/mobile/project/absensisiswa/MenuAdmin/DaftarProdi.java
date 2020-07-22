package mobile.project.absensisiswa.MenuAdmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mobile.project.absensisiswa.R;

public class DaftarProdi extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_prodi);

        intent = getIntent();
        Bundle kelas = intent.getExtras();

        Button klik = (Button) findViewById(R.id.klikips);

        klik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = getIntent();
                Bundle kelas = intent.getExtras();

                if (kelas != null){
                    String stringKelas = (String) kelas.get("kelas");

                    if (stringKelas.equals("1")){

                        Intent go = new Intent(DaftarProdi.this, SiswaIPSKelas1.class);
                        startActivity(go);

                    }else if (stringKelas.equals("2")){
                        Intent go = new Intent(DaftarProdi.this, SiswaIPSKelas2.class);
                        startActivity(go);
                    }else{
                        Intent go = new Intent(DaftarProdi.this, SIswaIPSKelas3.class);
                        startActivity(go);
                    }
                }
            }
        });
    }

    public void ipa(View view) {

        intent = getIntent();
        Bundle kelas = intent.getExtras();

        if (kelas != null){
           String stringKelas = (String) kelas.get("kelas");

           if (stringKelas.equals("1")){

               Intent go = new Intent(DaftarProdi.this, SiswaIPAKelas1.class);
               startActivity(go);

           }else if (stringKelas.equals("2")){
               Intent go = new Intent(DaftarProdi.this, SiswaIPAKelas2.class);
               startActivity(go);
           }else{
               Intent go = new Intent(DaftarProdi.this, SIswaIPAKelas3.class);
               startActivity(go);
           }
        }
    }
}
