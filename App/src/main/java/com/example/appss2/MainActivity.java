package com.example.appss2;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    EditText edtconffecienta;
    EditText edtconffecientb;
    TextView txtresult;
    SharedPreferences prefs;
    Button btnVietnamese;
    Button btnEnglish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Initialize SharedPreferences and apply saved language
        prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        String language = prefs.getString("Language", "vi"); // Default to Vietnamese
        setLocale(language);

        // Set layout and initialize views
        setContentView(R.layout.activity_main);
        addViews();

        // Set window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set language button listeners
        btnVietnamese.setOnClickListener(v -> {
            setLocale("vi");
            recreate(); // Refresh activity to apply new language
        });

        btnEnglish.setOnClickListener(v -> {
            setLocale("en");
            recreate(); // Refresh activity to apply new language
        });
    }

    private void setLocale(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources res = getResources();
        Configuration config = res.getConfiguration();
        config.setLocale(locale);
        res.updateConfiguration(config, res.getDisplayMetrics());

        // Save language to SharedPreferences
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("Language", language);
        editor.apply();
    }

    private void addViews() {
        edtconffecienta = findViewById(R.id.edtcoeffienta);
        edtconffecientb = findViewById(R.id.edtcoeffientb);
        txtresult=findViewById(R.id.txtresult);
        btnVietnamese = findViewById(R.id.btnVietnamese);
        btnEnglish = findViewById(R.id.btnEnglish);
    }

    public void do_Solution(View view) {
        String hsa=edtconffecienta.getText().toString();
        double a=Double.parseDouble(hsa);

        double b=Double.parseDouble(edtconffecientb.getText().toString());

        if(a==0 && b==0)
        {

            txtresult.setText(getResources().getText(R.string.title_infinity));
        }
        else if(a==0 && b != 0)
        {
            txtresult.setText(getResources().getText(R.string.title_nosolution));
        }
        else
        {
            txtresult.setText("X= "+(-b/a));
        }
    }


    public void do_next(View view) {
        edtconffecienta.setText("");
        edtconffecientb.setText("");
        edtconffecienta.requestFocus();
    }

    public void do_exit(View view) {
        finish();
    }
}

