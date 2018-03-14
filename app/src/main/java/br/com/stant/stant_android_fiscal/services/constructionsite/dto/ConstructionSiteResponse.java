package br.com.stant.stant_android_fiscal.services.constructionsite.dto;

import java.util.Date;

/**
 * Created by rachidcalazans on 9/12/16.
 */
public class ConstructionSiteResponse {

    private int id;
    private String title;
    private String latitude;
    private String longitude;
    private String district;
    private String zip_code;
    private String address;
    private String address_complement;
    private String state;
    private String street_number;
    private String project_image;
    private Date begin_at;
    private Date end_prediction_at;

    public ConstructionSiteResponse() {}

    public ConstructionSiteResponse(int id, String title, String latitude, String longitude, String district, String zip_code, String address, String address_complement, String state, String street_number, String project_image, Date begin_at, Date end_prediction_at) {
        this.id = id;
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
        this.district = district;
        this.zip_code = zip_code;
        this.address = address;
        this.address_complement = address_complement;
        this.state = state;
        this.street_number = street_number;
        this.project_image = project_image;
        this.begin_at = begin_at;
        this.end_prediction_at = end_prediction_at;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getDistrict() {
        return district;
    }

    public String getZipCode() {
        return zip_code;
    }

    public String getAddress() {
        return address;
    }

    public String getAddressComplement() {
        return address_complement;
    }

    public String getState() {
        return state;
    }

    public String getStreetNumber() {
        return street_number;
    }

    public String getProjectImage() {
        return project_image;
    }

    public Date getBeginAt() {
        return begin_at;
    }

    public Date getEndPredictionAt() {
        return end_prediction_at;
    }
}
