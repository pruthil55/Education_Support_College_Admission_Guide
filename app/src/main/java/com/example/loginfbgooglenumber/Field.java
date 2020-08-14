package com.example.loginfbgooglenumber;

public class Field
{
    String fieldName;
    float fieldRating;
    int noOfFieldRater;

    public Field()
    {

    }

    public Field(String fieldName, float fieldRating, int noOfFieldRater) {
        this.fieldName = fieldName;
        this.fieldRating = fieldRating;
        this.noOfFieldRater = noOfFieldRater;;
    }

    public String getFieldName() {
        return fieldName;
    }

    public float getFieldRating() {
        return fieldRating;
    }

    public int getNoOfFieldRater() {
        return noOfFieldRater;
    }
}
