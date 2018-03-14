package br.com.stant.stant_android_fiscal.selectconstructionsite.domain.dto;

import java.io.Serializable;

/**
 * Created by denisvieira on 15/05/17.
 */

public final class ConstructionSiteDto implements Serializable {

    private final int mId;
    private final String mTitle;
    private final String mProjectImage;
    private final String mAddress;
    private final float mContractsProgressEvaluationPerformed;

    public ConstructionSiteDto(int id, String title, String projectImage, String address,
                               float contractsProgressEvaluationPerformed){
        this.mId                                       = id;
        this.mTitle                                    = title;
        this.mProjectImage                             = projectImage;
        this.mAddress                                  = address;
        this.mContractsProgressEvaluationPerformed     = contractsProgressEvaluationPerformed;
    }

    public int getId() {
        return mId;
    }

    public String getTitle(){
        return mTitle;
    }

    public String getProjectImage() {
        return mProjectImage;
    }

    public String getAddress(){
        return mAddress;
    }

    public String getContractsProgressEvaluationPerformedString() {
        return (int) mContractsProgressEvaluationPerformed + "%";
    }

    public float getContractsProgressEvaluationPerformed() {
        return mContractsProgressEvaluationPerformed;
    }

    public boolean hasProjectImage() {
        return getProjectImage() != null && !getProjectImage().equals("");
    }
}
