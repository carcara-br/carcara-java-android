package br.com.stant.stant_android_fiscal.services.itemofcontractprogressevaluation;


import java.util.List;

import br.com.stant.stant_android_fiscal.services.contractprogressevaluation.dto.ContractProgressEvaluationResponse;
import br.com.stant.stant_android_fiscal.services.itemofcontractprogressevaluation.dto.ApproveRequest;
import br.com.stant.stant_android_fiscal.services.itemofcontractprogressevaluation.dto.DisapproveRequest;
import br.com.stant.stant_android_fiscal.services.itemofcontractprogressevaluation.dto.ItemOfContractProgressEvaluationResponse;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by denisvieira on 16/05/17.
 */

public interface ItemOfContractProgressEvaluationApiDataSource {

    @GET("api/v1/construction_site_progress_evaluations/contract_progress_evaluations/items")
    Observable<List<ItemOfContractProgressEvaluationResponse>> itemsOfContractProgressEvaluation(@Header("Authorization") String accessToken,
                                                                                                 @Query("contract_progress_evaluation_id") int contractProgressEvaluationId);

    @POST("api/v1/construction_site_progress_evaluations/contract_progress_evaluations/approve_item")
    Observable<ItemOfContractProgressEvaluationResponse> approve(@Header("Authorization") String accessToken,
                                                                 @Body ApproveRequest request);

    @POST("api/v1/construction_site_progress_evaluations/contract_progress_evaluations/disapprove_item")
    Observable<ItemOfContractProgressEvaluationResponse> disapprove(@Header("Authorization") String accessToken,
                                                                    @Body DisapproveRequest request);
}
