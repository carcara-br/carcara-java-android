package br.com.stant.stant_android_fiscal.services.auth;

import br.com.stant.stant_android_fiscal.services.auth.dto.AuthRequest;
import br.com.stant.stant_android_fiscal.services.auth.dto.AuthResponse;
import br.com.stant.stant_android_fiscal.services.auth.dto.EntityResponse;
import br.com.stant.stant_android_fiscal.services.auth.dto.UserResponse;
import br.com.stant.stant_android_fiscal.services.exception.RetrofitException;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by rachidcalazans on 8/9/16.
 */

public class FakeAuthRemoteDataSource implements AuthApiDataSource {

    private final Retrofit mRetrofit;

    public FakeAuthRemoteDataSource(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    @Override
    public Observable<AuthResponse> authorize(@Body AuthRequest request) {
        return Observable.create(new Observable.OnSubscribe<AuthResponse>() {

            @Override
            public void call(Subscriber<? super AuthResponse> subscriber) {
                if (isValid()) {
                    int userId = 1;
                    String userPicture = "http://www.ohub.com.br/ideias/wp-content/uploads/2014/07/Dollarphotoclub_60960281.jpg";
                    UserResponse userResponse = new UserResponse(userId, "Ralph Vasco", userPicture, new EntityResponse(1,"entity-name","AL"));
                    AuthResponse authResponse = new AuthResponse("3c4aa43a65a23809cf344260b9ed0dd96bcea318a0c31c6b25d1f49332c73ca7", "bearer", "1447264151", userResponse);


                    subscriber.onNext(authResponse);
                } else {
                    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                    String jsonString = "{\"error\": \"invalid_grant\", \"error_description\":\"Invalid Grant message\"}";
                    ResponseBody responseBody = ResponseBody.create(JSON, jsonString);
                    final int UNAUTHORIZED = 401;
                    retrofit2.Response r = retrofit2.Response.error(UNAUTHORIZED, responseBody);

                    RetrofitException re = RetrofitException.httpError("http://fake.stant.com.br/oauth/token", r, mRetrofit);

                    subscriber.onError(re);
                }
            }

            private boolean isValid() {
                return request.getEmail().equals("stant@stant.com.br") &&
                        request.getPassword().equals("stant123");
            }
        });
    }



}
