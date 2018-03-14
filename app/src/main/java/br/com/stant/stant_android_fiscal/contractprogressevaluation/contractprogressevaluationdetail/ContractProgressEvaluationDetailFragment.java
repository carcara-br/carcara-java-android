package br.com.stant.stant_android_fiscal.contractprogressevaluation.contractprogressevaluationdetail;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.stant.stant_android_fiscal.InjectionUseCase;
import br.com.stant.stant_android_fiscal.R;
import br.com.stant.stant_android_fiscal.StantFiscalApplication;
import br.com.stant.stant_android_fiscal.contractprogressevaluation.contractprogressevaluationdetail.domain.dto.ContractProgressEvaluationDto;
import br.com.stant.stant_android_fiscal.contractprogressevaluation.contractprogressevaluationdetail.domain.dto.ItemOfContractProgressEvaluationDto;
import br.com.stant.stant_android_fiscal.databinding.ContractProgressEvaluationDetailDisapproveDialogBinding;
import br.com.stant.stant_android_fiscal.databinding.ContractProgressEvaluationDetailFragBinding;
import br.com.stant.stant_android_fiscal.domain.entity.contractprogressevaluation.ContractProgressEvaluation;
import br.com.stant.stant_android_fiscal.domain.entity.contractprogressevaluation.ItemOfContractProgressEvaluation;
import br.com.stant.stant_android_fiscal.domain.entity.user.UserSession;
import br.com.stant.stant_android_fiscal.domain.enums.contractprogressevaluation.ContractProgressEvaluationStatusEnum;
import br.com.stant.stant_android_fiscal.mainmenu.MainMenu;
import br.com.stant.stant_android_fiscal.mainmenu.MainMenuPresenter;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by denisvieira on 17/05/17.
 */

public class ContractProgressEvaluationDetailFragment extends Fragment implements ContractProgressEvaluationDetailContract.View {


    private ContractProgressEvaluationDetailFragBinding selectConstructionSiteFragBinding;
    private ContractProgressEvaluationDetailDisapproveDialogBinding contractProgressEvaluationDetailDisapproveDialogBinding;
    private ContractProgressEvaluationDetailItemAdapter mContractProgressEvaluationDetailItemAdapter;
    private ContractProgressEvaluationDetailContract.Presenter mPresenter;
    private UserSession mCurrentUserSession;
    private MainMenu mMainMenu;
    private ContractProgressEvaluation mContractProgressEvaluation;

    public ContractProgressEvaluationDetailFragment(){}

    public static ContractProgressEvaluationDetailFragment newInstance(ContractProgressEvaluation contractProgressEvaluation) {
        ContractProgressEvaluationDetailFragment frag = new ContractProgressEvaluationDetailFragment();
        frag.mContractProgressEvaluation = contractProgressEvaluation;

        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCurrentUserSession = StantFiscalApplication.currentUserSession();
        mMainMenu = new MainMenu(getContext(), this);
        new MainMenuPresenter(mMainMenu, InjectionUseCase.provideDestroyUserSession(getContext()));

        mContractProgressEvaluationDetailItemAdapter = new ContractProgressEvaluationDetailItemAdapter(getContext(),
                new ArrayList<>(0),this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        selectConstructionSiteFragBinding = DataBindingUtil.inflate(inflater, R.layout.contract_progress_evaluation_detail_frag, container, false);
        selectConstructionSiteFragBinding.setHandler(this);
        selectConstructionSiteFragBinding.setUser(mCurrentUserSession);
        showContractProgressEvaluation(mContractProgressEvaluation);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        selectConstructionSiteFragBinding.contractProgressEvaluationDetailFragItemListRecycler.setLayoutManager(layout);
        selectConstructionSiteFragBinding.contractProgressEvaluationDetailFragItemListRecycler.setNestedScrollingEnabled(false);
        selectConstructionSiteFragBinding.contractProgressEvaluationDetailFragItemListRecycler.setAdapter(mContractProgressEvaluationDetailItemAdapter);

        mPresenter.loadItemsOfContractProgressEvaluation(mContractProgressEvaluation.getId());

        backButtonSetup();

        return selectConstructionSiteFragBinding.getRoot();
    }

    public void onResume() {
        mPresenter.loadItemsOfContractProgressEvaluation(mContractProgressEvaluation.getId());
        super.onResume();
    }

    @Override
    public void showRemoteRequestLoader() {

    }

    @Override
    public void hideRemoteRequestLoader() {

    }

    @Override
    public void setPresenter(ContractProgressEvaluationDetailContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void openMainMenu(View view) {
        mMainMenu.show();
    }

    @Override
    public void showContractProgressEvaluation(ContractProgressEvaluation contractProgressEvaluation) {
        selectConstructionSiteFragBinding.setContractProgressEvaluation(mapperContractProgressEvaluationDto(contractProgressEvaluation));
        setContractProgressEvaluationStatus(contractProgressEvaluation);
    }

    @Override
    public void showItemsOfContractProgressEvaluation(List<ItemOfContractProgressEvaluation> itemsOfContractProgressEvaluation) {
        mContractProgressEvaluationDetailItemAdapter.replaceData(mapperItemsOfContractProgressEvaluationDto(itemsOfContractProgressEvaluation));
    }

    @Override
    public void showNoItemsOfContractProgressEvaluation() {

    }

    @Override
    public void approveItemOfContractProgressEvaluation(int itemId) {
        mPresenter.approveItemOfContractProgressEvaluation(mContractProgressEvaluation.getId(),itemId);
    }

    @Override
    public void disapproveItemOfContractProgressEvaluation(int itemId) {

        Dialog disapproveItemDialog = new Dialog(getContext(), R.style.DialogTheme);
        contractProgressEvaluationDetailDisapproveDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.contract_progress_evaluation_detail_disapprove_dialog, null, false);
        contractProgressEvaluationDetailDisapproveDialogBinding.setHandler(this);
        disapproveItemDialog.setContentView(contractProgressEvaluationDetailDisapproveDialogBinding.getRoot());

        Window window = disapproveItemDialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        window.setAttributes(wlp);


        disapproveItemDialog.show();

        String observation = contractProgressEvaluationDetailDisapproveDialogBinding.contractProgressEvaluationDetailDialogObservationEditText.getText().toString();

        contractProgressEvaluationDetailDisapproveDialogBinding.contractProgressEvaluationDetailDialogOkButton.setOnClickListener(
                v -> {
                    disapproveItemDialog.dismiss();
                    mPresenter.disapproveItemOfContractProgressEvaluation(mContractProgressEvaluation.getId(), itemId, observation);
                });

        contractProgressEvaluationDetailDisapproveDialogBinding.contractProgressEvaluationDetailDialogCancelButton.setOnClickListener(
                v -> disapproveItemDialog.dismiss());

        contractProgressEvaluationDetailDisapproveDialogBinding.contractProgressEvaluationDetailDialogJustifyButton.setOnClickListener(
                v -> {
                    contractProgressEvaluationDetailDisapproveDialogBinding.contractProgressEvaluationDetailTextJustifyInfoTextView.setVisibility(View.GONE);
                    contractProgressEvaluationDetailDisapproveDialogBinding.contractProgressEvaluationDetailDialogObservationEditText.setVisibility(View.VISIBLE);
                    contractProgressEvaluationDetailDisapproveDialogBinding.contractProgressEvaluationDetailDialogJustifyButton.setVisibility(View.GONE);
                });

    }

    @Override
    public void showJustifyDisapproveObservationDialog(int itemId) {


    }

    @Override
    public void onApproveItemOfContractProgressEvaluation() {
        mPresenter.loadItemsOfContractProgressEvaluation(mContractProgressEvaluation.getId());
    }

    @Override
    public void onDisapproveItemOfContractProgressEvaluation() {
        mPresenter.loadItemsOfContractProgressEvaluation(mContractProgressEvaluation.getId());
    }

    @Override
    public void onFailedSetStatusOfItemOfContractProgressEvaluation() {
        Toast.makeText(getContext(), getResources().getString(R.string.contract_progress_evaluation_detail_frag_failed_set_state), Toast.LENGTH_SHORT).show();
    }

    private void backButtonSetup(){
        if (selectConstructionSiteFragBinding.contractProgressEvaluationDetailFragMainToolbar != null){
            ((AppCompatActivity) getActivity()).setSupportActionBar(selectConstructionSiteFragBinding.contractProgressEvaluationDetailFragMainToolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
            selectConstructionSiteFragBinding.contractProgressEvaluationDetailFragMainToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_left_gray));
            selectConstructionSiteFragBinding.contractProgressEvaluationDetailFragMainToolbar.setNavigationOnClickListener(V ->{
                getActivity().onBackPressed();
            });
        }
    }

