package br.com.stant.stant_android_fiscal.domain.entity.session;

import com.github.brunodles.simplepreferences.lib.Property;

import br.com.stant.stant_android_fiscal.domain.converters.EntityConverter;
import br.com.stant.stant_android_fiscal.domain.entity.user.Entity;
import br.com.stant.stant_android_fiscal.services.auth.dto.UserResponse;


/**
 * Created by denisvieira on 10/05/17.
 */
public class CurrentUserInfo {

    @Property private int id;
    @Property private Entity entity;
    @Property private String name;
    @Property private String imageProfile;

    public CurrentUserInfo(UserResponse user) {
        this.id           = user.getId();
        this.entity       = EntityConverter.convertFromResponse(user.getEntity());
        this.name         = user.getName();
        this.imageProfile = user.getImageProfile();
    }

    public CurrentUserInfo() {}

    public boolean hasData() {
        return getEntity() != null && getName() != null && !getName().equals("");
    }

    public String getName() {
        return name;
    }

    public String getImageProfile() {
        if (imageProfile != null && imageProfile.equals("null"))
            return null;

        return imageProfile;
    }

    public boolean hasImageProfile() {
        return getImageProfile() != null && !getImageProfile().equals("");
    }

    public int getId() {
        return id;
    }

    public Entity getEntity() {
        return entity;
    }

}
