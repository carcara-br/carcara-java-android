package br.com.stant.stant_android_fiscal.services.constructionsite;

import android.support.annotation.NonNull;

import br.com.stant.stant_android_fiscal.domain.converters.ConstructionSiteConverter;
import br.com.stant.stant_android_fiscal.domain.entity.user.UserSession;
import br.com.stant.stant_android_fiscal.services.RemoteErrorResponse;
import br.com.stant.stant_android_fiscal.services.exception.RetrofitException;
import br.com.stant.stant_android_fiscal.services.session.SessionLocalDataSource;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by denisvieira on 15/05/17.
 */

public class ConstructionSiteRemoteDataSourceImpl implements ConstructionSiteRemoteDataSource {

    private static ConstructionSiteRemoteDataSourceImpl INSTANCE = null;
    private final ConstructionSiteApiDataSource mApiDataSource;
    private final SessionLocalDataSource mSessionLocalDataSource;

    public ConstructionSiteRemoteDataSourceImpl(@NonNull ConstructionSiteApiDataSource constructionSiteApiDataSource,
                                                @NonNull SessionLocalDataSource sessionLocalDataSource) {

        mApiDataSource = checkNotNull(constructionSiteApiDataSource);
        mSessionLocalDataSource = checkNotNull(sessionLocalDataSource);
    }


    public static ConstructionSiteRemoteDataSourceImpl getInstance(@NonNull ConstructionSiteApiDataSource constructionSiteApiDataSource,
                                                       @NonNull SessionLocalDataSource sessionLocalDataSource) {
        if (INSTANCE == null)
            INSTANCE = new ConstructionSiteRemoteDataSourceImpl(constructionSiteApiDataSource, sessionLocalDataSource);

        return INSTANCE;
    }


    @Override
    public void allByCompany(LoadConstructionSitesCallback callback) {
        mApiDataSource.constructionSites(userSession().getAccessTokenAsHeader(),userSession().getEntity().getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(constructionSitesResponse -> {
                    if (constructionSitesResponse.isEmpty())
                        callback.onEmptyData();
                    else
                        callback.onConstructionSitesLoaded(ConstructionSiteConverter.convertResponsesToEntities(constructionSitesResponse));
                }, throwable -> {
                    throwable.printStackTrace();
                    RetrofitException error = (RetrofitException) throwable;
                    RemoteErrorResponse remoteErrorResponse = error.getErrorBodyAs(RemoteErrorResponse.class);
                    callback.onFailed(remoteErrorResponse);
                });
    }

    private UserSession userSession() {
        return mSessionLocalDataSource.getUserSession();
    }
}
