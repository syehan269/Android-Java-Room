package com.syehan.room2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.syehan.room2.roomdir.Sword;
import com.syehan.room2.roomdir.SwordVM;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private SwordVM swordVM;
    public static int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.rv);

        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ActivityDetail.class);
            startActivity(intent);
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        SwordAdapter swordAdapter = new SwordAdapter(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(swordAdapter);

        swordVM = ViewModelProviders.of(MainActivity.this).get(SwordVM.class);

        swordVM.getAllSword().observe(this, swordAdapter::setSword);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete){
            AlertDialog.Builder aleBuilder = new AlertDialog.Builder(this);
            aleBuilder.setTitle("Delete All")
                    .setMessage("All item will deleted")
                    .setPositiveButton("Delete", (dialogInterface, i) -> {
                        swordVM.deleteSword();
                        Toast.makeText(this, "item deleted", Toast.LENGTH_SHORT).show();
                    }).setNegativeButton("Cancel", (dialogInterface, i) -> {

            });
            aleBuilder.show();
        }

        return super.onOptionsItemSelected(item);
    }
}
