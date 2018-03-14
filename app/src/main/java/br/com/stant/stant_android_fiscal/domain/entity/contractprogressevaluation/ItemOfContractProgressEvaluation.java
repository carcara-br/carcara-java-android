package br.com.stant.stant_android_fiscal.domain.entity.contractprogressevaluation;

import java.util.Date;

/**
 * Created by denisvieira on 19/05/17.
 */

public class ItemOfContractProgressEvaluation {

    private int id;
    private int performedPercentage;
    private String serviceTitle;
    private String place;
    private int status;
    private Date beginAt;
    private Date endAt;

    public ItemOfContractProgressEvaluation(int id, int performedPercentage, String serviceTitle, String place, int status, Date beginAt, Date endAt) {
        this.id = id;
        this.performedPercentage = performedPercentage;
        this.serviceTitle = serviceTitle;
        this.place = place;
        this.status = status;
        this.beginAt = beginAt;
        this.endAt = endAt;
    }

    public int getId() {
        return id;
    }

    public int getPerformedPercentage() {
        return performedPercentage;
    }

    public String getServiceTitle() {
        return serviceTitle;
    }

    public String getPlace() {
        return place;
    }

    public int getStatus() {
        return status;
    }

    public Date getBeginAt() {
        return beginAt;
    }

    public Date getEndAt() {
        return endAt;
    }

}
