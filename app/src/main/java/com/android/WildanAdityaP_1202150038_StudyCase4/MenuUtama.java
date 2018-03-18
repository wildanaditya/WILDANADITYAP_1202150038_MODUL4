package com.android.WildanAdityaP_1202150038_StudyCase4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.Wildan.android.WildanAdityaP_1202150038_StudyCase4.R;

public class MenuUtama extends AppCompatActivity implements View.OnClickListener {

    //Menginisialisasi semua view dan variable yang digunakan
    private Button btnListNama;
    private Button btnCariGambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);

        btnListNama = (Button) findViewById(R.id.btnListNama);
        btnCariGambar = (Button) findViewById(R.id.btnCariGambar);
        btnCariGambar.setOnClickListener(this);
        btnListNama.setOnClickListener(this);
    }

    //Methods untuk handle semua view yang di deklarasikan diatas pada onCreate()
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnListNama:
                //Memanggil activity ListMahasiswa dengan Intent
                startActivity(new Intent(MenuUtama.this, ListMahasiswa.class));
                break;
            case R.id.btnCariGambar:
                //Memanggil activity PencariGambar dengan Intent
                startActivity(new Intent(MenuUtama.this, PencariGambar.class));
                break;
        }
    }
}
