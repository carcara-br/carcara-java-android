package br.com.stant.stant_android_fiscal;

import android.content.Context;

import br.com.stant.stant_android_fiscal.domain.entity.constructionsite.ConstructionSite;
import br.com.stant.stant_android_fiscal.domain.entity.session.SessionPreferences;
import br.com.stant.stant_android_fiscal.services.ApiDataSource;
import br.com.stant.stant_android_fiscal.services.auth.AuthApiDataSource;
import br.com.stant.stant_android_fiscal.services.constructionsite.ConstructionSiteApiDataSource;
import br.com.stant.stant_android_fiscal.services.contractprogressevaluation.ContractProgressEvaluationApiDataSource;
import br.com.stant.stant_android_fiscal.services.itemofcontractprogressevaluation.ItemOfContractProgressEvaluationApiDataSource;

/**
 * Created by denisvieira on 12/05/17.
 */

public class InjectionApiDataSourceMain {

    private static ApiDataSource provideApiDataSource() {
        String baseUrl = BuildConfig.BASE_URL;
        return ApiDataSource.getInstance(baseUrl);
    }

    public static AuthApiDataSource provideAuthApiDataSource() {
        return provideApiDataSource().authRemoteDataSource();
    }

    public static ConstructionSiteApiDataSource provideConstructionSiteApiDataSource() {
        return provideApiDataSource().constructionSiteRemoteDataSource();
    }

    public static ContractProgressEvaluationApiDataSource provideContractProgressEvaluationApiDataSource() {
        return provideApiDataSource().contractProgressEvaluationRemoteDataSource();
    }

    public static ItemOfContractProgressEvaluationApiDataSource provideItemOfContractProgressEvaluationApiDataSource() {
        return provideApiDataSource().itemOfcontractProgressEvaluationRemoteDataSource();
    }

    public static SessionPreferences provideSessionPreferences(Context context) {
        return SessionPreferences.getInstance(context);
    }

}
