package com.example.loginfbgooglenumber;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder>
{

    Activity context;
    ArrayList<CollegeList> list;

    public AdapterClass(ArrayList<CollegeList> list, Activity context)
    {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_college_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvCollegeName.setText(list.get(position).getCollegeName());
        holder.tvCollegeRating.setText(String.valueOf(list.get(position).getCollegetotal()));
        holder.tvNoOfCollegeRater.setText(String.valueOf(list.get(position).getNoofuser()));


        final int position_copy = position;
        holder.linear_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewFieldRating.class);
                intent.putExtra("COLLEGE_NAME", list.get(position_copy).getCollegeName());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvCollegeName, tvCollegeRating, tvNoOfCollegeRater;
        LinearLayout linear_layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCollegeName = itemView.findViewById(R.id.tvCollegeName);
            tvCollegeRating = itemView.findViewById(R.id.tvCollegeRating);
            tvNoOfCollegeRater = itemView.findViewById(R.id.tvNoOfCollegeRater);

            linear_layout =itemView.findViewById(R.id.linear_layout);


        }
    }
}
