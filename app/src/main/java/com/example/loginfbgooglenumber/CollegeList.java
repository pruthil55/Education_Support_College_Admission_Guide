package com.example.loginfbgooglenumber;

public class CollegeList
{

    public String collegeName;
    public float collegetotal;
    public int noofuser;

    public CollegeList() {

    }

    public CollegeList(String collegeName, float collegetotal, int noofuser) {
        this.collegeName = collegeName;
        this.collegetotal = collegetotal;
        this.noofuser = noofuser;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public float getCollegetotal() {
        return collegetotal;
    }

    public void setCollegetotal(float collegetotal) {
        this.collegetotal = collegetotal;
    }

    public int getNoofuser() {
        return noofuser;
    }

    public void setNoofuser(int noofuser) {
        this.noofuser = noofuser;
    }
}

















//package com.example.loginfbgooglenumber;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//public class CollegeList extends RecyclerView.Adapter<CollegeList.searchViewHolder> {
//
//    Context context;
//    ArrayList<String> collageName;
//    ArrayList<String> clgRating;
//    ArrayList<String> noOfRaterForCollege;
//
//    @NonNull
//    @Override
//    public CollegeList.searchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.layout_college_list, parent, false);
//
//        return new CollegeList.searchViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull searchViewHolder holder, int position) {
//        holder.tvclgname.setText(collageName.get(position));
//        holder.tvClgRating.setText(clgRating.get(position));
//        holder.tvNoOfCollegeRater.setText(noOfRaterForCollege.get(position));
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//
//    class searchViewHolder extends RecyclerView.ViewHolder {
//
//        TextView tvclgname, tvClgRating, tvNoOfCollegeRater;
//
//        public searchViewHolder(@NonNull View itemView) {
//            super(itemView);
//            tvclgname = (TextView) itemView.findViewById(R.id.tvclgname);
//            tvClgRating = (TextView) itemView.findViewById(R.id.tvClgRating);
//            tvNoOfCollegeRater = (TextView) itemView.findViewById(R.id.tvNoOfCollegeRater);
//
//        }
//    }
//
//    public CollegeList(Context context, ArrayList<String> collageName, ArrayList<String> clgRating, ArrayList<String> noOfRaterForCollege) {
//        this.context = context;
//        this.collageName = collageName;
//        this.clgRating = clgRating;
//        this.noOfRaterForCollege = noOfRaterForCollege;
//    }
//
//}


