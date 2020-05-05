package com.syehan.room2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.syehan.room2.roomdir.Sword;
import com.syehan.room2.roomdir.SwordVM;

import java.util.List;

public class SwordAdapter extends RecyclerView.Adapter<SwordAdapter.viewHolder> {

    private Context context;
    private List<Sword> swordList;
    private SwordVM swordVM;

    public SwordAdapter(Context context) {
        this.context = context;
    }

    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Sword item = swordList.get(position);
        int id = item.getId();
        String name = item.getName();
        holder.textView.setText(item.getName());

        //go to update activity
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ActivityUpdate.class);
            intent.putExtra("getId", id);
            intent.putExtra("getName", name);
            context.startActivity(intent);
        });
        
        holder.itemView.setOnLongClickListener(view -> {
            deleteItem(id, name);
            return true;
        });
    }

    //Method delete item when id is match
    private void deleteItem(int id, String name) {

        swordVM = ViewModelProviders.of((FragmentActivity) context).get(SwordVM.class);
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(context);

        alBuilder.setTitle("Delete item")
                .setMessage("Are you sure want to delete "+ name)
                .setPositiveButton("Delete", (dialogInterface, i) -> {
                    swordVM.deleteSingle(id);
                    Toast.makeText(context, "item delete", Toast.LENGTH_SHORT).show();

                }).setNegativeButton("Cancel", (dialogInterface, i) -> {

        });
        alBuilder.show();

    }

    @Override
    public int getItemCount() {
        if (swordList == null){
            return 0;
        }else {
            return swordList.size();
        }
    }

    //method for update list
    void setSword(List<Sword> sword){
        swordList = sword;
        notifyDataSetChanged();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.titletok);
        }
    }
}
