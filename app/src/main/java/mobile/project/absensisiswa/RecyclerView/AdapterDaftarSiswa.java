package mobile.project.absensisiswa.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import mobile.project.absensisiswa.R;

public class AdapterDaftarSiswa extends RecyclerView.Adapter<AdapterDaftarSiswa.ViewHolder> {
    List<ModelDaftarSiswa> list;
    Context context;
    String warna, spesialis, key;

    public AdapterDaftarSiswa(List<ModelDaftarSiswa> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_daftar_siswa, parent, false);
        ViewHolder myHoder = new ViewHolder(view);

        return myHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        ModelDaftarSiswa mylist = list.get(position);

        holder.Kelas.setText(mylist.getKelas());
        holder.Nama.setText(mylist.getNama());
        holder.BS.setText(mylist.getBidang_Studi());
        holder.MP.setText(mylist.getMata_Pelajaran());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView Kelas;
        TextView Nama;
        TextView BS;
        TextView MP;

        public ViewHolder(View itemView) {
            super(itemView);
            Nama = (TextView) itemView.findViewById(R.id.rvNama);
            Kelas = (TextView) itemView.findViewById(R.id.rvKelas);
            BS = (TextView) itemView.findViewById(R.id.rvBS);
            MP = (TextView) itemView.findViewById(R.id.rvMP);

        }
    }

    @Override
    public int getItemCount() {
        int arr = 0;

        try {
            if (list.size() == 0) {
                arr = 0;
            } else {

                arr = list.size();
            }
        } catch (Exception e) {
        }
        return arr;
    }

    AdapterDaftarSiswa.OnItemClick onItemClick;

    public void setOnItemClick(AdapterDaftarSiswa.OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick {
        void getPosition(int pos); //pass any things
    }
}

