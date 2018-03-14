package br.com.stant.stant_android_fiscal.contractprogressevaluation.contractprogressevaluationdetail;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;

import java.util.HashMap;
import java.util.List;

import br.com.stant.stant_android_fiscal.R;
import br.com.stant.stant_android_fiscal.contractprogressevaluation.contractprogressevaluationdetail.domain.dto.ItemOfContractProgressEvaluationDto;
import br.com.stant.stant_android_fiscal.databinding.ContractProgressEvaluationDetailItemBinding;
import br.com.stant.stant_android_fiscal.domain.enums.contractprogressevaluation.ItemOfContractProgressEvaluationStatusEnum;

import static br.com.stant.stant_android_fiscal.util.MetricUtil.dpToPx;

/**
 * Created by denisvieira on 17/05/17.
 */

public class ContractProgressEvaluationDetailItemAdapter extends RecyclerView.Adapter<ContractProgressEvaluationDetailItemAdapter.ItemViewHolder> implements ContractProgressEvaluationDetailContract.Adapter{

    private final ContractProgressEvaluationDetailContract.View mContractsProgressEvaluationView;
    private List<ItemOfContractProgressEvaluationDto> mItemsOfContractProgressEvaluation;
    private Context mContext;
    private ItemViewHolder mViewHolder;
    private Toast toast;

    public ContractProgressEvaluationDetailItemAdapter(Context context,
                                                  List<ItemOfContractProgressEvaluationDto> itemsOfContractProgressEvaluation,
                                                  ContractProgressEvaluationDetailContract.View contractsProgressEvaluationDetailView) {
        this.mContext                         = context;
        this.mItemsOfContractProgressEvaluation = itemsOfContractProgressEvaluation;
        this.mContractsProgressEvaluationView = contractsProgressEvaluationDetailView;
    }

    @Override
    public void replaceData(List list) {
        mItemsOfContractProgressEvaluation = list;
        notifyDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ContractProgressEvaluationDetailItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.contract_progress_evaluation_detail_item,
                parent,
                false);

        final ItemViewHolder vh = new ItemViewHolder(binding);
        return vh;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        mViewHolder = holder;

        ItemOfContractProgressEvaluationDto itemOfContractProgressEvaluationDto = mItemsOfContractProgressEvaluation.get(position);
        mViewHolder.itemBinding.setItemOfContractProgressEvaluation(itemOfContractProgressEvaluationDto);
        setItemOfContractProgressEvaluationStatus(mViewHolder.itemBinding.contractProgressEvaluationItemStatusTextView,itemOfContractProgressEvaluationDto.getStatus());

