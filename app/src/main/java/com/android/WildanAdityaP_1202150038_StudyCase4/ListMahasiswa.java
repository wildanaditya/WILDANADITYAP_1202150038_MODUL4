package com.android.WildanAdityaP_1202150038_StudyCase4;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.Wildan.android.WildanAdityaP_1202150038_StudyCase4.R;

public class ListMahasiswa extends AppCompatActivity implements View.OnClickListener {

    //Menginisialisasi semua view dan variable yang digunakan
    private Button btnAsync;
    private ListView lvMahasiswa;
    private ArrayAdapter<String> itemsAdapter;

    //Variabel String Array untuk menyimpan data statis semua nama mahasiswa
    private String[] namaMahasiswa = {"Fitri", "Anya", "Fany", "Sari", "Charles", "Bambang", "Adam",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mahasiswa);

        setTitle("Asynctask"); //Set judul dari activity
        btnAsync = (Button) findViewById(R.id.btnAsync);
        lvMahasiswa = (ListView) findViewById(R.id.lvMahasiswa);
        btnAsync.setOnClickListener(this); //Mendeklarasikan button asynctask dengan onClickListener
    }

    //Methods untuk handle semua view yang di deklarasikan pada onCreate()
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAsync:
                new loadNamaMahasiswa(this).execute(); //Memanggil method asynctask "loadNamaMahasiswa" dengan mengirimkan parameter context
                break;
        }
    }

    //Asynctask methods
    public class loadNamaMahasiswa extends AsyncTask<String[], Integer, String[]>{
        //Inisialisasi semua view yang digunakan
        private ProgressDialog dialog;
        private Context context;
        private ListMahasiswa activity;
        int maxProgress;

        //Constructor Asynctask digunakan untuk menerima paramater yang dikirm dan di deklarasikan  view yang diperlukan
        public loadNamaMahasiswa(ListMahasiswa act) {
            this.activity = act;
            context = activity;
            dialog = new ProgressDialog(context);
        }

        //Method yang dijalankan pertama kali saat method asynctask di execute
        @Override
        protected void onPreExecute() {
            //Konfigurasi progress dialog
            dialog.setTitle("Loading");
            dialog.setCancelable(false);
            dialog.setIndeterminate(true);
            dialog.setMax(100);
            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }

        //Method yang menjalankan proses inti pada asynctask
        @Override
        protected String[] doInBackground(String[]... voids) {
            maxProgress = namaMahasiswa.length; //cek length/size dari array namaMahasiswa
            for (int i=0; i<namaMahasiswa.length; i++) { //looping length namaMahasiswa
                dialog.setMessage((i * 100 / maxProgress)+"%"); //menampilkan pesan total progress load data
            }

            //mengembalikan nilai array namaMahasiswa
            return namaMahasiswa;
        }

        //method dijalankan saat proses background selesai dan menerima data dari background
        @Override
        protected void onPostExecute(final String[] s) {
            //menampilkan handler untuk delay dialog semua 400 millisecond
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //declare itemsAdapter dan populate data dari String namaMahasiswa
                    itemsAdapter = new ArrayAdapter<String>(ListMahasiswa.this, android.R.layout.simple_list_item_1, s);
                    //set listview adapter dengan itemsAdapter
                    lvMahasiswa.setAdapter(itemsAdapter);

                    //pengecekan kondisi jika dialog sudah terbuka, maka dismiss
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }
            }, 400);
        }
    }
}
