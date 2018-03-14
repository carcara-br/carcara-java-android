package br.com.stant.stant_android_fiscal.selectconstructionsite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.stant.stant_android_fiscal.InjectionUseCase;
import br.com.stant.stant_android_fiscal.R;
import br.com.stant.stant_android_fiscal.util.ActivityUtils;

/**
 * Created by denisvieira on 09/05/17.
 */

public class SelectConstructionSiteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_construction_site_act);

        SelectConstructionSiteFragment selectConstructionSiteFragment = (SelectConstructionSiteFragment) getSupportFragmentManager().findFragmentById(R.id.contract_progress_evaluation_detail_content_frame);

        if (selectConstructionSiteFragment == null){
            selectConstructionSiteFragment = selectConstructionSiteFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), selectConstructionSiteFragment, R.id.contract_progress_evaluation_detail_content_frame);
        }

        new SelectConstructionSitePresenter(
                selectConstructionSiteFragment,
                InjectionUseCase.provideGetConstructionSites(getApplicationContext()));
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

}
