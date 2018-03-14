package br.com.stant.stant_android_fiscal.services.itemofcontractprogressevaluation.dto;

import java.util.Date;

/**
 * Created by denisvieira on 19/05/17.
 */

public class ItemOfContractProgressEvaluationResponse {

    private int id;
    private int performed_percentage;
    private String service_title;
    private String place;
    private int status;
    private Date begin_at;
    private Date end_at;

    public ItemOfContractProgressEvaluationResponse(int id, int performed_percentage, String service_title, String place, int status, Date begin_at, Date end_at) {
        this.id = id;
        this.performed_percentage = performed_percentage;
        this.service_title = service_title;
        this.place = place;
        this.status = status;
        this.begin_at = begin_at;
        this.end_at = end_at;
    }

    public int getId() {
        return id;
    }

    public int getPerformedPercentage() {
        return performed_percentage;
    }

    public String getServiceTitle() {
        return service_title;
    }

    public String getPlace() {
        return place;
    }

    public int getStatus() {
        return status;
    }

    public Date getBeginAt() {
        return begin_at;
    }

    public Date getEndAt() {
        return end_at;
    }
}
