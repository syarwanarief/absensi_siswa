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

public class AdapterPelajaran extends RecyclerView.Adapter<AdapterPelajaran.ViewHolder> {
    List<ModelPelajaran> list;
    Context context;

    public AdapterPelajaran(List<ModelPelajaran> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_pelajaran, parent, false);
        ViewHolder myHoder = new ViewHolder(view);

        return myHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        ModelPelajaran mylist = list.get(position);

        holder.Kelas.setText(mylist.getKelas());
        holder.MP.setText(mylist.getMata_Pelajaran());

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView Kelas;
        TextView MP;

        public ViewHolder(View itemView) {
            super(itemView);
            Kelas = (TextView) itemView.findViewById(R.id.rvKelasPel);
            MP = (TextView) itemView.findViewById(R.id.rvPelajaranPel);

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

    AdapterPelajaran.OnItemClick onItemClick;

    public void setOnItemClick(AdapterPelajaran.OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick {
        void getPosition(int pos); //pass any things
    }
}

