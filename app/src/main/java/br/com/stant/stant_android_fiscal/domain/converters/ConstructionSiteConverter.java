package br.com.stant.stant_android_fiscal.domain.converters;

import java.util.ArrayList;
import java.util.List;

import br.com.stant.stant_android_fiscal.domain.entity.constructionsite.ConstructionSite;
import br.com.stant.stant_android_fiscal.services.constructionsite.dto.ConstructionSiteResponse;

/**
 * Created by denisvieira on 15/05/17.
 */

public class ConstructionSiteConverter {

    public static List<ConstructionSite> convertResponsesToEntities(List<ConstructionSiteResponse> constructionSiteResponses) {
        if(constructionSiteResponses == null) return new ArrayList<>();

        List<ConstructionSite> constructionSites = new ArrayList<>();

        for (ConstructionSiteResponse response : constructionSiteResponses) {
            ConstructionSite constructionSite = convertResponseToEntity(response);
            if(constructionSite!= null)
                constructionSites.add(constructionSite);
        }
        return constructionSites;
    }

    public static ConstructionSite convertResponseToEntity(ConstructionSiteResponse response) {
        if(response == null) return null;

        return new ConstructionSite(response.getId(), response.getTitle(),response.getLatitude(),response.getLongitude(),
                response.getDistrict(), response.getZipCode(), response.getAddress(), response.getAddressComplement(), response.getState(),
                response.getStreetNumber(), response.getProjectImage(),response.getBeginAt(),response.getEndPredictionAt());
    }
}
