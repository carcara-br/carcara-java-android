package br.com.stant.stant_android_fiscal.contractprogressevaluation.constractsprogressevaluation;

import java.util.List;

import br.com.stant.stant_android_fiscal.BaseAdapters;
import br.com.stant.stant_android_fiscal.BasePresenter;
import br.com.stant.stant_android_fiscal.BaseView;
import br.com.stant.stant_android_fiscal.domain.entity.contractprogressevaluation.ContractProgressEvaluation;
import br.com.stant.stant_android_fiscal.domain.enums.contractprogressevaluation.ContractProgressEvaluationStatusEnum;

/**
 * Created by denisvieira on 11/05/17.
 */

public class ContractsProgressEvaluationContract {

    interface View extends BaseView<ContractsProgressEvaluationContract.Presenter> {
        void showContractsProgressEvaluation(List<ContractProgressEvaluation> contractsProgressEvaluation);
        void showNoContractsProgressEvaluation();
        void goToContractProgressEvaluationDetail(ContractProgressEvaluation contractProgressEvaluation);
    }

    interface Presenter extends BasePresenter {
        void loadConstractsProgressEvaluation(int constructionSiteId, ContractProgressEvaluationStatusEnum contractProgressEvaluationStatusEnum);
    }

    interface Adapter extends BaseAdapters {}
}