        if (itemOfContractProgressEvaluationDto.getStatus() == ItemOfContractProgressEvaluationStatusEnum.APPROVED.getValue())
            configLittleSwipe(mViewHolder.itemBinding.siffDetailItemSwipeLayout);
        else
            configFullSwipe(mViewHolder.itemBinding.siffDetailItemSwipeLayout, itemOfContractProgressEvaluationDto);
    }

    @Override
    public int getItemCount() {
        return mItemsOfContractProgressEvaluation.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder{

        private ContractProgressEvaluationDetailItemBinding itemBinding;

        public ItemViewHolder(ContractProgressEvaluationDetailItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }

    private void setItemOfContractProgressEvaluationStatus(TextView textView, int itemOfContractProgressEvaluationStatus){
        HashMap<Integer, String> status = new HashMap<>();
        status.put(ItemOfContractProgressEvaluationStatusEnum.APPROVED.getValue(),mContext.getResources().getString(R.string.contract_progress_evaluation_detail_frag_approved));
        status.put(ItemOfContractProgressEvaluationStatusEnum.DISAPPROVED.getValue(),mContext.getResources().getString(R.string.contract_progress_evaluation_detail_frag_disapproved));
        status.put(ItemOfContractProgressEvaluationStatusEnum.PROGRESS.getValue(),mContext.getResources().getString(R.string.contracts_progress_evaluation_data_tabs_in_progress));

        HashMap<Integer, Integer> statusColor = new HashMap<>();
        statusColor.put(ItemOfContractProgressEvaluationStatusEnum.APPROVED.getValue(), ContextCompat.getColor(mContext, R.color.green_approved));
        statusColor.put(ItemOfContractProgressEvaluationStatusEnum.DISAPPROVED.getValue(),ContextCompat.getColor(mContext, R.color.red));
        statusColor.put(ItemOfContractProgressEvaluationStatusEnum.PROGRESS.getValue(),ContextCompat.getColor(mContext, R.color.gray));

        textView.setText(status.get(itemOfContractProgressEvaluationStatus));
        textView.setTextColor(statusColor.get(itemOfContractProgressEvaluationStatus));
    }

    private void configFullSwipe(SwipeLayout swipeLayout, ItemOfContractProgressEvaluationDto itemOfContractProgressEvaluationDto) {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mViewHolder.itemBinding.contractProgressEvaluationItemDisapproveSwipeLinearLayout.getLayoutParams();
        params.width                    = ViewGroup.LayoutParams.MATCH_PARENT;
        mViewHolder.itemBinding.contractProgressEvaluationItemDisapproveSwipeLinearLayout.setLayoutParams(params);
        mViewHolder.itemBinding.contractProgressEvaluationItemApproveSwipeLinearLayout.setLayoutParams(params);

        swipeLayout.setEnabled(false);
        swipeLayout.addDrag(SwipeLayout.DragEdge.Left, swipeLayout.findViewById(R.id.contract_progress_evaluation_item_approve_swipe_linear_layout));
        swipeLayout.addDrag(SwipeLayout.DragEdge.Right, swipeLayout.findViewById(R.id.contract_progress_evaluation_item_disapprove_swipe_linear_layout));

        swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener(){

            @Override
            public void onOpen(SwipeLayout layout) {

                if (layout.getDragEdge() == SwipeLayout.DragEdge.valueOf("Right")){
                    mContractsProgressEvaluationView.disapproveItemOfContractProgressEvaluation(itemOfContractProgressEvaluationDto.getId());
                    swipeLayout.close(true);
                }

                if(layout.getDragEdge() == SwipeLayout.DragEdge.valueOf("Left")){
                    mContractsProgressEvaluationView.approveItemOfContractProgressEvaluation(itemOfContractProgressEvaluationDto.getId());
                    swipeLayout.close(true);
                }

            }
            @Override public void onClose(SwipeLayout layout) {} @Override public void onStartOpen(SwipeLayout layout) {} @Override public void onStartClose(SwipeLayout layout) {} @Override public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {} @Override public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {}
        });
    }

    private void configLittleSwipe(SwipeLayout swipeLayout){
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mViewHolder.itemBinding.contractProgressEvaluationItemDisapproveSwipeLinearLayout.getLayoutParams();
        params.width                    = dpToPx(120, mContext);
        mViewHolder.itemBinding.contractProgressEvaluationItemDisapproveSwipeLinearLayout.setLayoutParams(params);
        mViewHolder.itemBinding.contractProgressEvaluationItemApproveSwipeLinearLayout.setLayoutParams(params);

        swipeLayout.addDrag(SwipeLayout.DragEdge.Left, mViewHolder.itemBinding.siffDetailItemSwipeLayout.findViewById(R.id.contract_progress_evaluation_item_approve_swipe_linear_layout));
        swipeLayout.addDrag(SwipeLayout.DragEdge.Right, mViewHolder.itemBinding.siffDetailItemSwipeLayout.findViewById(R.id.contract_progress_evaluation_item_disapprove_swipe_linear_layout));

        swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {

            @Override
            public void onOpen(SwipeLayout layout) {

                if (layout.getDragEdge() == SwipeLayout.DragEdge.valueOf("Right")){
                    impossibleToSetToastConfiguration();
                    swipeLayout.close(true);
                }

                if(layout.getDragEdge() == SwipeLayout.DragEdge.valueOf("Left")){
                    impossibleToSetToastConfiguration();
                    swipeLayout.close(true);
                }
            }
            @Override public void onClose(SwipeLayout layout) {} @Override public void onStartOpen(SwipeLayout layout) {} @Override public void onStartClose(SwipeLayout layout) {} @Override public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {} @Override public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {}
        });

    }

    public void impossibleToSetToastConfiguration() {
        cancelToast(toast);
        toast = new Toast(mContext);
        toast.setDuration(Toast.LENGTH_SHORT);

        View layout = getToastCustomLayout(mContext.getString(R.string.contract_progress_evaluation_detail_frag_do_not_set_state_message));

        toast.setView(layout);
        toast.show();
    }

    public void cancelToast(Toast toast){
        if (toast != null){
            toast.cancel();
        }
    }

    @NonNull
    private View getToastCustomLayout(String message) {
        View v                  = new View(mContext);
        LayoutInflater li       = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup toastCustom   = (ViewGroup) v.findViewById(R.id.toast_layout_root);
        View layout             = li.inflate(R.layout.toast_custom_green, toastCustom);
        TextView text           = (TextView) layout.findViewById(R.id.toast_green_text);
        text.setText(message);
        return layout;
    }

}
