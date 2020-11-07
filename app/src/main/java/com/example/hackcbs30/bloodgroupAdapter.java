package com.example.hackcbs30;

import android.media.tv.TvContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class bloodgroupAdapter extends RecyclerView.Adapter<bloodgroupAdapter.MyViewHolder>{

    private String[] data;
    public bloodgroupAdapter(String[] data) {
        this.data=data;
    }

    @NonNull
    @Override
    public bloodgroupAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.blood_group,parent,false);

        return new bloodgroupAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull bloodgroupAdapter.MyViewHolder holder, int position) {
        String type=data[position];
        holder.textView.setText(type);


    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView =itemView.findViewById(R.id.blood_type);
        }
    }
}
