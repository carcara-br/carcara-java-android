package br.com.stant.stant_android_fiscal.services.contractprogressevaluation;

import android.support.annotation.NonNull;

import br.com.stant.stant_android_fiscal.domain.converters.ContractProgressEvaluationConverter;
import br.com.stant.stant_android_fiscal.domain.entity.user.UserSession;
import br.com.stant.stant_android_fiscal.domain.enums.contractprogressevaluation.ContractProgressEvaluationStatusEnum;
import br.com.stant.stant_android_fiscal.services.RemoteErrorResponse;
import br.com.stant.stant_android_fiscal.services.exception.RetrofitException;
import br.com.stant.stant_android_fiscal.services.session.SessionLocalDataSource;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by denisvieira on 16/05/17.
 */

public class ContractProgressEvaluationRemoteDataSourceImpl implements ContractProgressEvaluationRemoteDataSource {

    private static ContractProgressEvaluationRemoteDataSourceImpl INSTANCE = null;
    private final ContractProgressEvaluationApiDataSource mApiDataSource;
    private final SessionLocalDataSource mSessionLocalDataSource;

    public ContractProgressEvaluationRemoteDataSourceImpl(@NonNull ContractProgressEvaluationApiDataSource contractProgressEvaluationApiDataSource,
                                                          @NonNull SessionLocalDataSource sessionLocalDataSource) {

        mApiDataSource = checkNotNull(contractProgressEvaluationApiDataSource);
        mSessionLocalDataSource = checkNotNull(sessionLocalDataSource);
    }


    public static ContractProgressEvaluationRemoteDataSourceImpl getInstance(@NonNull ContractProgressEvaluationApiDataSource ContractProgressEvaluationApiDataSource,
                                                                   @NonNull SessionLocalDataSource sessionLocalDataSource) {
        if (INSTANCE == null)
            INSTANCE = new ContractProgressEvaluationRemoteDataSourceImpl(ContractProgressEvaluationApiDataSource, sessionLocalDataSource);

        return INSTANCE;
    }

    @Override
    public void allByConstructionSiteAndStatus(int constructionSiteId, ContractProgressEvaluationStatusEnum contractProgressEvaluationStatusEnum, LoadContractsProgressEvaluationCallback callback) {
        mApiDataSource.contractsProgressEvaluation(userSession().getAccessTokenAsHeader(), constructionSiteId, contractProgressEvaluationStatusEnum.getValue())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(constructionSitesResponse -> {
                    if (constructionSitesResponse.isEmpty())
                        callback.onEmptyData();
                    else
                        callback.onContractsProgressEvaluationLoaded(ContractProgressEvaluationConverter.convertResponsesToEntities(constructionSitesResponse));
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
