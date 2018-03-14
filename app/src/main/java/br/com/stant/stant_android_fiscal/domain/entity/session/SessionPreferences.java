package br.com.stant.stant_android_fiscal.domain.entity.session;

import android.content.Context;

import com.github.brunodles.simplepreferences.lib.DaoPreferences;

import br.com.stant.stant_android_fiscal.services.auth.dto.AuthResponse;

/**
 * Created by denisvieira on 10/05/17.
 */
public class SessionPreferences  {
    private static SessionPreferences INSTANCE = null;
    private final DaoPreferences daoPreferences;

    private Context context;
    private SessionInfo sessionInfo = null;
    private CurrentUserInfo currentUserInfo = null;

    public static SessionPreferences getInstance(Context context) {
        if(INSTANCE ==  null) {
            INSTANCE = new SessionPreferences(context);
        }

        return INSTANCE;
    }

    private SessionPreferences(Context context) {
        this.context   = context;
        daoPreferences = new DaoPreferences(this.context);

        SessionInfo sessionInfo = new SessionInfo();
        daoPreferences.load(sessionInfo, "sessionInfo");

        CurrentUserInfo currentUserInfo = new CurrentUserInfo();
        daoPreferences.load(currentUserInfo, "currentUser");

        if (sessionInfo.hasData())
            this.sessionInfo = sessionInfo;

        if (currentUserInfo.hasData())
            this.currentUserInfo = currentUserInfo;

    }

    public SessionPreferences setPreferences(AuthResponse response, String deviceId) {
        mapResponse(response, deviceId);

        daoPreferences.commit(this.sessionInfo, "sessionInfo");
        daoPreferences.commit(this.currentUserInfo, "currentUser");

        return this;
    }

    public static CurrentUserInfo currentUserInfo(Context context) {
        return SessionPreferences.getInstance(context).getCurrentUserInfo();
    }

    public SessionInfo getSessionInfo() {
        if (sessionInfo == null)
            return null;

        daoPreferences.load(sessionInfo, "sessionInfo");
        return sessionInfo;
    }

    public CurrentUserInfo getCurrentUserInfo() {
        if (currentUserInfo == null)
            return null;

        daoPreferences.load(currentUserInfo, "currentUser");
        return currentUserInfo;
    }

    public void clearSessionPreferences() {
        daoPreferences.clear("currentUser");
        daoPreferences.clear("sessionInfo");
        this.currentUserInfo = null;
        this.sessionInfo = null;
    }

    private void mapResponse(AuthResponse response, String deviceId) {
        this.sessionInfo = new SessionInfo(response, deviceId);
        this.currentUserInfo = new CurrentUserInfo(response.getUser());
    }
}
