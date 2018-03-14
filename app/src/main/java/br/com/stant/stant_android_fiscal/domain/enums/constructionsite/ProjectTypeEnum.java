package br.com.stant.stant_android_fiscal.domain.enums.constructionsite;

/**
 * Created by rachidcalazans on 22/02/17.
 */

public enum ProjectTypeEnum {
    COMMERCIAL(0), RESIDENTIAL(1), PUBLIC(2);

    private final int mValue;

    ProjectTypeEnum(int value) {
        mValue = value;
    }

    public int getValue(){
        return mValue;
    }
}
