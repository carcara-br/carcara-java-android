package br.com.stant.stant_android_fiscal.domain.enums.auth;

/**
 * Created by denisvieira on 18/05/17.
 */

public enum AuthRemoteErrorCodeEnum {

    UNAUTHORIZED(401), INVALID(400), SERVER_UNAVAILABLE(500);

    private final int mValue;

    AuthRemoteErrorCodeEnum(int value) {
        mValue = value;
    }

    public int getValue(){
        return mValue;
    }


    public static AuthRemoteErrorCodeEnum getEnum(int value) {
        switch (value){
            case 401:
                return AuthRemoteErrorCodeEnum.UNAUTHORIZED;
            case 400:
                return AuthRemoteErrorCodeEnum.INVALID;
            case 500:
                return AuthRemoteErrorCodeEnum.SERVER_UNAVAILABLE;
        }
        return null;
    }
}
