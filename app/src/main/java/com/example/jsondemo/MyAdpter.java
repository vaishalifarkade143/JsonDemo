package com.example.jsondemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdpter extends RecyclerView.Adapter<MyAdpter.MyViewHolder>
{
    ArrayList al_name;
    ArrayList  al_email;
    ArrayList al_gender;
    MyAdpter(ArrayList al_name, ArrayList al_email, ArrayList al_gender)
    {
            this.al_name = al_name;
            this.al_email = al_email;
            this.al_gender =  al_gender;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(al_name.get(position).toString());
        holder.email.setText(al_email.get(position).toString());
        holder.gender.setText(al_gender.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return al_name.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView name,email,gender;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name=  itemView.findViewById(R.id.si_name);
            email=  itemView.findViewById(R.id.si_email);
            gender=  itemView.findViewById(R.id.si_gender);
        }
    }

}
