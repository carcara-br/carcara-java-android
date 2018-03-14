package br.com.stant.stant_android_fiscal;

import br.com.stant.stant_android_fiscal.domain.enums.auth.AuthRemoteErrorCodeEnum;

/**
 * Created by denisvieira on 12/05/17.
 */

public class UseCase {

    public interface UseCaseCallback {
        void onSuccess();
        void onError(String errorDescription);
    }

    public interface UseCaseAuthCallback {
        void onSuccess();
        void onError(AuthRemoteErrorCodeEnum authRemoteErrorCodeEnum);
    }

    public interface LoadUseCaseCallback<R> {
        void onLoaded(R response);
        void onEmptyData();
        void onFailed(int errorCode);
    }

}
