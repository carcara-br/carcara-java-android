package br.com.stant.stant_android_fiscal.login;

import br.com.stant.stant_android_fiscal.BasePresenter;
import br.com.stant.stant_android_fiscal.BaseView;
import br.com.stant.stant_android_fiscal.domain.enums.auth.AuthRemoteErrorCodeEnum;

/**
 * Created by denisvieira on 09/05/17.
 */

public interface LoginContract {

    interface View extends BaseView<Presenter> {
        void login(android.view.View view);
        void goToSelectConstructionSite();
        void showLoginErrors(AuthRemoteErrorCodeEnum authRemoteErrorCodeEnum);
        void showInitTutorial();
    }

    interface Presenter extends BasePresenter {
        void login(String username, String password, String deviceId);
    }
}
