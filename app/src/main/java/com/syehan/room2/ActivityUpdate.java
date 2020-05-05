package com.syehan.room2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.syehan.room2.roomdir.Sword;
import com.syehan.room2.roomdir.SwordVM;

public class ActivityUpdate extends AppCompatActivity {

    private EditText editText;
    private SwordVM swordVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        editText = findViewById(R.id.et_up);
        swordVM = ViewModelProviders.of(ActivityUpdate.this).get(SwordVM.class);
        Button button = findViewById(R.id.btn_up);
        int getId = getIntent().getIntExtra("getId", 0);
        String getName = getIntent().getStringExtra("getName");
        editText.setText(getName);

        button.setOnClickListener(view -> {
            swordVM.update(getId, editText.getText().toString());
            finish();
        });

    }
}
