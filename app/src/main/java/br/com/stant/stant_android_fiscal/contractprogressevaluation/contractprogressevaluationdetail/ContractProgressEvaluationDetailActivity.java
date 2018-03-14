package br.com.stant.stant_android_fiscal.contractprogressevaluation.contractprogressevaluationdetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.stant.stant_android_fiscal.InjectionUseCase;
import br.com.stant.stant_android_fiscal.R;
import br.com.stant.stant_android_fiscal.domain.entity.contractprogressevaluation.ContractProgressEvaluation;
import br.com.stant.stant_android_fiscal.util.ActivityUtils;

/**
 * Created by denisvieira on 17/05/17.
 */

public class ContractProgressEvaluationDetailActivity extends AppCompatActivity  {

    public static final String KEY_CHOSEN_CONTRACT_PROGRESS_EVALUATION = "KEY_CHOSEN_CONTRACT_PROGRESS_EVALUATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contract_progress_evaluation_detail_act);

        ContractProgressEvaluationDetailFragment contractProgressEvaluationDetailFragment = (ContractProgressEvaluationDetailFragment) getSupportFragmentManager().findFragmentById(R.id.contract_progress_evaluation_detail_content_frame);

        if (contractProgressEvaluationDetailFragment == null){
            Bundle extras = getIntent().getExtras();
            ContractProgressEvaluation contractProgressEvaluation = (ContractProgressEvaluation) extras.getSerializable(KEY_CHOSEN_CONTRACT_PROGRESS_EVALUATION);

            contractProgressEvaluationDetailFragment = ContractProgressEvaluationDetailFragment.newInstance(contractProgressEvaluation);

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), contractProgressEvaluationDetailFragment, R.id.contract_progress_evaluation_detail_content_frame);
        }

        new ContractProgressEvaluationDetailPresenter(
                contractProgressEvaluationDetailFragment,
                InjectionUseCase.provideGetItemsOfContractProgressEvaluation(getApplicationContext()),
                InjectionUseCase.provideApproveItemOfContractProgressEvaluation(getApplicationContext()),
                InjectionUseCase.provideDisapproveItemOfContractProgressEvaluation(getApplicationContext()));
    }

}
