package br.com.stant.stant_android_fiscal.mainmenu;

import br.com.stant.stant_android_fiscal.BasePresenter;
import br.com.stant.stant_android_fiscal.BaseView;

/**
 * Created by denisvieira on 13/05/17.
 */

public interface MainMenuContract {

    interface View extends BaseView<Presenter> {
        void mainMenuConfiguration();
        void show();
        void hideMenu(android.view.View view);
        void cancelLogoutListener(android.view.View view);
        void confirmLogoutListener(android.view.View view);
        void clickToCloseListener(android.view.View view);
        void clickOnLogoutOptionListener(android.view.View view);
    }

    interface Presenter extends BasePresenter {
        void logout();
    }
}
