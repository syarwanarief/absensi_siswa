package mobile.project.absensisiswa.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mobile.project.absensisiswa.R;

public class AdapterLaporan extends RecyclerView.Adapter<AdapterLaporan.ViewHolder> {
    List<ModelLaporan> list;
    Context context;

    public AdapterLaporan(List<ModelLaporan> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_laporan, parent, false);
        ViewHolder myHoder = new ViewHolder(view);

        return myHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        ModelLaporan mylist = list.get(position);

        holder.Kelas.setText(mylist.getKelas());
        holder.Nama.setText(mylist.getNama());
        holder.BS.setText(mylist.getBidang_Studi());
        holder.MP.setText(mylist.getMata_Pelajaran());
        holder.Keterangan.setText(mylist.getKeterangan());

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView Kelas;
        TextView Nama;
        TextView BS;
        TextView MP;
        TextView Keterangan;

        public ViewHolder(View itemView) {
            super(itemView);
            Nama = (TextView) itemView.findViewById(R.id.rvNama);
            Kelas = (TextView) itemView.findViewById(R.id.rvKelas);
            BS = (TextView) itemView.findViewById(R.id.rvBS);
            MP = (TextView) itemView.findViewById(R.id.rvMP);
            Keterangan = (TextView) itemView.findViewById(R.id.rvKeterangan);

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

    AdapterLaporan.OnItemClick onItemClick;

    public void setOnItemClick(AdapterLaporan.OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick {
        void getPosition(int pos); //pass any things
    }
}

