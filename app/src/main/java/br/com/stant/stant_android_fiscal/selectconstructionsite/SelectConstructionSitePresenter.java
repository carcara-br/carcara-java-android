package br.com.stant.stant_android_fiscal.selectconstructionsite;

import android.support.annotation.NonNull;

import java.util.List;

import br.com.stant.stant_android_fiscal.UseCase;
import br.com.stant.stant_android_fiscal.domain.converters.ConstructionSiteConverter;
import br.com.stant.stant_android_fiscal.domain.entity.constructionsite.ConstructionSite;
import br.com.stant.stant_android_fiscal.selectconstructionsite.domain.usecases.GetConstructionSites;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by denisvieira on 10/05/17.
 */

public class SelectConstructionSitePresenter implements SelectConstructionSiteContract.Presenter {

    private final SelectConstructionSiteContract.View mConstructionSiteView;
    private final GetConstructionSites mGetConstructionSites;

    public SelectConstructionSitePresenter(@NonNull SelectConstructionSiteContract.View constructionSiteView ,
                                           @NonNull GetConstructionSites getConstructionSites) {
        mConstructionSiteView = checkNotNull(constructionSiteView);
        mGetConstructionSites = checkNotNull(getConstructionSites);

        mConstructionSiteView.setPresenter(this);
    }

    @Override
    public void loadConstructionSites() {
        mGetConstructionSites.getConstructionSites( new UseCase.LoadUseCaseCallback<List<ConstructionSite>>() {
            @Override
            public void onLoaded(List<ConstructionSite> constructionSites) {
                mConstructionSiteView.showConstructionSites(constructionSites);
            }

            @Override
            public void onEmptyData() {
                mConstructionSiteView.showNoConstructionSites();
            }

            @Override
            public void onFailed(int errorCode) {
                mConstructionSiteView.onFailedLoadConstructionSites(errorCode);
            }
        });
    }
}
