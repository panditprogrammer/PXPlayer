package com.panditprogrammer.pxplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder> {
    private ArrayList<String> arrayList;
    Context context;

    public MyAdapter(ArrayList<String> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.titleView.setText(arrayList.get(position));
        

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView titleView;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            titleView = itemView.findViewById(R.id.titleViewid);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, "clicked on "+this.getAdapterPosition(), Toast.LENGTH_SHORT).show();
        }
    }
}
