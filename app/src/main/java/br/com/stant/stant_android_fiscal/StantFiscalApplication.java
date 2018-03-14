package br.com.stant.stant_android_fiscal;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import br.com.stant.stant_android_fiscal.domain.entity.user.UserSession;
import br.com.stant.stant_android_fiscal.services.session.SessionLocalDataSource;

/**
 * Created by denisvieira on 9/05/16.
 */
public class StantFiscalApplication extends Application {

    private static Context mContext;
    private static SessionLocalDataSource sessionLocalDataSource;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

        MultiDex.install(mContext);
        sessionLocalDataSource = InjectionLocalDataSource.provideSessionLocalDataSource(mContext);
    }

    public static Context getAppContext() {
        return mContext;
    }

    public static UserSession currentUserSession() {
        return  sessionLocalDataSource.getUserSession();
    }
}
