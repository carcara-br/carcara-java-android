package br.com.stant.stant_android_fiscal.services.contractprogressevaluation;


import java.util.List;

import br.com.stant.stant_android_fiscal.services.contractprogressevaluation.dto.ContractProgressEvaluationResponse;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by denisvieira on 16/05/17.
 */

public interface ContractProgressEvaluationApiDataSource {

    @GET("api/v1/construction_site_progress_evaluations/contract_progress_evaluations")
    Observable<List<ContractProgressEvaluationResponse>> contractsProgressEvaluation(@Header("Authorization") String accessToken,
                                                                                     @Query("construction_site_id") int constructionSiteId,
                                                                                     @Query("status") int statusValue);
}
