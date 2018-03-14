package br.com.stant.stant_android_fiscal.services.session.dto;

import br.com.stant.stant_android_fiscal.domain.entity.user.Entity;

/**
 * Created by rachidcalazans on 3/13/17.
 */

public class UserSessionEntryBuilder {

    private String mToken;
    private String mTokenType;
    private int mId;
    private String mName;
    private EntityEntry mEntity;
    private String mImageProfile;
    private String mCreatedAt;

    public UserSessionEntryBuilder() {
        this.mToken        = null;
        this.mTokenType    = "bearer";
        this.mId           = 0;
        this.mName         = null;
        this.mEntity       = null;
        this.mImageProfile = null;
        this.mCreatedAt    = null;
    }

    public UserSessionEntry build() {
        UserSessionEntry entry = new UserSessionEntry();

        entry.token         = mToken;
        entry.token_type    = mTokenType;
        entry.id            = mId;
        entry.entity        = mEntity;
        entry.name          = mName;
        entry.image_profile = mImageProfile;
        entry.created_at    = mCreatedAt;

        return entry;
    }

    public UserSessionEntryBuilder token(String token) {
        mToken = token;
        return this;
    }

    public UserSessionEntryBuilder tokenType(String tokenType) {
        mTokenType = tokenType;
        return this;
    }

    public UserSessionEntryBuilder id(int guid) {
        mId = guid;
        return this;
    }

    public UserSessionEntryBuilder name(String name) {
        mName = name;
        return this;
    }

    public UserSessionEntryBuilder entity(EntityEntry entityEntry) {
        mEntity = entityEntry;
        return this;
    }

    public UserSessionEntryBuilder imageProfile(String imageProfile) {
        mImageProfile = imageProfile;
        return this;
    }

    public UserSessionEntryBuilder createdAt(String createdAt) {
        mCreatedAt = createdAt;
        return this;
    }
}
