package br.com.stant.stant_android_fiscal.contractprogressevaluation.contractsprogressevaluationdatatabs;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import br.com.stant.stant_android_fiscal.InjectionUseCase;
import br.com.stant.stant_android_fiscal.R;
import br.com.stant.stant_android_fiscal.StantFiscalApplication;
import br.com.stant.stant_android_fiscal.databinding.ContractsProgressEvaluationActBinding;
import br.com.stant.stant_android_fiscal.domain.entity.user.UserSession;
import br.com.stant.stant_android_fiscal.mainmenu.MainMenu;
import br.com.stant.stant_android_fiscal.mainmenu.MainMenuPresenter;

/**
 * Created by denisvieira on 11/05/17.
 */

public class ContractsProgressEvaluationDataTabsActivity extends AppCompatActivity implements ContractsProgressEvaluationDataTabsContract.View{

    private ContractsProgressEvaluationActBinding mContractsProgressEvaluationActBinding;
    public static final String KEY_CHOSEN_CONSTRUCTION_SITE_ID = "KEY_CHOSE_CONSTRUCTION_SITE_GUID";
    public static final String KEY_CHOSEN_CONSTRUCTION_SITE_TITLE = "KEY_CONSTRUCTION_SITE_TITLE";

    private UserSession mCurrentUserSession;
    private MainMenu mMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContractsProgressEvaluationActBinding = DataBindingUtil.setContentView(
                this, R.layout.contracts_progress_evaluation_act);

        mMainMenu = new MainMenu(this, this);
        new MainMenuPresenter(mMainMenu, InjectionUseCase.provideDestroyUserSession(this));

        String constructionSiteTitle = getIntent().getStringExtra(KEY_CHOSEN_CONSTRUCTION_SITE_TITLE);
        mCurrentUserSession = StantFiscalApplication.currentUserSession();

        mContractsProgressEvaluationActBinding.setUser(mCurrentUserSession);
        mContractsProgressEvaluationActBinding.setConstructionSiteTitle(constructionSiteTitle);
        mContractsProgressEvaluationActBinding.setHandler(this);

        tabLayoutSetup();
        backButtonSetup();
    }

    @Override
    public void showRemoteRequestLoader() {

    }

    @Override
    public void hideRemoteRequestLoader() {

    }

    @Override
    public void openMainMenu(View view) {
        mMainMenu.show();
    }

    @Override
    public void setPresenter(ContractsProgressEvaluationDataTabsContract.Presenter presenter) {

    }

    private void backButtonSetup(){
        setSupportActionBar(mContractsProgressEvaluationActBinding.contractProgressEvaluationDetailFragMainToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mContractsProgressEvaluationActBinding.contractProgressEvaluationDetailFragMainToolbar.setNavigationOnClickListener(v -> finish());

    }

    private void tabLayoutSetup(){

        Bundle constructionSiteIdBundle = getIntent().getExtras();
        int constructionSiteId = constructionSiteIdBundle.getInt(KEY_CHOSEN_CONSTRUCTION_SITE_ID);

        mContractsProgressEvaluationActBinding.contractsProgressEvaluationDataTabsActMainViewPager.setAdapter(new ContractsProgressEvaluationDataTabsPagerAdapter(
                getSupportFragmentManager(),
                getResources().getStringArray(R.array.contracts_progress_evaluation_data_tabs_tab_titles),
                constructionSiteId));

        mContractsProgressEvaluationActBinding.contractsProgressEvaluationDataTabsActMainTabLayout.setTabTextColors(ContextCompat.getColor(this, R.color.gray),ContextCompat.getColor(this, R.color.secondary_color));
        mContractsProgressEvaluationActBinding.contractsProgressEvaluationDataTabsActMainTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.secondary_color));
        mContractsProgressEvaluationActBinding.contractsProgressEvaluationDataTabsActMainTabLayout.setupWithViewPager(mContractsProgressEvaluationActBinding.contractsProgressEvaluationDataTabsActMainViewPager);
    }
}
