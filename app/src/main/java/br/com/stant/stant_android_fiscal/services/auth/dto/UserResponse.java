package br.com.stant.stant_android_fiscal.services.auth.dto;

/**
 * Created by denisvieira on 10/05/17.
 */

public final class UserResponse {
    private final int id;
    private final String name;
    private final String image_profile;
    private final EntityResponse entity;

    public UserResponse(int guid, String name, String image_profile, EntityResponse entity) {
        this.id            = guid;
        this.name          = name;
        this.image_profile = image_profile;
        this.entity        = entity;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public String getImageProfile() { return image_profile; }

    public EntityResponse getEntity() {
        return entity;
    }
}
