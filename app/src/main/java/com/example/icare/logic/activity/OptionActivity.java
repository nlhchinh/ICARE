package com.example.icare.logic.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.icare.R;

public class OptionActivity extends AppCompatActivity {

    ConstraintLayout btn_period_care, btn_health_care, btn_manage_finance, btn_note;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set layout
        setContentView(R.layout.option_layout);

        // find view by id
        btn_period_care = findViewById(R.id.btn_period);
        btn_period_care.setOnClickListener(v -> {
            Intent intent = new Intent(OptionActivity.this, PeriodInputAcivity.class);
            startActivity(intent);
            finish();
        });


    }
}
