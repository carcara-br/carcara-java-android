package br.com.stant.stant_android_fiscal.mainmenu.domain.usecases;


import android.support.annotation.NonNull;

import br.com.stant.stant_android_fiscal.services.session.SessionLocalDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by denisvieira on 13/05/17.
 */

public class DestroyUserSession {

    private final SessionLocalDataSource sessionLocalDataSource;

    public DestroyUserSession(@NonNull SessionLocalDataSource sessionLocalDataSource) {
        this.sessionLocalDataSource = checkNotNull(sessionLocalDataSource);
    }

    public void destroy() {
        sessionLocalDataSource.destroy();
    }
}
