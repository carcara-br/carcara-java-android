package br.com.stant.stant_android_fiscal.selectconstructionsite;

import java.util.List;

import br.com.stant.stant_android_fiscal.BaseAdapters;
import br.com.stant.stant_android_fiscal.BasePresenter;
import br.com.stant.stant_android_fiscal.BaseView;
import br.com.stant.stant_android_fiscal.domain.entity.constructionsite.ConstructionSite;

/**
 * Created by denisvieira on 10/05/17.
 */

public interface SelectConstructionSiteContract {

    interface View extends BaseView<Presenter> {
        void openMainMenu(android.view.View view);
        void showConstructionSites(List<ConstructionSite> constructionSites);
        void showNoConstructionSites();
        void goToContractProgressEvaluation(int constructionSiteId, String constructionSiteTitle);
        void onFailedLoadConstructionSites(int errorCode);
    }

    interface Presenter extends BasePresenter {
        void loadConstructionSites();
    }

    interface Adapter extends BaseAdapters {}
}
