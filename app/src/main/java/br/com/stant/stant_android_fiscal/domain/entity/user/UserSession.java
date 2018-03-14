package br.com.stant.stant_android_fiscal.domain.entity.user;

/**
 * Created by rachidcalazans on 3/10/17.
 */

public class UserSession {
    private final String mToken;
    private final String mTokenType;
    private final int mId;
    private final String mName;
    private final Entity mEntity;
    private final String mImageProfile;
    private final String mCreatedAt;
    private final String mDeviceId;

    public UserSession(String token, String tokenType, int id, Entity entity, String name,
                       String imageProfile, String createdAt, String deviceId) {
        this.mToken        = token;
        this.mTokenType    = tokenType;
        this.mId           = id;
        this.mEntity       = entity;
        this.mName         = name;
        this.mImageProfile = imageProfile;
        this.mCreatedAt    = createdAt;
        this.mDeviceId     = deviceId;
    }

    public String getToken() {
        return mToken;
    }

    public String getTokenType() {
        return mTokenType;
    }

    public int getUserId() {
        return mId;
    }

    public Entity getEntity() {
        return mEntity;
    }

    public String getName() {
        return mName;
    }

    public String getImageProfile() {
        return mImageProfile;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public String getAccessTokenAsHeader() {
        return getTokenType() + " " + getToken();
    }

    public String getDeviceId() {
        return mDeviceId;
    }
}
