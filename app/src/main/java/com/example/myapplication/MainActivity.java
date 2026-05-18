package com.example.myapplication;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    EditText etUsername, etPassword;
    Button btnLogin, btnCancel;
    ImageView imgShow;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);
        imgShow = findViewById(R.id.imgShow);
        sharedPreferences =
                getSharedPreferences("LOGIN", MODE_PRIVATE);
        btnLogin.setOnClickListener(v -> {
            String username =
                    etUsername.getText().toString();
            String password =
                    etPassword.getText().toString();
            if(username.isEmpty() || password.isEmpty()){
                Toast.makeText(
                        MainActivity.this,
                        "Data tidak boleh kosong",
                        Toast.LENGTH_SHORT
                ).show();
            }
            else if(username.equals("Murfid")
                    && password.equals("murfid123")){
                SharedPreferences.Editor editor =
                        sharedPreferences.edit();
                editor.putBoolean("isLoggedIn", true);
                editor.putString("username", username);
                editor.apply();
                Intent intent =
                        new Intent(MainActivity.this,
                                DashboardActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(
                        MainActivity.this,
                        "Username atau Password Salah",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
        btnCancel.setOnClickListener(v -> {
            etUsername.setText("");
            etPassword.setText("");
        });
        imgShow.setOnClickListener(v -> {
            if(etPassword.getInputType() ==
                    (InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_PASSWORD)){
                etPassword.setInputType(
                        InputType.TYPE_CLASS_TEXT |
                                InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                );
            } else {
                etPassword.setInputType(
                        InputType.TYPE_CLASS_TEXT |
                                InputType.TYPE_TEXT_VARIATION_PASSWORD
                );
            }
            etPassword.setSelection(
                    etPassword.getText().length()
            );
        });
    }
}