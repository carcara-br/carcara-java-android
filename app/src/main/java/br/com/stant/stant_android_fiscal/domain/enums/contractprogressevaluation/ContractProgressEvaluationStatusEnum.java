package br.com.stant.stant_android_fiscal.domain.enums.contractprogressevaluation;

/**
 * Created by denisvieira on 16/05/17.
 */

public enum ContractProgressEvaluationStatusEnum {
    PROGRESS(0), FINISH(1), APPROVED(2);

    private final int mValue;

    ContractProgressEvaluationStatusEnum(int value) {
        mValue = value;
    }

    public int getValue(){
        return mValue;
    }

    public static ContractProgressEvaluationStatusEnum getEnum(int value) {
        switch (value){
            case 0:
                return ContractProgressEvaluationStatusEnum.PROGRESS;
            case 1:
                return ContractProgressEvaluationStatusEnum.FINISH;
            case 2:
                return ContractProgressEvaluationStatusEnum.APPROVED;
        }
        return null;
    }

}
