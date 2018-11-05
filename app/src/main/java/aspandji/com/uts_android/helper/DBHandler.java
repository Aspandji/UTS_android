package aspandji.com.uts_android.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import aspandji.com.uts_android.model.kota;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_kota"; // NAMA DATABASE
    private static final String TABLE_KOTA = "table_kota"; // NAMA TABEL
    private static final String COLUMN_ID = "id"; // NAMA KOLOM ID
    private static final String COLUMN_NAMA = "nama"; // NAMA KOLOM NAMA

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // FUNGSI UNTUK MEMBUAT DATABASENYA
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_KOTA + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAMA + " TEXT)";
        db.execSQL(CREATE_USER_TABLE);
    }

    // FUNGSI UNTUK MENGECEK DATABASE ADA ATAU TIDAK.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KOTA);
        onCreate(db);
    }

    // FUNGSI UNTUK TAMBAH DATA KOTA
    public void tambahKota(kota mahasiswa){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMA, mahasiswa.getNama());

        db.insert(TABLE_KOTA, null, values);
        db.close();
    }

    // FUNGSI UNTUK AMBIL 1 DATA KOTA
    public kota getKota(int id_mahasiswa){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_KOTA, new String[]{COLUMN_ID, COLUMN_NAMA},
                COLUMN_ID + "=?", new String[]{String.valueOf(id_mahasiswa)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        kota kota = new kota(cursor.getString(1));
        return kota;
    }

    // FUNGSI UNTUK AMBIL SEMUA DATA KOTA
    public List<kota> getSemuaMahasiswa(){
        List<kota> mahasiswaList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_KOTA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                kota mahasiswa = new kota(cursor.getString(1));
                mahasiswaList.add(mahasiswa);
            } while (cursor.moveToNext());
        }
        return mahasiswaList;
    }

    // FUNGSI MENGHITUNG ADA BEBERAPA DATA
    public int getKotaCount(){
        String countQuery = "SELECT * FROM " + TABLE_KOTA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    // FUNGSI UPDATE DATA KOTA
    public int updateDataKota(kota kota) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMA, kota.getNama());
        return db.update(TABLE_KOTA, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(kota.getId())});
    }

    // FUNGSI HAPUS DATA 1 KOTA
    public void hapusDataKota(kota kota) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_KOTA, COLUMN_ID + " = ?",
                new String[]{String.valueOf(kota.getId())});
        db.close();
    }

    // FUNGSI UNTUK MENGHAPUS SEMUA DATA KOTA
    public void hapusSemuaDataMahasiswa(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_KOTA);
    }
}

