package br.com.stant.stant_android_fiscal.services.auth;

import br.com.stant.stant_android_fiscal.services.auth.dto.AuthRequest;
import br.com.stant.stant_android_fiscal.services.auth.dto.AuthResponse;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by rachidcalazans on 8/5/16.
 */

public interface AuthApiDataSource {

    @POST("api/v1/authenticate/entity_access_token")
    Observable<AuthResponse> authorize(@Body AuthRequest request);
}