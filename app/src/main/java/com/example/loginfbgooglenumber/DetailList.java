package com.example.loginfbgooglenumber;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class DetailList extends ArrayAdapter<Details>
{

    TextView tvTeachingStaffRating;
    TextView tvEducationalEnvironmentRating;
    TextView tvEducationalResourcesRating;
    TextView tvLibraryServicesRating;
    TextView tvCollegeInfrastructureRating;
    TextView placementReviewRating;



    private Activity context;
    private List<Details> detailList;

    public DetailList(Activity context,List<Details> detailList)
    {
        super(context,R.layout.layout_detail_list,detailList);
        this.context = context;
        this.detailList = detailList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.layout_detail_list, null , true);


         tvTeachingStaffRating = (TextView)listViewItem.findViewById(R.id.tvTeachingStaffRating);
         tvEducationalEnvironmentRating = (TextView)listViewItem.findViewById(R.id.tvEducationalEnvironmentRating);
         tvEducationalResourcesRating = (TextView)listViewItem.findViewById(R.id.tvEducationalResourcesRating);
         tvLibraryServicesRating = (TextView)listViewItem.findViewById(R.id.tvLibraryServicesRating);
         tvCollegeInfrastructureRating = (TextView)listViewItem.findViewById(R.id.tvCollegeInfrastructureRating);
         placementReviewRating = (TextView)listViewItem.findViewById(R.id.placementReviewRating);

        Details details = detailList.get(position);

//        tvTeachingStaffRating.setText(String.valueOf(details.getTeachingStaffRating()));
//        tvEducationalEnvironmentRating.setText(String.valueOf(details.getEducationalEnvironentRating()));
//        tvEducationalResourcesRating.setText(String.valueOf(details.getEducationalResourcesRating()));
//        tvLibraryServicesRating.setText(String.valueOf(details.getLibrartServicesRating()));
//        tvCollegeInfrastructureRating.setText(String.valueOf(details.getCollegeInfrastructureRating()));


        tvTeachingStaffRating.setText(String.valueOf(details.getTeachingStaffRating()));
        tvEducationalEnvironmentRating.setText(String.valueOf(details.getEducationalEnvironmentRating()));
        tvEducationalResourcesRating.setText(String.valueOf(details.getEducationalResourcesRating()));
        tvLibraryServicesRating.setText(String.valueOf(details.getLibrartServicesRating()));
        tvCollegeInfrastructureRating.setText(String.valueOf(details.getCollegeInfrastructureRating()));
        placementReviewRating.setText(String.valueOf(details.getPlacementReviewRating()));

        return  listViewItem;
    }

}
