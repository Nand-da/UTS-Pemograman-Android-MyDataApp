package com.example.myapplication;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
public class DashboardActivity extends AppCompatActivity {
    TextView tvWelcome;
    EditText etNim, etNama, etProdi,
            etKelas, etAlamat, etEmail;
    Button btnTambah, btnLogout;
    ListView listView;
    ArrayList<String> dataList;
    ArrayAdapter<String> adapter;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        tvWelcome = findViewById(R.id.tvWelcome);
        etNim = findViewById(R.id.etNim);
        etNama = findViewById(R.id.etNama);
        etProdi = findViewById(R.id.etProdi);
        etKelas = findViewById(R.id.etKelas);
        etAlamat = findViewById(R.id.etAlamat);
        etEmail = findViewById(R.id.etEmail);
        btnTambah = findViewById(R.id.btnTambah);
        btnLogout = findViewById(R.id.btnLogout);
        listView = findViewById(R.id.listView);
        sharedPreferences =
                getSharedPreferences("LOGIN", MODE_PRIVATE);
        String username =
                sharedPreferences.getString("username", "");
        tvWelcome.setText("Selamat Datang " + username);
        dataList = new ArrayList<>();
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dataList
        );
        listView.setAdapter(adapter);
        btnTambah.setOnClickListener(v -> {
            String nim = etNim.getText().toString();
            String nama = etNama.getText().toString();
            String prodi = etProdi.getText().toString();
            String kelas = etKelas.getText().toString();
            String alamat = etAlamat.getText().toString();
            String email = etEmail.getText().toString();
            String data =
                    "NIM : " + nim +
                            "\nNama : " + nama +
                            "\nProdi : " + prodi +
                            "\nKelas : " + kelas +
                            "\nAlamat : " + alamat +
                            "\nEmail : " + email;
            dataList.add(data);
            adapter.notifyDataSetChanged();
            etNim.setText("");
            etNama.setText("");
            etProdi.setText("");
            etKelas.setText("");
            etAlamat.setText("");
            etEmail.setText("");
        });
        btnLogout.setOnClickListener(v -> {
            SharedPreferences.Editor editor =
                    sharedPreferences.edit();
            editor.clear();
            editor.apply();
            Intent intent =
                    new Intent(DashboardActivity.this,
                            MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}