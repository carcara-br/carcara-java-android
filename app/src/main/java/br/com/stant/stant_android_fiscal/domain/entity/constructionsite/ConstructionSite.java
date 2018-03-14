package br.com.stant.stant_android_fiscal.domain.entity.constructionsite;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by rachidcalazans on 9/12/16.
 */
public class ConstructionSite implements Serializable {

    private final int id;
    private final String title;
    private final String latitude;
    private final String longitude;
    private final String district;
    private final String zipCode;
    private final String address;
    private final String addressComplement;
    private final String state;
    private final String streetNumber;
    private final String projectImage;
    private final Date beginAt;
    private final Date endPredictionAt;


    public ConstructionSite(int id, String title, String latitude, String longitude, String district, String zipCode, String address, String addressComplement, String state, String streetNumber, String projectImage, Date beginAt, Date endPredictionAt) {
        this.id = id;
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
        this.district = district;
        this.zipCode = zipCode;
        this.address = address;
        this.addressComplement = addressComplement;
        this.state = state;
        this.streetNumber = streetNumber;
        this.projectImage = projectImage;
        this.beginAt = beginAt;
        this.endPredictionAt = endPredictionAt;
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
        return zipCode;
    }

    public String getAddress() {
        return address;
    }

    public String getAddressComplement() {
        return addressComplement;
    }

    public String getState() {
        return state;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getProjectImage() {
        return projectImage;
    }

    public Date getBeginAt() {
        return beginAt;
    }

    public Date getEndPredictionAt() {
        return endPredictionAt;
    }
}
