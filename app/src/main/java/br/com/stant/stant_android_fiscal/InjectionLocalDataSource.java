package br.com.stant.stant_android_fiscal;

import android.content.Context;

import com.github.brunodles.simplepreferences.lib.DaoPreferences;

import br.com.stant.stant_android_fiscal.services.session.SessionLocalDataSource;
import br.com.stant.stant_android_fiscal.services.session.SessionLocalDataSourceImpl;

/**
 * Created by denisvieira on 12/05/17.
 */

public class InjectionLocalDataSource {

    public static SessionLocalDataSource provideSessionLocalDataSource(Context context) {
        DaoPreferences daoPreferences = new DaoPreferences(context);

        return SessionLocalDataSourceImpl.getInstance(daoPreferences);
    }
}
