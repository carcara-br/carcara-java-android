package br.com.stant.stant_android_fiscal.domain.builders;

import java.util.Date;

import br.com.stant.stant_android_fiscal.domain.enums.constructionsite.ProjectTypeEnum;
import br.com.stant.stant_android_fiscal.services.constructionsite.dto.ConstructionSiteResponse;
import br.com.stant.stant_android_fiscal.util.GuidGenerator;


/**
 * Created by rachidcalazans on 22/02/17.
 */
public class ConstructionSiteResponseBuilder {


    private int id;
    private String title;
    private String latitude;
    private String longitude;
    private String district;
    private String zipCode;
    private String address;
    private String addressComplement;
    private String state;
    private String streetNumber;
    private String projectImage;
    private Date beginAt;
    private Date endPredictionAt;

    public ConstructionSiteResponseBuilder() {
        id = 0;
        title = null;
        latitude = null;
        longitude = null;
        district = null;
        zipCode = null;
        address = null;
        addressComplement = null;
        state = null;
        streetNumber = null;
        projectImage = null;
        beginAt = null;
        endPredictionAt = null;
    }

    public ConstructionSiteResponse build() {
        return new ConstructionSiteResponse(
                id,title,latitude,longitude,district,zipCode,address,addressComplement,
                state,streetNumber,projectImage,beginAt,endPredictionAt
        );
    }

    public ConstructionSiteResponseBuilder id(int id) {
        this.id = id;
        return this;
    }

    public ConstructionSiteResponseBuilder title(String title) {
        this.title = title;
        return this;
    }

    public ConstructionSiteResponseBuilder latitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public ConstructionSiteResponseBuilder longitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public ConstructionSiteResponseBuilder district(String district) {
        this.district = district;
        return this;
    }

    public ConstructionSiteResponseBuilder zipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public ConstructionSiteResponseBuilder address(String address) {
        this.address = address;
        return this;
    }

    public ConstructionSiteResponseBuilder addressComplement(String addressComplement) {
        this.addressComplement = addressComplement;
        return this;
    }

    public ConstructionSiteResponseBuilder state(String state) {
        this.state = state;
        return this;
    }

    public ConstructionSiteResponseBuilder streetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }

    public ConstructionSiteResponseBuilder projectImage(String projectImage) {
        this.projectImage = projectImage;
        return this;
    }

    public ConstructionSiteResponseBuilder beginAt(Date beginAt) {
        this.beginAt = beginAt;
        return this;
    }

    public ConstructionSiteResponseBuilder endPredictionAt(Date endPredictionAt) {
        this.endPredictionAt = endPredictionAt;
        return this;
    }
}
