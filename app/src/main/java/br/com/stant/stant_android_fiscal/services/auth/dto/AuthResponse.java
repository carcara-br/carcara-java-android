package br.com.stant.stant_android_fiscal.services.auth.dto;

/**
 * Created by denisvieira on 10/05/17.
 */

public final class AuthResponse {
    private final String access_token;
    private final String token_type;
    private final String created_at;
    private final UserResponse user;

    public AuthResponse(String access_token, String token_type, String created_at, UserResponse user) {
        this.access_token = access_token;
        this.token_type   = token_type;
        this.created_at   = created_at;
        this.user         = user;
    }

    public String getAccessToken() {
        return access_token;
    }

    public String getAccessTokenAsHeader() {
        return "Bearer " + getAccessToken();
    }

    public String getTokenType() {
        return token_type;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public UserResponse getUser() { return user; }
}