    private void setContractProgressEvaluationStatus(ContractProgressEvaluation contractProgressEvaluation){
        HashMap<Integer,String> status = new HashMap<>();
        status.put(ContractProgressEvaluationStatusEnum.APPROVED.getValue(),getResources().getString(R.string.contract_progress_evaluation_detail_frag_approved));
        status.put(ContractProgressEvaluationStatusEnum.FINISH.getValue(),getResources().getString(R.string.contract_progress_evaluation_detail_frag_finish));
        status.put(ContractProgressEvaluationStatusEnum.PROGRESS.getValue(),getResources().getString(R.string.contracts_progress_evaluation_data_tabs_in_progress));

        String contractProgressEvaluationStatus = status.get(contractProgressEvaluation.getStatus());
        selectConstructionSiteFragBinding.setStatus(contractProgressEvaluationStatus);
    }


    private ContractProgressEvaluationDto mapperContractProgressEvaluationDto(ContractProgressEvaluation contractProgressEvaluation) {
        return new ContractProgressEvaluationDto(contractProgressEvaluation.getId(),
                contractProgressEvaluation.getTitle(),
                contractProgressEvaluation.getStatus(),
                contractProgressEvaluation.getItemsNotInspected(),
                contractProgressEvaluation.getItemsApproved(),
                contractProgressEvaluation.getItemsDisapproved(),
                contractProgressEvaluation.getCreatedAt());
    }

    private List<ItemOfContractProgressEvaluationDto> mapperItemsOfContractProgressEvaluationDto(List<ItemOfContractProgressEvaluation> itemsOfContractProgressEvaluation) {
        List<ItemOfContractProgressEvaluationDto> itemsOfContractProgressEvaluationDto = new ArrayList<>();

        for (ItemOfContractProgressEvaluation itemOfContractProgressEvaluation : itemsOfContractProgressEvaluation){
            itemsOfContractProgressEvaluationDto.add(mapperItemOfContractProgressEvaluationDto(itemOfContractProgressEvaluation));
        }

        return itemsOfContractProgressEvaluationDto;
    }

    private ItemOfContractProgressEvaluationDto mapperItemOfContractProgressEvaluationDto(ItemOfContractProgressEvaluation itemOfContractProgressEvaluation) {
        return new ItemOfContractProgressEvaluationDto(itemOfContractProgressEvaluation.getId(),
                itemOfContractProgressEvaluation.getPerformedPercentage(),
                itemOfContractProgressEvaluation.getServiceTitle(),
                itemOfContractProgressEvaluation.getPlace(),
                itemOfContractProgressEvaluation.getStatus(),
                itemOfContractProgressEvaluation.getBeginAt(),
                itemOfContractProgressEvaluation.getEndAt());
    }
}
