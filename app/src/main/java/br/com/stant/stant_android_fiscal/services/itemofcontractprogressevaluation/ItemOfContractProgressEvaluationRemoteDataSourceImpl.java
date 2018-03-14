package br.com.stant.stant_android_fiscal.services.itemofcontractprogressevaluation;

import android.support.annotation.NonNull;

import br.com.stant.stant_android_fiscal.domain.converters.ContractProgressEvaluationConverter;
import br.com.stant.stant_android_fiscal.domain.converters.ItemOfContractProgressEvaluationConverter;
import br.com.stant.stant_android_fiscal.domain.entity.user.UserSession;
import br.com.stant.stant_android_fiscal.domain.enums.contractprogressevaluation.ContractProgressEvaluationStatusEnum;
import br.com.stant.stant_android_fiscal.services.RemoteErrorResponse;
import br.com.stant.stant_android_fiscal.services.exception.RetrofitException;
import br.com.stant.stant_android_fiscal.services.itemofcontractprogressevaluation.dto.ApproveRequest;
import br.com.stant.stant_android_fiscal.services.itemofcontractprogressevaluation.dto.DisapproveRequest;
import br.com.stant.stant_android_fiscal.services.session.SessionLocalDataSource;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by denisvieira on 16/05/17.
 */

public class ItemOfContractProgressEvaluationRemoteDataSourceImpl implements ItemOfContractProgressEvaluationRemoteDataSource {

    private static ItemOfContractProgressEvaluationRemoteDataSourceImpl INSTANCE = null;
    private final ItemOfContractProgressEvaluationApiDataSource mApiDataSource;
    private final SessionLocalDataSource mSessionLocalDataSource;

    public ItemOfContractProgressEvaluationRemoteDataSourceImpl(@NonNull ItemOfContractProgressEvaluationApiDataSource contractProgressEvaluationApiDataSource,
                                                                @NonNull SessionLocalDataSource sessionLocalDataSource) {

        mApiDataSource = checkNotNull(contractProgressEvaluationApiDataSource);
        mSessionLocalDataSource = checkNotNull(sessionLocalDataSource);
    }


    public static ItemOfContractProgressEvaluationRemoteDataSourceImpl getInstance(@NonNull ItemOfContractProgressEvaluationApiDataSource ContractProgressEvaluationApiDataSource,
                                                                                   @NonNull SessionLocalDataSource sessionLocalDataSource) {
        if (INSTANCE == null)
            INSTANCE = new ItemOfContractProgressEvaluationRemoteDataSourceImpl(ContractProgressEvaluationApiDataSource, sessionLocalDataSource);

        return INSTANCE;
    }

    private UserSession userSession() {
        return mSessionLocalDataSource.getUserSession();
    }

    @Override
    public void allByContractProgressEvaluation(int contractProgressEvaluationId, LoadItemsOfContractProgressEvaluationCallback callback) {
        mApiDataSource.itemsOfContractProgressEvaluation(userSession().getAccessTokenAsHeader(), contractProgressEvaluationId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(itemsOfContractProgressEvaluationResponse -> {
                    if (itemsOfContractProgressEvaluationResponse.isEmpty())
                        callback.onEmptyData();
                    else
                        callback.onItemsOfContractProgressEvaluationLoaded(ItemOfContractProgressEvaluationConverter.convertResponsesToEntities(itemsOfContractProgressEvaluationResponse));
                }, throwable -> {
                    throwable.printStackTrace();
                    RetrofitException error = (RetrofitException) throwable;
                    RemoteErrorResponse remoteErrorResponse = error.getErrorBodyAs(RemoteErrorResponse.class);
                    callback.onFailed(remoteErrorResponse);
                });
    }

    @Override
    public void disapproveItemOfConstractProgressEvaluation(int itemOfConstractProgressEvaluationId, int contractProgressEvaluationId, String observation, SetSituationCallback callback) {
        DisapproveRequest disapproveRequest = new DisapproveRequest(contractProgressEvaluationId,itemOfConstractProgressEvaluationId,observation);
        mApiDataSource.disapprove(userSession().getAccessTokenAsHeader(), disapproveRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(itemOfContractProgressEvaluationResponse -> {
                    if (itemOfContractProgressEvaluationResponse != null)
                        callback.onSuccess();
                }, throwable -> {
                    throwable.printStackTrace();
                    RetrofitException error = (RetrofitException) throwable;
                    RemoteErrorResponse remoteErrorResponse = error.getErrorBodyAs(RemoteErrorResponse.class);
                    callback.onError(remoteErrorResponse);
                });
    }

    @Override
    public void approveItemOfConstractProgressEvaluation(int itemOfConstractProgressEvaluationId, int contractProgressEvaluationId, SetSituationCallback callback) {
        ApproveRequest approveRequest = new ApproveRequest(contractProgressEvaluationId,itemOfConstractProgressEvaluationId);
        mApiDataSource.approve(userSession().getAccessTokenAsHeader(), approveRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(itemOfContractProgressEvaluationResponse -> {
                    if (itemOfContractProgressEvaluationResponse != null)
                        callback.onSuccess();
                }, throwable -> {
                    throwable.printStackTrace();
                    RetrofitException error = (RetrofitException) throwable;
                    RemoteErrorResponse remoteErrorResponse = error.getErrorBodyAs(RemoteErrorResponse.class);
                    callback.onError(remoteErrorResponse);
                });
    }
}
