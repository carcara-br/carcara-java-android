package br.com.stant.stant_android_fiscal.contractprogressevaluation.constractsprogressevaluation;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import br.com.stant.stant_android_fiscal.R;
import br.com.stant.stant_android_fiscal.contractprogressevaluation.constractsprogressevaluation.domain.dto.ContractProgressEvaluationDto;
import br.com.stant.stant_android_fiscal.databinding.ContractsProgressEvaluationItemBinding;
import br.com.stant.stant_android_fiscal.domain.converters.ContractProgressEvaluationConverter;
import br.com.stant.stant_android_fiscal.domain.entity.contractprogressevaluation.ContractProgressEvaluation;

/**
 * Created by denisvieira on 11/05/17.
 */

public class ContractsProgressEvaluationItemAdapter extends RecyclerView.Adapter<ContractsProgressEvaluationItemAdapter.ItemViewHolder> implements ContractsProgressEvaluationContract.Adapter{

    private final ContractsProgressEvaluationContract.View mContractsProgressEvaluationView;
    private List<ContractProgressEvaluationDto> mContractsProgressEvaluation;
    private Context mContext;
    private ItemViewHolder mViewHolder;

    public ContractsProgressEvaluationItemAdapter(Context context,
                                         List<ContractProgressEvaluationDto> contractsProgressEvaluationDto,
                                         ContractsProgressEvaluationContract.View contractsProgressEvaluationView) {
        this.mContext                         = context;
        this.mContractsProgressEvaluation     = contractsProgressEvaluationDto;
        this.mContractsProgressEvaluationView = contractsProgressEvaluationView;
    }

    @Override
    public void replaceData(List list) {
        mContractsProgressEvaluation = list;
        notifyDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ContractsProgressEvaluationItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.contracts_progress_evaluation_item,
                parent,
                false);

        final ItemViewHolder vh = new ItemViewHolder(binding);
        return vh;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        mViewHolder = holder;

        ContractProgressEvaluationDto contractProgressEvaluation = mContractsProgressEvaluation.get(position);
        mViewHolder.itemBinding.setContractsProgressEvaluation(contractProgressEvaluation);
        mViewHolder.itemBinding.contractsProgressEvaluationFragItemGeneralContainerLinearLayout.setOnClickListener(
                v -> mContractsProgressEvaluationView.goToContractProgressEvaluationDetail(mapperContractProgressEvaluationDto(contractProgressEvaluation)));

        setWeightInLinear(mViewHolder.itemBinding.contractsProgressEvaluationApprovedBarLinearLayout,
                contractProgressEvaluation.getApprovedPercentOfContractProgressEvaluation());

        setWeightInLinear(mViewHolder.itemBinding.contractsProgressEvaluationInProgressBarLinearLayout,
                contractProgressEvaluation.getNotInspectedPercentOfContractProgressEvaluation());

        setWeightInLinear(mViewHolder.itemBinding.contractsProgressEvaluationDisapprovedBarLinearLayout,
                contractProgressEvaluation.getDisapprovedPercentOfContractProgressEvaluation());

        mViewHolder.itemBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mContractsProgressEvaluation.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        private ContractsProgressEvaluationItemBinding itemBinding;

        public ItemViewHolder(ContractsProgressEvaluationItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }

    private void setWeightInLinear(LinearLayout bar, Integer quantityValue){
        if(quantityValue == 0){
            bar.setVisibility(View.GONE);
        }else{
            bar.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            params.weight = quantityValue;
            bar.setLayoutParams(params);
        }

    }

    private ContractProgressEvaluation mapperContractProgressEvaluationDto(ContractProgressEvaluationDto contractProgressEvaluation) {
        return new ContractProgressEvaluation(contractProgressEvaluation.getId(),
                contractProgressEvaluation.getTitle(),
                contractProgressEvaluation.getStatus(),
                contractProgressEvaluation.getItemsNotInspected(),
                contractProgressEvaluation.getItemsApproved(),
                contractProgressEvaluation.getItemsDisapproved(),
                contractProgressEvaluation.getCreatedAt());
    }
}
