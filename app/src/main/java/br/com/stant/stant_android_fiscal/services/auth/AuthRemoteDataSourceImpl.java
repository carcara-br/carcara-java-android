package br.com.stant.stant_android_fiscal.services.auth;

import android.support.annotation.NonNull;

import br.com.stant.stant_android_fiscal.domain.converters.UserSessionConverter;
import br.com.stant.stant_android_fiscal.domain.entity.user.UserSession;
import br.com.stant.stant_android_fiscal.services.RemoteErrorResponse;
import br.com.stant.stant_android_fiscal.services.auth.dto.AuthRequest;
import br.com.stant.stant_android_fiscal.services.exception.RetrofitException;
import br.com.stant.stant_android_fiscal.services.session.SessionLocalDataSource;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by denisvieira on 12/05/17.
 */

public class AuthRemoteDataSourceImpl implements AuthRemoteDataSource{

    private static AuthRemoteDataSourceImpl INSTANCE = null;
    private final AuthApiDataSource mApiDataSource;
    private final SessionLocalDataSource mSessionLocalDataSource;

    public AuthRemoteDataSourceImpl(@NonNull AuthApiDataSource authApiDataSource,
                                    @NonNull SessionLocalDataSource sessionLocalDataSource) {

        mApiDataSource = checkNotNull(authApiDataSource);
        mSessionLocalDataSource = checkNotNull(sessionLocalDataSource);
    }

    public static AuthRemoteDataSourceImpl getInstance(@NonNull AuthApiDataSource authApiDataSource,
                                                       @NonNull SessionLocalDataSource sessionLocalDataSource) {
        if (INSTANCE == null)
            INSTANCE = new AuthRemoteDataSourceImpl(authApiDataSource, sessionLocalDataSource);

        return INSTANCE;
    }

    @Override
    public void authorize(AuthRequest request, AuthCallback callback) {
        mApiDataSource.authorize(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(authResponse -> {
                    UserSession userSession = UserSessionConverter.convertFromResponse(authResponse, request.getDeviceId());
                    mSessionLocalDataSource.save(userSession);
                    callback.onAuthorized();
                }, throwable -> {
                    RetrofitException error = (RetrofitException) throwable;
                    RemoteErrorResponse errorResponse = error.getErrorBodyAs(RemoteErrorResponse.class);
                    callback.onFailed(errorResponse);
                });
    }
}
