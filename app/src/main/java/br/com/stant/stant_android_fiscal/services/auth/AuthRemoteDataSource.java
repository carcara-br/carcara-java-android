package br.com.stant.stant_android_fiscal.services.auth;

import br.com.stant.stant_android_fiscal.services.RemoteErrorResponse;
import br.com.stant.stant_android_fiscal.services.auth.dto.AuthRequest;

/**
 * Created by denisvieira on 12/05/17.
 */

public interface AuthRemoteDataSource {

    interface AuthCallback {
        void onAuthorized();
        void onFailed(RemoteErrorResponse remoteErrorResponse);
    }

    void authorize(AuthRequest request, AuthCallback callback);
}
