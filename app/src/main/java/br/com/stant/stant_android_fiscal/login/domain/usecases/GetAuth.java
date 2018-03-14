package br.com.stant.stant_android_fiscal.login.domain.usecases;

import android.support.annotation.NonNull;

import br.com.stant.stant_android_fiscal.UseCase;
import br.com.stant.stant_android_fiscal.domain.enums.auth.AuthRemoteErrorCodeEnum;
import br.com.stant.stant_android_fiscal.services.RemoteErrorResponse;
import br.com.stant.stant_android_fiscal.services.auth.AuthRemoteDataSource;
import br.com.stant.stant_android_fiscal.services.auth.dto.AuthRequest;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by denisvieira on 12/05/17.
 */

public class GetAuth {

    private final AuthRemoteDataSource mAuthRemoteDataSource;

    public GetAuth(@NonNull AuthRemoteDataSource authRemoteDataSource) {
        mAuthRemoteDataSource = checkNotNull(authRemoteDataSource);
    }

    public void authorize(AuthRequest request, UseCase.UseCaseAuthCallback callback) {



        mAuthRemoteDataSource.authorize(request, new AuthRemoteDataSource.AuthCallback() {
            @Override
            public void onAuthorized() {
                callback.onSuccess();
            }

            @Override
            public void onFailed(RemoteErrorResponse remoteErrorResponse) {
                callback.onError(AuthRemoteErrorCodeEnum.getEnum(remoteErrorResponse.getCode()));
            }

        });
    }

}
