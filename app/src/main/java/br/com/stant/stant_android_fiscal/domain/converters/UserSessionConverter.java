package br.com.stant.stant_android_fiscal.domain.converters;


import br.com.stant.stant_android_fiscal.domain.entity.user.Entity;
import br.com.stant.stant_android_fiscal.domain.entity.user.UserSession;
import br.com.stant.stant_android_fiscal.services.auth.dto.AuthResponse;
import br.com.stant.stant_android_fiscal.services.auth.dto.UserResponse;
import br.com.stant.stant_android_fiscal.services.session.dto.UserSessionEntry;

/**
 * Created by rachidcalazans on 3/13/17.
 */

public class UserSessionConverter {

    public static UserSession convertFromResponse(AuthResponse authResponse, String deviceId) {
        if (authResponse == null) return null;

        String token     = authResponse.getAccessToken();
        String tokenType = authResponse.getTokenType();
        String createdAt = authResponse.getCreatedAt();

       UserResponse userResponse = authResponse.getUser();

        int id              = userResponse.getId();
        Entity entity       = EntityConverter.convertFromResponse(userResponse.getEntity());
        String name         = userResponse.getName();
        String imageProfile = userResponse.getImageProfile();

        return new UserSession(token, tokenType, id, entity, name, imageProfile,
                createdAt, deviceId);
    }

    public static UserSession convertFromEntry(UserSessionEntry entry) {
        if (entry == null) return null;

        return new UserSession(entry.token, entry.token_type, entry.id, EntityConverter.convertFromEntry(entry.entity),
                entry.name, entry.image_profile, entry.created_at, entry.device_id);
    }

    public static UserSessionEntry convertFromEntity(UserSession userSession) {
        if (userSession == null) return null;

        UserSessionEntry entry = new UserSessionEntry();
        entry.token         = userSession.getToken();
        entry.token_type    = userSession.getTokenType();
        entry.id            = userSession.getUserId();
        entry.entity        = EntityConverter.convertEntityToEntry(userSession.getEntity());
        entry.name          = userSession.getName();
        entry.image_profile = userSession.getImageProfile();
        entry.created_at    = userSession.getCreatedAt();
        entry.device_id     = userSession.getDeviceId();
        return entry;
    }
}
