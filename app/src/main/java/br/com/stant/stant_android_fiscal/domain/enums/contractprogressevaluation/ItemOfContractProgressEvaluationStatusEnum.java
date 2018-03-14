package br.com.stant.stant_android_fiscal.domain.enums.contractprogressevaluation;

/**
 * Created by denisvieira on 16/05/17.
 */

public enum ItemOfContractProgressEvaluationStatusEnum {
    DISAPPROVED(0), APPROVED(1), PROGRESS(2);

    private final int mValue;

    ItemOfContractProgressEvaluationStatusEnum(int value) {
        mValue = value;
    }

    public int getValue(){
        return mValue;
    }

    public static ItemOfContractProgressEvaluationStatusEnum getEnum(int value) {
        switch (value){
            case 0:
                return ItemOfContractProgressEvaluationStatusEnum.DISAPPROVED;
            case 1:
                return ItemOfContractProgressEvaluationStatusEnum.APPROVED;
            case 2:
                return ItemOfContractProgressEvaluationStatusEnum.DISAPPROVED;
        }
        return null;
    }

}
