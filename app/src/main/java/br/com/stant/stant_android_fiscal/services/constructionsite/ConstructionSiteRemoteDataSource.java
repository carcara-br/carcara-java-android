package br.com.stant.stant_android_fiscal.services.constructionsite;

import java.util.List;

import br.com.stant.stant_android_fiscal.domain.entity.constructionsite.ConstructionSite;
import br.com.stant.stant_android_fiscal.services.RemoteErrorResponse;

/**
 * Created by denisvieira on 15/05/17.
 */

public interface ConstructionSiteRemoteDataSource {

    interface LoadConstructionSitesCallback {
        void onConstructionSitesLoaded(List<ConstructionSite> constructionSites);
        void onEmptyData();
        void onFailed(RemoteErrorResponse errorResponse);
    }

    void allByCompany(final LoadConstructionSitesCallback callback);
}
