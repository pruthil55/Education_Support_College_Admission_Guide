package com.example.loginfbgooglenumber;

public class Details
{

    public float teachingStaffRating , educationalEnvironmentRating , educationalResourcesRating , libraryServicesRating , collegeInfrastructureRating, placementReviewRating;
    public float totalRatingCollege , totalRatingField;
    static String clgName , fieldName;
    int noOfUserCollege;
    int noOfUserField;

    public Details()
    {

    }

    public Details(float teachingStaffRating, float educationalEnvironmentRating, float educationalResourcesRating, float librartServicesRating, float collegeInfrastructureRating, float placementReviewRating) {
        this.teachingStaffRating = teachingStaffRating;
        this.educationalEnvironmentRating = educationalEnvironmentRating;
        this.educationalResourcesRating = educationalResourcesRating;
        this.libraryServicesRating = librartServicesRating;
        this.collegeInfrastructureRating = collegeInfrastructureRating;
        this.placementReviewRating = placementReviewRating;
    }

//    public Details(float teachingStaffRating, float educationalEnvironentRating, float educationalResourcesRating, float librartServicesRating, float collegeInfrastructureRating ,float totalRatingCollege ,float totalRatingField,int noOfUserCollege ,int noOfUserField) {
//        this.teachingStaffRating = teachingStaffRating;
//        this.educationalEnvironentRating = educationalEnvironentRating;
//        this.educationalResourcesRating = educationalResourcesRating;
//        this.libraryServicesRating = librartServicesRating;
//        this.collegeInfrastructureRating = collegeInfrastructureRating;
//        this.totalRatingCollege = totalRatingCollege;
//        this.totalRatingField = totalRatingField;
//        this.noOfUserCollege = noOfUserCollege;
//        this.noOfUserField = noOfUserField;
//    }

    public float getTeachingStaffRating() {
        return teachingStaffRating;
    }

    public float getEducationalEnvironmentRating() {
        return educationalEnvironmentRating;

    }

    public float getEducationalResourcesRating() {
        return educationalResourcesRating;
    }

    public float getLibrartServicesRating() {
        return libraryServicesRating;
    }

    public float getCollegeInfrastructureRating() {
        return collegeInfrastructureRating;
    }

    public float getPlacementReviewRating() {
        return placementReviewRating;
    }

    public float getTotalRatingCollege() {
        return totalRatingCollege;
    }

    public float getTotalRatingField() {
        return totalRatingField;
    }

    public static String getClgName() {
        return clgName;
    }

    public static String getFieldName() {
        return fieldName;
    }

    public int getNoOfUserCollege() {
        return noOfUserCollege;
    }

    public int getNoOfUserField() {
        return noOfUserField;
    }
}
