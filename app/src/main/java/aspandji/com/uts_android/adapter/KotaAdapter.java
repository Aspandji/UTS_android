package aspandji.com.uts_android.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import aspandji.com.uts_android.R;
import aspandji.com.uts_android.model.kota;

public class KotaAdapter extends RecyclerView.Adapter<KotaAdapter.KotaViewHolder> {

    private List<kota> kotaList = new ArrayList<>();
    public KotaAdapter(List<kota> kotaList) {
        this.kotaList = kotaList;
    }

    @NonNull
    @Override
    public KotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_kota, parent, false);
        KotaViewHolder kotaViewHolder = new KotaViewHolder(view);
        return kotaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull KotaViewHolder holder, int position) {
        holder.txt_resultnama.setText(kotaList.get(position).getNama());
    }

    @Override
    public int getItemCount() {
        return kotaList.size();
    }


    public class KotaViewHolder extends RecyclerView.ViewHolder {
        TextView txt_resultnama;
//        TextView txt_resulttempatlahir;

        public KotaViewHolder(View itemView) {
            super(itemView);

            txt_resultnama = (TextView) itemView.findViewById(R.id.txt_resultnama);
//            txt_resulttempatlahir = (TextView) itemView.findViewById(R.id.txt_resulttempatlahir);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
