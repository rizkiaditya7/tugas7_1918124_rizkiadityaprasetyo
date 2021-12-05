package com.example.tugas7_1918124_rizkiaditya;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainRead extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView mListView;
    private CustomListAdapter adapter_off;
    private com.example.tugas7_1918124_rizkiaditya.MyDatabase db;
    private List<tni> Listtni = new ArrayList<tni>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_read);
        db = new com.example.tugas7_1918124_rizkiaditya.MyDatabase(this);

        adapter_off = new CustomListAdapter(this, Listtni);
        mListView = (ListView) findViewById(R.id.list_tni);
        mListView.setAdapter(adapter_off);
        mListView.setOnItemClickListener(this);
        mListView.setClickable(true);
        Listtni.clear();

        List<tni> tni = db.Readtni();
        for (tni mhs : tni) {
            tni daftar = new tni();
            daftar.set_id(mhs.get_id());
            daftar.set_nama(mhs.get_nama());
            daftar.set_umur(mhs.get_umur());
            daftar.set_pangkat(mhs.get_pangkat());
            daftar.set_satuan(mhs.get_satuan());
            Listtni.add(daftar);
            if ((Listtni.isEmpty()))
                Toast.makeText(MainRead.this, "Tidak ada data", Toast.LENGTH_SHORT).show();
            else {
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int
            i, long l) {
        Object o = mListView.getItemAtPosition(i);
        tni detailMhs = (tni) o;

        String Sid = detailMhs.get_id();
        String Snama = detailMhs.get_nama();
        String Sumur = detailMhs.get_umur();
        String Spangkat = detailMhs.get_pangkat();
        String Ssatuan = detailMhs.get_satuan();

        Intent goUpdel = new Intent(MainRead.this,
                com.example.tugas7_1918124_rizkiaditya.MainUpdel.class);
        goUpdel.putExtra("Iid", Sid);
        goUpdel.putExtra("Inama", Snama);
        goUpdel.putExtra("Iumur", Sumur);
        goUpdel.putExtra("Ipangkat", Spangkat);
        goUpdel.putExtra("Isatuan", Ssatuan);
        startActivity(goUpdel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Listtni.clear();
        mListView.setAdapter(adapter_off);

        List<tni> tni = db.Readtni();
        for (tni x : tni) {
            tni daftar = new tni();
            daftar.set_id(x.get_id());
            daftar.set_nama(x.get_nama());
            daftar.set_umur(x.get_umur());
            daftar.set_pangkat(x.get_pangkat());
            daftar.set_satuan(x.get_satuan());
            Listtni.add(daftar);

            if ((Listtni.isEmpty()))
                Toast.makeText(MainRead.this, "Tidak ada data", Toast.LENGTH_SHORT).show();
            else {
            }
        }
    }
}