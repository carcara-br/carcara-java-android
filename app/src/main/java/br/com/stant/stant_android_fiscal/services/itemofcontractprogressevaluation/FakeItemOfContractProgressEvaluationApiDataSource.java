package br.com.stant.stant_android_fiscal.services.itemofcontractprogressevaluation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.stant.stant_android_fiscal.domain.builders.ItemOfContractProgressEvaluationResponseBuilder;
import br.com.stant.stant_android_fiscal.domain.converters.ItemOfContractProgressEvaluationConverter;
import br.com.stant.stant_android_fiscal.domain.enums.contractprogressevaluation.ItemOfContractProgressEvaluationStatusEnum;
import br.com.stant.stant_android_fiscal.services.itemofcontractprogressevaluation.dto.ApproveRequest;
import br.com.stant.stant_android_fiscal.services.itemofcontractprogressevaluation.dto.DisapproveRequest;
import br.com.stant.stant_android_fiscal.services.itemofcontractprogressevaluation.dto.ItemOfContractProgressEvaluationResponse;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by rachidcalazans on 9/20/16.
 */
public class FakeItemOfContractProgressEvaluationApiDataSource implements ItemOfContractProgressEvaluationApiDataSource {

    private final Retrofit mRetrofit;

    private Observable.OnSubscribe<List<ItemOfContractProgressEvaluationResponse>> mListOnSubscribe = subscriber -> {
        subscriber.onNext(getItemsOfContractProgressEvaluationResponse());
    };

    private Observable.OnSubscribe<ItemOfContractProgressEvaluationResponse> mApprovedObjectOnSubscribe = subscriber -> {
        subscriber.onNext( new ItemOfContractProgressEvaluationResponseBuilder()
                .id(1)
                .beginAt(new Date())
                .endAt(new Date())
                .performedPercentage(30)
                .place("place 1")
                .serviceTitle("Service Title 1")
                .status(ItemOfContractProgressEvaluationStatusEnum.APPROVED.getValue())
                .build());
    };

    private Observable.OnSubscribe<ItemOfContractProgressEvaluationResponse> mDisapprovedObjectOnSubscribe = subscriber -> {
        subscriber.onNext( new ItemOfContractProgressEvaluationResponseBuilder()
                .id(1)
                .beginAt(new Date())
                .endAt(new Date())
                .performedPercentage(30)
                .place("place 1")
                .serviceTitle("Service Title 1")
                .status(ItemOfContractProgressEvaluationStatusEnum.DISAPPROVED.getValue())
                .build());
    };

    public FakeItemOfContractProgressEvaluationApiDataSource(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    @Override
    public Observable<List<ItemOfContractProgressEvaluationResponse>> itemsOfContractProgressEvaluation(@Header("Authorization") String accessToken, @Query("contract_progress_evaluation_id") int contractProgressEvaluationId) {
        return Observable.create(mListOnSubscribe);
    }

    @Override
    public Observable<ItemOfContractProgressEvaluationResponse> approve(@Header("Authorization") String accessToken, @Body ApproveRequest request) {
        return Observable.create(mApprovedObjectOnSubscribe);
    }

    @Override
    public Observable<ItemOfContractProgressEvaluationResponse> disapprove(@Header("Authorization") String accessToken, @Body DisapproveRequest request) {
        return Observable.create(mDisapprovedObjectOnSubscribe);
    }

    private List<ItemOfContractProgressEvaluationResponse> getItemsOfContractProgressEvaluationResponse(){

        List<ItemOfContractProgressEvaluationResponse> itemsOfContractProgressEvaluationResponse = new ArrayList<>();

        ItemOfContractProgressEvaluationResponse itemOfContractProgressEvaluationResponse1 =
                new ItemOfContractProgressEvaluationResponseBuilder()
                        .id(1)
                        .beginAt(new Date())
                        .endAt(new Date())
                        .performedPercentage(30)
                        .place("place 1")
                        .serviceTitle("Service Title 1")
                        .status(ItemOfContractProgressEvaluationStatusEnum.PROGRESS.getValue())
                        .build();

        ItemOfContractProgressEvaluationResponse itemOfContractProgressEvaluationResponse2 =
                new ItemOfContractProgressEvaluationResponseBuilder()
                        .id(1)
                        .beginAt(new Date())
                        .endAt(new Date())
                        .performedPercentage(30)
                        .place("place 1")
                        .serviceTitle("Service Title 2")
                        .status(ItemOfContractProgressEvaluationStatusEnum.PROGRESS.getValue())
                        .build();

        ItemOfContractProgressEvaluationResponse itemOfContractProgressEvaluationResponse3 =
                new ItemOfContractProgressEvaluationResponseBuilder()
                        .id(1)
                        .beginAt(new Date())
                        .endAt(new Date())
                        .performedPercentage(30)
                        .place("place 1")
                        .serviceTitle("Service Title 3")
                        .status(ItemOfContractProgressEvaluationStatusEnum.PROGRESS.getValue())
                        .build();

        itemsOfContractProgressEvaluationResponse.add(itemOfContractProgressEvaluationResponse1);
        itemsOfContractProgressEvaluationResponse.add(itemOfContractProgressEvaluationResponse2);
        itemsOfContractProgressEvaluationResponse.add(itemOfContractProgressEvaluationResponse3);

        return itemsOfContractProgressEvaluationResponse;
    }
}
