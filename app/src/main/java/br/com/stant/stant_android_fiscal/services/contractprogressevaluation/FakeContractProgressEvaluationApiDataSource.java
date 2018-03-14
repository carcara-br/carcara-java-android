package br.com.stant.stant_android_fiscal.services.contractprogressevaluation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.stant.stant_android_fiscal.domain.builders.ContractProgressEvaluationResponseBuilder;
import br.com.stant.stant_android_fiscal.domain.enums.contractprogressevaluation.ContractProgressEvaluationStatusEnum;
import br.com.stant.stant_android_fiscal.services.contractprogressevaluation.dto.ContractProgressEvaluationResponse;
import retrofit2.Retrofit;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by rachidcalazans on 9/20/16.
 */
public class FakeContractProgressEvaluationApiDataSource implements ContractProgressEvaluationApiDataSource{

    private final Retrofit mRetrofit;
    private int mContractProgressEvaluationStatusEnumValue;

    private Observable.OnSubscribe<List<ContractProgressEvaluationResponse>> mListOnSubscribe = subscriber -> {
        subscriber.onNext(getContractsProgressEvaluationResponse(mContractProgressEvaluationStatusEnumValue));
    };

    public FakeContractProgressEvaluationApiDataSource(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    @Override
    public Observable<List<ContractProgressEvaluationResponse>> contractsProgressEvaluation(@Header("Authorization") String accessToken, @Query("construction_site_id") int constructionSiteId, @Query("status") int statusValue) {
        mContractProgressEvaluationStatusEnumValue = statusValue;
        return Observable.create(mListOnSubscribe);
    }

    private List<ContractProgressEvaluationResponse> getContractsProgressEvaluationResponse(int contractProgressEvaluationStatusEnumValue){

        ContractProgressEvaluationStatusEnum contractProgressEvaluationStatusEnum = ContractProgressEvaluationStatusEnum.getEnum(contractProgressEvaluationStatusEnumValue);

        List<ContractProgressEvaluationResponse> contractsProgressEvaluationFinalized = new ArrayList<>();
        List<ContractProgressEvaluationResponse> contractsProgressEvaluationApproved = new ArrayList<>();
        List<ContractProgressEvaluationResponse> contractsProgressEvaluationInProgress = new ArrayList<>();

        HashMap<ContractProgressEvaluationStatusEnum, List<ContractProgressEvaluationResponse>> responseStatusMap = new HashMap<>();
        responseStatusMap.put(ContractProgressEvaluationStatusEnum.APPROVED,contractsProgressEvaluationApproved);
        responseStatusMap.put(ContractProgressEvaluationStatusEnum.FINISH,contractsProgressEvaluationFinalized);
        responseStatusMap.put(ContractProgressEvaluationStatusEnum.PROGRESS,contractsProgressEvaluationInProgress);

        ContractProgressEvaluationResponse response1 = new ContractProgressEvaluationResponseBuilder()
                .id(1)
                .title("Contrato Fake Medição Edf. Calazans")
                .status(ContractProgressEvaluationStatusEnum.PROGRESS.getValue())
                .itemsApproved(2)
                .itemsDisapproved(5)
                .itemsNotInspected(3)
                .build();


        ContractProgressEvaluationResponse response2 = new ContractProgressEvaluationResponseBuilder()
                .id(2)
                .title("Contrato Fake Medição Edf. Gijo")
                .status(ContractProgressEvaluationStatusEnum.FINISH.getValue())
                .itemsApproved(2)
                .itemsDisapproved(5)
                .itemsNotInspected(3)
                .build();

        ContractProgressEvaluationResponse response3 = new ContractProgressEvaluationResponseBuilder()
                .id(3)
                .title("Contrato Fake Medição Edf. Vasco")
                .status(ContractProgressEvaluationStatusEnum.FINISH.getValue())
                .itemsApproved(2)
                .itemsDisapproved(5)
                .itemsNotInspected(3)
                .build();



        ContractProgressEvaluationResponse response4 = new ContractProgressEvaluationResponseBuilder()
                .id(4)
                .title("Contrato Fake Medição Edf. Rosa")
                .status(ContractProgressEvaluationStatusEnum.APPROVED.getValue())
                .itemsApproved(2)
                .itemsDisapproved(5)
                .itemsNotInspected(3)
                .build();

        ContractProgressEvaluationResponse response5 = new ContractProgressEvaluationResponseBuilder()
                .id(5)
                .title("Contrato Fake Medição Edf. Stant")
                .status(ContractProgressEvaluationStatusEnum.APPROVED.getValue())
                .itemsApproved(2)
                .itemsDisapproved(5)
                .itemsNotInspected(3)
                .build();

        contractsProgressEvaluationInProgress.add(response1);

        contractsProgressEvaluationFinalized.add(response2);
        contractsProgressEvaluationFinalized.add(response3);

        contractsProgressEvaluationApproved.add(response4);
        contractsProgressEvaluationApproved.add(response5);


        return responseStatusMap.get(contractProgressEvaluationStatusEnum);
    }
}
