package br.com.stant.stant_android_fiscal.login;

import android.content.Context;
import android.support.annotation.NonNull;

import br.com.stant.stant_android_fiscal.UseCase;
import br.com.stant.stant_android_fiscal.domain.enums.auth.AuthRemoteErrorCodeEnum;
import br.com.stant.stant_android_fiscal.services.auth.dto.AuthRequest;
import br.com.stant.stant_android_fiscal.login.domain.usecases.GetAuth;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by denisvieira on 09/05/17.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View mLoginView;
    private final Context context;
    private final GetAuth mGetAuth;

    public LoginPresenter(@NonNull LoginContract.View loginView,
                          @NonNull GetAuth getAuth,
                          @NonNull Context context) {
        this.mLoginView       = checkNotNull(loginView);
        this.mGetAuth         = checkNotNull(getAuth);
        this.context          = checkNotNull(context);

        mLoginView.setPresenter(this);
    }


    @Override
    public void login(String email, String password, String deviceId) {

        AuthRequest request = new AuthRequest(email, password, deviceId);
        mLoginView.showRemoteRequestLoader();

        mGetAuth.authorize(request, new UseCase.UseCaseAuthCallback() {
            @Override
            public void onSuccess() {
                mLoginView.hideRemoteRequestLoader();
                mLoginView.goToSelectConstructionSite();
            }

            @Override
            public void onError(AuthRemoteErrorCodeEnum authRemoteErrorCodeEnum) {
                mLoginView.hideRemoteRequestLoader();
                mLoginView.showLoginErrors(authRemoteErrorCodeEnum);
            }

        });

    }
}
