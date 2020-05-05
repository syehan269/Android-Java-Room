package com.syehan.room2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.syehan.room2.roomdir.Sword;
import com.syehan.room2.roomdir.SwordVM;

public class ActivityDetail extends AppCompatActivity {

    private EditText name, id;
    private Button btn;
    public static final String extra_rep = "com.syehan.room2.reply";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        btn = findViewById(R.id.btn);
        name = findViewById(R.id.et_name);
        SwordVM swordVM = ViewModelProviders.of(ActivityDetail.this).get(SwordVM.class);

        btn.setOnClickListener(view -> {
            Intent intent = new Intent();
            if (TextUtils.isEmpty(name.getText())){
                Toast.makeText(ActivityDetail.this, "value null", Toast.LENGTH_SHORT).show();
            }else {
                String getName = name.getText().toString();
                Sword sword = new Sword();
                sword.setName(getName);
                swordVM.insertSword(sword);

                intent.putExtra("getName", getName);
            }
            finish();
        });

    }
}
