package br.com.stant.stant_android_fiscal.selectconstructionsite.domain.usecases;

import android.support.annotation.NonNull;

import java.util.List;

import br.com.stant.stant_android_fiscal.UseCase;
import br.com.stant.stant_android_fiscal.domain.entity.constructionsite.ConstructionSite;
import br.com.stant.stant_android_fiscal.services.RemoteErrorResponse;
import br.com.stant.stant_android_fiscal.services.constructionsite.ConstructionSiteRemoteDataSource;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by denisvieira on 15/05/17.
 */

public class GetConstructionSites {

    private final ConstructionSiteRemoteDataSource mConstructionSiteRemoteDataSource;

    public GetConstructionSites(@NonNull ConstructionSiteRemoteDataSource authRemoteDataSource) {
        mConstructionSiteRemoteDataSource = checkNotNull(authRemoteDataSource);
    }

    public void getConstructionSites(UseCase.LoadUseCaseCallback<List<ConstructionSite>> useCaseCallback) {
        mConstructionSiteRemoteDataSource
                .allByCompany( new ConstructionSiteRemoteDataSource.LoadConstructionSitesCallback() {
                    @Override
                    public void onConstructionSitesLoaded(List<ConstructionSite> constructionSites) {
                        useCaseCallback.onLoaded(constructionSites);
                    }

                    @Override
                    public void onEmptyData() {
                        useCaseCallback.onEmptyData();
                    }

                    @Override
                    public void onFailed(RemoteErrorResponse errorResponse) {
                        useCaseCallback.onFailed(errorResponse.getCode());
                    }
                });
    }


}
