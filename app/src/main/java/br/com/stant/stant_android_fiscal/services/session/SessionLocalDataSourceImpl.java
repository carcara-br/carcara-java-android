package br.com.stant.stant_android_fiscal.services.session;

import com.github.brunodles.simplepreferences.lib.DaoPreferences;

import br.com.stant.stant_android_fiscal.domain.converters.UserSessionConverter;
import br.com.stant.stant_android_fiscal.domain.entity.user.UserSession;
import br.com.stant.stant_android_fiscal.services.session.dto.EntityEntry;
import br.com.stant.stant_android_fiscal.services.session.dto.UserSessionEntry;


/**
 * Created by rachidcalazans on 3/13/17.
 */

public class SessionLocalDataSourceImpl implements SessionLocalDataSource {

    private static SessionLocalDataSourceImpl INSTANCE;
    private final DaoPreferences mDaoPreferences;

    public SessionLocalDataSourceImpl(DaoPreferences daoPreferences) {
        mDaoPreferences = daoPreferences;
    }

    public static SessionLocalDataSourceImpl getInstance(DaoPreferences daoPreferences) {
        if (INSTANCE == null)
            INSTANCE = new SessionLocalDataSourceImpl(daoPreferences);

        return INSTANCE;
    }

    @Override
    public UserSession save(UserSession userSession) {
        UserSessionEntry userSessionEntry = UserSessionConverter.convertFromEntity(userSession);

        mDaoPreferences.commit(userSessionEntry, UserSessionEntry.PREFERENCE_KEY);
        mDaoPreferences.commit(userSessionEntry.entity, EntityEntry.PREFERENCE_KEY);

        return userSession;
    }

    @Override
    public UserSession getUserSession() {
        UserSessionEntry userSessionEntry = new UserSessionEntry();
        EntityEntry entityEntry = new EntityEntry();
        UserSessionEntry load = mDaoPreferences.load(userSessionEntry, UserSessionEntry.PREFERENCE_KEY);
        load.entity = mDaoPreferences.load(entityEntry, EntityEntry.PREFERENCE_KEY);

        if (load.name == null) return null;

        return UserSessionConverter.convertFromEntry(load);
    }

    @Override
    public void destroy() {
        mDaoPreferences.clear(UserSessionEntry.PREFERENCE_KEY);
    }
}
