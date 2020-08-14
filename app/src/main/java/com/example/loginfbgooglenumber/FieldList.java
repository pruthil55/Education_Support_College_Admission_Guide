package com.example.loginfbgooglenumber;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class FieldList extends ArrayAdapter<Field>
{

    private Activity context;
    private List<Field> fieldList;

    public FieldList(Activity context,List<Field> fieldList)
    {
        super(context,R.layout.layout_field_list,fieldList);
        this.context = context;
        this.fieldList = fieldList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.layout_field_list, null , true);

        TextView tvFieldName = (TextView)listViewItem.findViewById(R.id.tvFieldName);
        TextView tvFieldRating = (TextView)listViewItem.findViewById(R.id.tvFieldRating);
        TextView tvNoOfFieldRater = (TextView)listViewItem.findViewById(R.id.tvNoOfFieldRater);


        Field field = fieldList.get(position);

        tvFieldName.setText(field.getFieldName().toUpperCase());
        tvFieldRating.setText(String.valueOf(field.getFieldRating()));
        tvNoOfFieldRater.setText(String.valueOf(field.getNoOfFieldRater()));

        return  listViewItem;
    }



}
