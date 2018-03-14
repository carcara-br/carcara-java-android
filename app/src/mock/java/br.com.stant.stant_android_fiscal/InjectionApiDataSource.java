package br.com.stant.stant_android_fiscal;

import br.com.stant.stant_android_fiscal.services.auth.AuthApiDataSource;
import br.com.stant.stant_android_fiscal.services.auth.FakeAuthRemoteDataSource;
import br.com.stant.stant_android_fiscal.services.constructionsite.ConstructionSiteApiDataSource;
import br.com.stant.stant_android_fiscal.services.constructionsite.FakeConstructionSiteApiDataSource;
import br.com.stant.stant_android_fiscal.services.contractprogressevaluation.ContractProgressEvaluationApiDataSource;
import br.com.stant.stant_android_fiscal.services.contractprogressevaluation.FakeContractProgressEvaluationApiDataSource;
import br.com.stant.stant_android_fiscal.services.exception.RxErrorHandlingCallAdapterFactory;
import br.com.stant.stant_android_fiscal.services.itemofcontractprogressevaluation.FakeItemOfContractProgressEvaluationApiDataSource;
import br.com.stant.stant_android_fiscal.services.itemofcontractprogressevaluation.ItemOfContractProgressEvaluationApiDataSource;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rachidcalazans on 9/13/16.
 */
public class InjectionApiDataSource {

    public static Retrofit buildFakeRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .build();
    }

    public static AuthApiDataSource provideAuthApiDataSource() {
        return new FakeAuthRemoteDataSource(buildFakeRetrofit());
    }

    public static ConstructionSiteApiDataSource provideConstructionSiteApiDataSource() {
        return new FakeConstructionSiteApiDataSource(buildFakeRetrofit());
    }

    public static ContractProgressEvaluationApiDataSource provideContractProgressEvaluationApiDataSource() {
        return new FakeContractProgressEvaluationApiDataSource(buildFakeRetrofit());
    }

    public static ItemOfContractProgressEvaluationApiDataSource provideItemOfContractProgressEvaluationApiDataSource() {
        return new FakeItemOfContractProgressEvaluationApiDataSource(buildFakeRetrofit());
    }


}
