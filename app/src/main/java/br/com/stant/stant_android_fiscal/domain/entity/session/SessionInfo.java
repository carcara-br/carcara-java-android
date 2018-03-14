package br.com.stant.stant_android_fiscal.domain.entity.session;

import com.github.brunodles.simplepreferences.lib.Property;

import br.com.stant.stant_android_fiscal.services.auth.dto.AuthResponse;


/**
 * Created by denisvieira on 10/05/17.
 */
public class SessionInfo {
    @Property private String accessToken;
    @Property private String deviceId;
    @Property private String tokenType;
    @Property private String createdAt;

    public SessionInfo(AuthResponse response, String deviceId) {
        this.accessToken = response.getAccessToken();
        this.tokenType   = response.getTokenType();
        this.createdAt   = response.getCreatedAt();
        this.deviceId    = deviceId;
    }

    public SessionInfo() {}

    public boolean hasData() {
        return getAccessToken() != null && !getAccessToken().equals("") &&
                getDeviceId() != null && !getDeviceId().equals("") &&
                getAccessToken() != null && !getAccessToken().equals("");
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessTokenAsHeader() {
        return "Bearer " + getAccessToken();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
