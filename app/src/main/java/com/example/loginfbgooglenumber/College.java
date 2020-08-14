package com.example.loginfbgooglenumber;

public class College
{
    String clgName;
    float clgRating;
    int noOfCollegeRater;

    public College()
    {

    }

    public College(String clgName, float clgRating, int noOfCollegeRater) {
        this.clgName = clgName;
        this.clgRating = clgRating;
        this.noOfCollegeRater = noOfCollegeRater;
    }

    public String getClgName() {
        return clgName;
    }

    public float getClgRating() {
        return clgRating;
    }

    public int getNoOfCollegeRater() {
        return noOfCollegeRater;
    }
}
