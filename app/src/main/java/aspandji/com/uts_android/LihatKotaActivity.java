package aspandji.com.uts_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import aspandji.com.uts_android.adapter.KotaAdapter;
import aspandji.com.uts_android.helper.DBHandler;
import aspandji.com.uts_android.helper.RecyclerItemClickListener;
import aspandji.com.uts_android.model.kota;

public class LihatKotaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private KotaAdapter adapter;
    private DBHandler dbHandler;
    private TextView txt_resultadapter;
    private List<kota> kotaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data);

        initComponents();
        initRecyclerView();
        cekDataRecyclerView();
    }

    // FUNGSI INI UNTUK MENG-INIT RECYLERVIEW BESERTA ADAPTERNYA
    private void initRecyclerView(){
        recyclerView = (RecyclerView) findViewById(R.id.rv_mahasiswa);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        dbHandler = new DBHandler(LihatKotaActivity.this);
        kotaList = dbHandler.getSemuaMahasiswa();
        adapter = new KotaAdapter(kotaList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initComponents(){
        txt_resultadapter = (TextView) findViewById(R.id.txt_resultadapter);
    }

    // FUNGSI INI UNTUK MENGECEK APAKAH ADA DATA DI DALEM RECYCLERVIEW ATAU TIDAK
    private void cekDataRecyclerView(){
        if (adapter.getItemCount() == 0){
            txt_resultadapter.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            txt_resultadapter.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            recyclerView.addOnItemTouchListener(
                    new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                        @Override public void onItemClick(View view, int position) {
                            // TODO Handle item click
                            kota kt = kotaList.get(position);
                            String kota = kt.getNama();

                            Toast.makeText(LihatKotaActivity.this, "Klik di " + kota, Toast.LENGTH_SHORT).show();
                        }
                    })
            );
        }
    }
}
