package br.com.stant.stant_android_fiscal.mainmenu;

import android.support.annotation.NonNull;


import br.com.stant.stant_android_fiscal.mainmenu.domain.usecases.DestroyUserSession;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by denisvieira on 13/05/17.
 */

public class MainMenuPresenter implements MainMenuContract.Presenter {
    private final MainMenuContract.View mMainMenuView;
    private final DestroyUserSession mDestroyUserSession;

    public MainMenuPresenter(@NonNull MainMenuContract.View constructionSiteView,
                             @NonNull DestroyUserSession destroyUserSession) {
        mMainMenuView = checkNotNull(constructionSiteView);
        mDestroyUserSession     = checkNotNull(destroyUserSession);

        mMainMenuView.setPresenter(this);
    }

    @Override
    public void logout() {
        mDestroyUserSession.destroy();
    }
}
