package br.com.stant.stant_android_fiscal.contractprogressevaluation.constractsprogressevaluation;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.stant.stant_android_fiscal.R;
import br.com.stant.stant_android_fiscal.contractprogressevaluation.constractsprogressevaluation.domain.dto.ContractProgressEvaluationDto;
import br.com.stant.stant_android_fiscal.contractprogressevaluation.contractprogressevaluationdetail.ContractProgressEvaluationDetailActivity;
import br.com.stant.stant_android_fiscal.databinding.ContractsProgressEvaluationFragBinding;
import br.com.stant.stant_android_fiscal.domain.entity.contractprogressevaluation.ContractProgressEvaluation;
import br.com.stant.stant_android_fiscal.domain.enums.contractprogressevaluation.ContractProgressEvaluationStatusEnum;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by denisvieira on 11/05/17.
 */

public class ContractsProgressEvaluationFragment extends Fragment implements ContractsProgressEvaluationContract.View{

    private ContractsProgressEvaluationContract.Presenter mPresenter;
    private ContractsProgressEvaluationFragBinding contractsProgressEvaluationFragBinding;
    private int mConstructionSiteId;
    private ContractProgressEvaluationStatusEnum mTabStatusPositionEnum;
    private ContractsProgressEvaluationItemAdapter contractsProgressEvaluationItemAdapter;

    public static ContractsProgressEvaluationFragment newInstance(int constructionSiteId,
                                                                  ContractProgressEvaluationStatusEnum tabStatusPosition){
        ContractsProgressEvaluationFragment fragment = new ContractsProgressEvaluationFragment();
        fragment.mConstructionSiteId    = constructionSiteId;
        fragment.mTabStatusPositionEnum = tabStatusPosition;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        contractsProgressEvaluationItemAdapter = new ContractsProgressEvaluationItemAdapter(getContext(),
                new ArrayList<>(0),this);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        contractsProgressEvaluationFragBinding = DataBindingUtil.inflate(inflater, R.layout.contracts_progress_evaluation_frag,container,false);

        contractsProgressEvaluationFragBinding.contractsProgressEvaluationFragListRecycler.setNestedScrollingEnabled(false);
        contractsProgressEvaluationFragBinding.contractsProgressEvaluationFragListRecycler.setFocusable(false);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        contractsProgressEvaluationFragBinding.contractsProgressEvaluationFragListRecycler.setLayoutManager(layout);
        contractsProgressEvaluationFragBinding.contractsProgressEvaluationFragListRecycler.setAdapter(contractsProgressEvaluationItemAdapter);

        mPresenter.loadConstractsProgressEvaluation(mConstructionSiteId, mTabStatusPositionEnum);
        return contractsProgressEvaluationFragBinding.getRoot();
    }

    public void onResume() {
        mPresenter.loadConstractsProgressEvaluation(mConstructionSiteId, mTabStatusPositionEnum);
        super.onResume();
    }

    @Override
    public void showRemoteRequestLoader() {

    }

    @Override
    public void hideRemoteRequestLoader() {

    }

    @Override
    public void setPresenter(ContractsProgressEvaluationContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showContractsProgressEvaluation(List<ContractProgressEvaluation> contractsProgressEvaluation) {
        contractsProgressEvaluationItemAdapter.replaceData(mapperContractsProgressEvaluationDto(contractsProgressEvaluation));
    }

    @Override
    public void showNoContractsProgressEvaluation() {

    }

    @Override
    public void goToContractProgressEvaluationDetail(ContractProgressEvaluation contractProgressEvaluation) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ContractProgressEvaluationDetailActivity.KEY_CHOSEN_CONTRACT_PROGRESS_EVALUATION, contractProgressEvaluation);

        Intent intent = new Intent(getContext(), ContractProgressEvaluationDetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private List<ContractProgressEvaluationDto> mapperContractsProgressEvaluationDto(List<ContractProgressEvaluation> contractsProgressEvaluation) {
        List<ContractProgressEvaluationDto> contractsProgressEvaluationDto = new ArrayList<>();
        for (ContractProgressEvaluation contractProgressEvaluation : contractsProgressEvaluation) {
            contractsProgressEvaluationDto.add(new ContractProgressEvaluationDto(contractProgressEvaluation.getId(),
                    contractProgressEvaluation.getTitle(),
                    contractProgressEvaluation.getStatus(),
                    contractProgressEvaluation.getItemsNotInspected(),
                    contractProgressEvaluation.getItemsApproved(),
                    contractProgressEvaluation.getItemsDisapproved(),
                    contractProgressEvaluation.getCreatedAt()));
        }
        return contractsProgressEvaluationDto;
    }
}
