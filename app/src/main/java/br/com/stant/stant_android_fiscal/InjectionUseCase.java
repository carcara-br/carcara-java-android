package br.com.stant.stant_android_fiscal;

import android.content.Context;

import br.com.stant.stant_android_fiscal.contractprogressevaluation.constractsprogressevaluation.domain.usecases.GetContractsProgressEvaluation;
import br.com.stant.stant_android_fiscal.contractprogressevaluation.contractprogressevaluationdetail.domain.usecases.ApproveItemOfContractProgressEvaluation;
import br.com.stant.stant_android_fiscal.contractprogressevaluation.contractprogressevaluationdetail.domain.usecases.DisapproveItemOfContractProgressEvaluation;
import br.com.stant.stant_android_fiscal.contractprogressevaluation.contractprogressevaluationdetail.domain.usecases.GetItemsOfContractProgressEvaluation;
import br.com.stant.stant_android_fiscal.mainmenu.domain.usecases.DestroyUserSession;
import br.com.stant.stant_android_fiscal.login.domain.usecases.GetAuth;
import br.com.stant.stant_android_fiscal.selectconstructionsite.domain.usecases.GetConstructionSites;

/**
 * Created by denisvieira on 12/05/17.
 */
public class InjectionUseCase {

    public static GetAuth provideGetAuth(Context context) {
        return new GetAuth( InjectionRemoteDataSource.provideAuthRemoteDataSource(context));
    }

    public static DestroyUserSession provideDestroyUserSession(Context context) {
            return new DestroyUserSession(InjectionLocalDataSource.provideSessionLocalDataSource(context));
    }

    public static GetConstructionSites provideGetConstructionSites(Context context) {
        return new GetConstructionSites( InjectionRemoteDataSource.provideConstructionSiteRemoteDataSource(context));
    }

    public static GetContractsProgressEvaluation provideGetContractsProgressEvaluation(Context context) {
        return new GetContractsProgressEvaluation( InjectionRemoteDataSource.provideContractProgressEvaluationRemoteDataSource(context));
    }

    public static GetItemsOfContractProgressEvaluation provideGetItemsOfContractProgressEvaluation(Context context) {
        return new GetItemsOfContractProgressEvaluation( InjectionRemoteDataSource.provideItemOfContractProgressEvaluationRemoteDataSource(context));
    }

    public static ApproveItemOfContractProgressEvaluation provideApproveItemOfContractProgressEvaluation(Context context) {
        return new ApproveItemOfContractProgressEvaluation( InjectionRemoteDataSource.provideItemOfContractProgressEvaluationRemoteDataSource(context));
    }

    public static DisapproveItemOfContractProgressEvaluation provideDisapproveItemOfContractProgressEvaluation(Context context) {
        return new DisapproveItemOfContractProgressEvaluation( InjectionRemoteDataSource.provideItemOfContractProgressEvaluationRemoteDataSource(context));
    }

}
