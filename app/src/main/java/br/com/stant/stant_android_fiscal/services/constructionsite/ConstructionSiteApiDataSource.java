package br.com.stant.stant_android_fiscal.services.constructionsite;

import java.util.List;

import br.com.stant.stant_android_fiscal.services.constructionsite.dto.ConstructionSiteResponse;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by rachidcalazans on 9/12/16.
 */
public interface ConstructionSiteApiDataSource {

    @GET("api/v1/construction_sites/from_entity")
    Observable<List<ConstructionSiteResponse>> constructionSites(@Header("Authorization") String accessToken, @Query("entity_id")int entity_id);

}
