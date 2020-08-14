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

public class AdapterClassCollageRankingList extends RecyclerView.Adapter<AdapterClassCollageRankingList.MyViewHolder>
{
    Activity context;
    ArrayList<CollageRanking> list;

    public AdapterClassCollageRankingList(ArrayList<CollageRanking> list, Activity context)
    {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterClassCollageRankingList.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clglist, parent, false);
        return new AdapterClassCollageRankingList.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterClassCollageRankingList.MyViewHolder holder, int position) {

        holder.tvFullName.setText(list.get(position).getClgFullName());

        final int position_copy = position;
        holder.linear_layout_ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewRankingImage.class);
                intent.putExtra("COLLEGE_FULL_NAME", list.get(position_copy).getClgFullName());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvFullName;
        LinearLayout linear_layout_ranking;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvFullName = itemView.findViewById(R.id.tvFullName);
            linear_layout_ranking = itemView.findViewById(R.id.linear_layout_ranking);

        }
    }

}


