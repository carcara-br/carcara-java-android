package br.com.stant.stant_android_fiscal.services;

import java.util.concurrent.TimeUnit;

import br.com.stant.stant_android_fiscal.domain.entity.contractprogressevaluation.ContractProgressEvaluation;
import br.com.stant.stant_android_fiscal.services.auth.AuthApiDataSource;
import br.com.stant.stant_android_fiscal.services.constructionsite.ConstructionSiteApiDataSource;
import br.com.stant.stant_android_fiscal.services.contractprogressevaluation.ContractProgressEvaluationApiDataSource;
import br.com.stant.stant_android_fiscal.services.exception.RxErrorHandlingCallAdapterFactory;
import br.com.stant.stant_android_fiscal.services.itemofcontractprogressevaluation.ItemOfContractProgressEvaluationApiDataSource;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rachidcalazans on 8/4/16.
 */

public class ApiDataSource {

    private static final Integer PER_PAGE = 200;
    private static ApiDataSource INSTANCE = null;
    private final String mBaseUrl;
    // TODO remove the timeout
    private OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build();

    private ApiDataSource(String baseUrl) {
        this.mBaseUrl = baseUrl;
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param baseUrl the url to get remote data source
     * @return the {@link ApiDataSource} instance
     */
    public static ApiDataSource getInstance(String baseUrl) {
        if (INSTANCE == null)
            INSTANCE = new ApiDataSource(baseUrl);

        return INSTANCE;
    }

    public Integer getPerPage() {
        return PER_PAGE;
    }

    private Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .build();
    }

    public AuthApiDataSource authRemoteDataSource() {
        return buildRetrofit().create(AuthApiDataSource.class);
    }

    public ConstructionSiteApiDataSource constructionSiteRemoteDataSource() {
        return buildRetrofit().create(ConstructionSiteApiDataSource.class);
    }

    public ContractProgressEvaluationApiDataSource contractProgressEvaluationRemoteDataSource() {
        return buildRetrofit().create(ContractProgressEvaluationApiDataSource.class);
    }

    public ItemOfContractProgressEvaluationApiDataSource itemOfcontractProgressEvaluationRemoteDataSource() {
        return buildRetrofit().create(ItemOfContractProgressEvaluationApiDataSource.class);
    }

}
