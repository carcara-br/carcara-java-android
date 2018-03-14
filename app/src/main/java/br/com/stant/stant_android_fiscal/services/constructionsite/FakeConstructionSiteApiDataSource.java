package br.com.stant.stant_android_fiscal.services.constructionsite;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.stant.stant_android_fiscal.domain.builders.ConstructionSiteResponseBuilder;
import br.com.stant.stant_android_fiscal.domain.enums.constructionsite.ProjectTypeEnum;
import br.com.stant.stant_android_fiscal.services.constructionsite.dto.ConstructionSiteResponse;
import retrofit2.Retrofit;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by rachidcalazans on 9/20/16.
 */
public class FakeConstructionSiteApiDataSource implements ConstructionSiteApiDataSource {

    private final Retrofit mRetrofit;

    private Observable.OnSubscribe<List<ConstructionSiteResponse>> mListOnSubscribe = subscriber -> {
        subscriber.onNext(getConstructionSitesResponse());
    };

    public FakeConstructionSiteApiDataSource(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    @Override
    public Observable<List<ConstructionSiteResponse>> constructionSites(@Header("Authorization") String accessToken,@Query("entity_id") int entityId) {
        return Observable.create(mListOnSubscribe);
    }

    private List<ConstructionSiteResponse> getConstructionSitesResponse(){
        List<ConstructionSiteResponse> list = new ArrayList<>();

        String photoPrime = "http://seatotal.com.br/wp-content/uploads/2014/11/imagem_post-construtoras-seatotal.jpg";

        Date date = new Date();
        ConstructionSiteResponse response1 = new ConstructionSiteResponseBuilder()
                .id(0)
                .title("Edf. Prime")
                .district("district")
                .address("address")
                .addressComplement("address complement")
                .latitude("latitude")
                .longitude("longitude")
                .state("state")
                .projectImage(photoPrime)
                .beginAt(date)
                .endPredictionAt(date)
                .build();

        ConstructionSiteResponse response2 = new ConstructionSiteResponseBuilder()
                .id(1)
                .title("Edf. Life")
                .district("district")
                .address("address")
                .addressComplement("address complement")
                .latitude("latitude")
                .longitude("longitude")
                .state("state")
                .projectImage(photoPrime)
                .beginAt(date)
                .endPredictionAt(date)
                .build();

        ConstructionSiteResponse response3 = new ConstructionSiteResponseBuilder()
                .id(2)
                .title("Edf. Prime")
                .district("district")
                .address("address")
                .addressComplement("address complement")
                .latitude("latitude")
                .longitude("longitude")
                .state("state")
                .projectImage(photoPrime)
                .beginAt(date)
                .endPredictionAt(date)
                .build();

        list.add(response1);
        list.add(response2);
        list.add(response3);
        return list;
    }
}
