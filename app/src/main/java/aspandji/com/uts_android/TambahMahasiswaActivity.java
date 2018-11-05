package aspandji.com.uts_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import aspandji.com.uts_android.adapter.KotaAdapter;
import aspandji.com.uts_android.helper.DBHandler;
import aspandji.com.uts_android.model.kota;

public class TambahMahasiswaActivity extends AppCompatActivity {

    private EditText et_nama;
    //    private EditText et_tempatlahir;
    private Button button_tambahdata;
    private Button button_lihatdata;
    private Button button_hapusdata;

    private DBHandler dbHandler;
    private KotaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        dbHandler = new DBHandler(this);
        initComponents();
    }

    private void initComponents(){
        et_nama = (EditText) findViewById(R.id.et_nama);
//        et_tempatlahir = (EditText) findViewById(R.id.et_tempatlahir);
        button_tambahdata = (Button) findViewById(R.id.button_tambahdata);
        button_lihatdata = (Button) findViewById(R.id.button_lihatdata);
        //button_hapusdata = (Button) findViewById(R.id.button_hapusdata);

        button_lihatdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TambahMahasiswaActivity.this, LihatKotaActivity.class));
            }
        });

//        button_hapusdata.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dbHandler.hapusSemuaDataMahasiswa();
//                Toast.makeText(TambahMahasiswaActivity.this, "Berhasil Menghapus Semua Data Mahasiswa", Toast.LENGTH_SHORT).show();
//            }
//        });

        button_tambahdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validasiForm();
            }
        });
    }

    // FUNGSI INI UNTUK MEMVALIDASI FORM JIKA ADA YANG KOSONG ATAU TIDAK
    // LALU DILANJUT UNTUK MENJALANKAN PERINTAH SELANJUTNYA
    private void validasiForm(){
        String form_nama = et_nama.getText().toString();
//        String form_tempatlahir = et_tempatlahir.getText().toString();

        if (form_nama.isEmpty()){
            et_nama.setError("Isi nama dulu");
            et_nama.requestFocus();
        } else {
            dbHandler.tambahKota(new kota(form_nama));
            List<kota> mahasiswaList = dbHandler.getSemuaMahasiswa();
            adapter = new KotaAdapter(mahasiswaList);
            adapter.notifyDataSetChanged();

            Toast.makeText(TambahMahasiswaActivity.this, "Berhasil Menambahkan Data", Toast.LENGTH_SHORT).show();
        }
    }
}
