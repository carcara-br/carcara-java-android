package br.com.stant.stant_android_fiscal.services.session;


import br.com.stant.stant_android_fiscal.domain.entity.user.UserSession;

/**
 * Created by rachidcalazans on 3/10/17.
 */

public interface SessionLocalDataSource {

    UserSession save(UserSession userSession);

    UserSession getUserSession();

    void destroy();

}
