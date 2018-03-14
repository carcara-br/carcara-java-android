package br.com.stant.stant_android_fiscal.selectconstructionsite;

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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.stant.stant_android_fiscal.InjectionUseCase;
import br.com.stant.stant_android_fiscal.R;
import br.com.stant.stant_android_fiscal.StantFiscalApplication;
import br.com.stant.stant_android_fiscal.contractprogressevaluation.contractsprogressevaluationdatatabs.ContractsProgressEvaluationDataTabsActivity;
import br.com.stant.stant_android_fiscal.databinding.SelectConstructionSiteFragBinding;
import br.com.stant.stant_android_fiscal.domain.entity.constructionsite.ConstructionSite;
import br.com.stant.stant_android_fiscal.domain.entity.user.UserSession;
import br.com.stant.stant_android_fiscal.domain.enums.auth.AuthRemoteErrorCodeEnum;
import br.com.stant.stant_android_fiscal.mainmenu.MainMenu;
import br.com.stant.stant_android_fiscal.mainmenu.MainMenuPresenter;
import br.com.stant.stant_android_fiscal.selectconstructionsite.domain.dto.ConstructionSiteDto;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by denisvieira on 10/05/17.
 */

public class SelectConstructionSiteFragment extends Fragment implements SelectConstructionSiteContract.View{

    private SelectConstructionSiteContract.Presenter mPresenter;
    private SelectConstructionSiteFragBinding selectConstructionSiteFragBinding;
    private SelectConstructionSiteAdapter mConstructionSiteAdapter;
    private UserSession mCurrentUserSession;
    private MainMenu mMainMenu;

    public SelectConstructionSiteFragment(){}

    public static SelectConstructionSiteFragment newInstance() {
        return new SelectConstructionSiteFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCurrentUserSession = StantFiscalApplication.currentUserSession();
        mMainMenu = new MainMenu(getContext(), this);
        new MainMenuPresenter(mMainMenu, InjectionUseCase.provideDestroyUserSession(getContext()));

        mConstructionSiteAdapter = new SelectConstructionSiteAdapter(getContext(),
                new ArrayList<>(0),this);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        selectConstructionSiteFragBinding = DataBindingUtil.inflate(inflater, R.layout.select_construction_site_frag, container, false);
        selectConstructionSiteFragBinding.setHandler(this);
        selectConstructionSiteFragBinding.setUser(mCurrentUserSession);

        RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        selectConstructionSiteFragBinding.selectConstructionSiteListRecycler.setLayoutManager(layout);
        selectConstructionSiteFragBinding.selectConstructionSiteListRecycler.setNestedScrollingEnabled(false);
        selectConstructionSiteFragBinding.selectConstructionSiteListRecycler.setAdapter(mConstructionSiteAdapter);

        mPresenter.loadConstructionSites();

        return selectConstructionSiteFragBinding.getRoot();
    }

    public void onResume() {
        mPresenter.loadConstructionSites();
        super.onResume();
    }


    @Override
    public void showRemoteRequestLoader() {

    }

    @Override
    public void hideRemoteRequestLoader() {

    }

    @Override
    public void setPresenter(SelectConstructionSiteContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void openMainMenu(View view) {
        mMainMenu.show();
    }

    @Override
    public void showConstructionSites(List<ConstructionSite> constructionSites) {
        mConstructionSiteAdapter.replaceData(mapperConstructionSitesDto(constructionSites));

        selectConstructionSiteFragBinding.selectConstructionSiteNoConstructionSitesTextView.setVisibility(View.GONE);
        selectConstructionSiteFragBinding.selectConstructionSiteListRecycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNoConstructionSites() {
        selectConstructionSiteFragBinding.selectConstructionSiteListRecycler.setVisibility(View.GONE);
        selectConstructionSiteFragBinding.selectConstructionSiteNoConstructionSitesTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void goToContractProgressEvaluation(int constructionSiteId, String constructionSiteTitle) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ContractsProgressEvaluationDataTabsActivity.KEY_CHOSEN_CONSTRUCTION_SITE_ID, constructionSiteId);
        bundle.putSerializable(ContractsProgressEvaluationDataTabsActivity.KEY_CHOSEN_CONSTRUCTION_SITE_TITLE, constructionSiteTitle);

        Intent intent = new Intent(getContext(), ContractsProgressEvaluationDataTabsActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    @Override
    public void onFailedLoadConstructionSites(int errorCode) {
        HashMap<Integer, String> failedLoadMap = new HashMap<>();
        failedLoadMap.put(AuthRemoteErrorCodeEnum.UNAUTHORIZED.getValue(),getContext().getResources().getString(R.string.login_frag_saripaar_errors_invalid_grant));
        failedLoadMap.put(AuthRemoteErrorCodeEnum.INVALID.getValue(),getContext().getResources().getString(R.string.on_load_failed));
        failedLoadMap.put(AuthRemoteErrorCodeEnum.SERVER_UNAVAILABLE.getValue(),getContext().getResources().getString(R.string.on_load_failed));

        Toast.makeText(getContext(), failedLoadMap.get(errorCode), Toast.LENGTH_SHORT).show();
    }

    private List<ConstructionSiteDto> mapperConstructionSitesDto(List<ConstructionSite> constructionSites) {
        List<ConstructionSiteDto> constructionSitesDto = new ArrayList<ConstructionSiteDto>();
        for (ConstructionSite constructionSite : constructionSites) {
            constructionSitesDto.add(new ConstructionSiteDto(constructionSite.getId(), constructionSite.getTitle(), constructionSite.getProjectImage(), constructionSite.getAddress(), 15));
        }
        return constructionSitesDto;
    }

}
