package br.com.stant.stant_android_fiscal;

import android.content.Context;

import br.com.stant.stant_android_fiscal.services.auth.AuthApiDataSource;
import br.com.stant.stant_android_fiscal.services.auth.AuthRemoteDataSource;
import br.com.stant.stant_android_fiscal.services.auth.AuthRemoteDataSourceImpl;
import br.com.stant.stant_android_fiscal.services.constructionsite.ConstructionSiteApiDataSource;
import br.com.stant.stant_android_fiscal.services.constructionsite.ConstructionSiteRemoteDataSource;
import br.com.stant.stant_android_fiscal.services.constructionsite.ConstructionSiteRemoteDataSourceImpl;
import br.com.stant.stant_android_fiscal.services.contractprogressevaluation.ContractProgressEvaluationApiDataSource;
import br.com.stant.stant_android_fiscal.services.contractprogressevaluation.ContractProgressEvaluationRemoteDataSource;
import br.com.stant.stant_android_fiscal.services.contractprogressevaluation.ContractProgressEvaluationRemoteDataSourceImpl;
import br.com.stant.stant_android_fiscal.services.itemofcontractprogressevaluation.ItemOfContractProgressEvaluationApiDataSource;
import br.com.stant.stant_android_fiscal.services.itemofcontractprogressevaluation.ItemOfContractProgressEvaluationRemoteDataSource;
import br.com.stant.stant_android_fiscal.services.itemofcontractprogressevaluation.ItemOfContractProgressEvaluationRemoteDataSourceImpl;
import br.com.stant.stant_android_fiscal.services.session.SessionLocalDataSource;

/**
 * Created by denisvieira on 12/05/17.
 */

public class InjectionRemoteDataSource {

    public static AuthRemoteDataSource provideAuthRemoteDataSource(Context context) {
        AuthApiDataSource authApiDataSource = InjectionApiDataSource.provideAuthApiDataSource();
        SessionLocalDataSource sessionLocalDataSource =InjectionLocalDataSource.provideSessionLocalDataSource(context);

        return AuthRemoteDataSourceImpl.getInstance(authApiDataSource, sessionLocalDataSource);
    }

    public static ConstructionSiteRemoteDataSource provideConstructionSiteRemoteDataSource(Context context) {
        ConstructionSiteApiDataSource constructionSiteApiDataSource = InjectionApiDataSource.provideConstructionSiteApiDataSource();
        SessionLocalDataSource sessionLocalDataSource =InjectionLocalDataSource.provideSessionLocalDataSource(context);

        return ConstructionSiteRemoteDataSourceImpl.getInstance(constructionSiteApiDataSource, sessionLocalDataSource);
    }

    public static ContractProgressEvaluationRemoteDataSource provideContractProgressEvaluationRemoteDataSource(Context context) {
        ContractProgressEvaluationApiDataSource contractProgressEvaluationApiDataSource = InjectionApiDataSource.provideContractProgressEvaluationApiDataSource();
        SessionLocalDataSource sessionLocalDataSource =InjectionLocalDataSource.provideSessionLocalDataSource(context);

        return ContractProgressEvaluationRemoteDataSourceImpl.getInstance(contractProgressEvaluationApiDataSource, sessionLocalDataSource);
    }

    public static ItemOfContractProgressEvaluationRemoteDataSource provideItemOfContractProgressEvaluationRemoteDataSource(Context context) {
        ItemOfContractProgressEvaluationApiDataSource itemOfContractProgressEvaluationApiDataSource = InjectionApiDataSource.provideItemOfContractProgressEvaluationApiDataSource();
        SessionLocalDataSource sessionLocalDataSource =InjectionLocalDataSource.provideSessionLocalDataSource(context);

        return ItemOfContractProgressEvaluationRemoteDataSourceImpl.getInstance(itemOfContractProgressEvaluationApiDataSource, sessionLocalDataSource);
    }

}
