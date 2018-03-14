package br.com.stant.stant_android_fiscal.contractprogressevaluation.contractsprogressevaluationdatatabs;

import br.com.stant.stant_android_fiscal.BasePresenter;
import br.com.stant.stant_android_fiscal.BaseView;

/**
 * Created by denisvieira on 11/05/17.
 */

public interface ContractsProgressEvaluationDataTabsContract {

    interface View extends BaseView<Presenter> {
        void openMainMenu(android.view.View view);
    }

    interface Presenter extends BasePresenter {
    }
}
