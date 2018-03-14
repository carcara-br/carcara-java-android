package br.com.stant.stant_android_fiscal.mainmenu;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.util.LayoutDirection;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import br.com.stant.stant_android_fiscal.R;
import br.com.stant.stant_android_fiscal.StantFiscalApplication;
import br.com.stant.stant_android_fiscal.databinding.LogoutConfirmationDialogBinding;
import br.com.stant.stant_android_fiscal.databinding.MainMenuDialogBinding;
import br.com.stant.stant_android_fiscal.domain.entity.user.UserSession;
import br.com.stant.stant_android_fiscal.login.LoginActivity;

/**
 * Created by denisvieira on 13/05/17.
 */
public class MainMenu implements MainMenuContract.View {

    private final Context mContext;
    private final Fragment mFragment;
    private final Activity mActivity;
    private UserSession mCurrentUserSession;
    private Dialog mMainMenuDialog;
    private Dialog mConfirmationLogoutDialog;
    private MainMenuContract.Presenter mMainMenuPresenter;
    private MainMenuDialogBinding mMainMenuDialogBinding;
    private LogoutConfirmationDialogBinding mLogoutConfirmationDialogBinding;

    public MainMenu(Context context, Fragment fragment) {
        mContext            = context;
        mFragment           = fragment;
        mActivity           = mFragment.getActivity();
        mCurrentUserSession = StantFiscalApplication.currentUserSession();

        mainMenuConfiguration();

        mMainMenuDialogBinding.setUser(mCurrentUserSession);
        mMainMenuDialogBinding.setHandler(this);
    }

    public MainMenu(Context context, Activity activity) {
        mContext            = context;
        mFragment           = null;
        mActivity           = activity;
        mCurrentUserSession = StantFiscalApplication.currentUserSession();

        mainMenuConfiguration();

        mMainMenuDialogBinding.setUser(mCurrentUserSession);
        mMainMenuDialogBinding.setHandler(this);
    }

    @Override
    public void show(){
        mMainMenuDialog.show();
    }

    @Override
    public void hideMenu(View view) {
        mMainMenuDialog.dismiss();
    }

    @Override
    public void mainMenuConfiguration() {

        mMainMenuDialog = new Dialog(mContext, R.style.DialogTheme);
        mMainMenuDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.main_menu_dialog, null, false);
        mMainMenuDialogBinding.setHandler(this);
        mMainMenuDialog.setContentView(mMainMenuDialogBinding.getRoot());

        ViewGroup.LayoutParams mainMenuParams = mMainMenuDialogBinding.mainMenuGeneralContainerLinearLayout.getLayoutParams();
        mainMenuParams.width = mContext.getResources().getDimensionPixelSize(R.dimen.main_menu_dialog_width);
        mainMenuParams.resolveLayoutDirection(LayoutDirection.RTL);
        mainMenuParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mMainMenuDialogBinding.mainMenuGeneralContainerLinearLayout.setLayoutParams(mainMenuParams);

        Window window = mMainMenuDialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.width = mContext.getResources().getDimensionPixelSize(R.dimen.main_menu_dialog_width);
        wlp.gravity = Gravity.TOP | Gravity.RIGHT;
        wlp.horizontalMargin = 0.023f;
        window.setAttributes(wlp);

        mConfirmationLogoutDialog = new Dialog(mContext);
        mLogoutConfirmationDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext),R.layout.logout_confirmation_dialog, null, false);
        mLogoutConfirmationDialogBinding.setHandler(this);
        mConfirmationLogoutDialog.setContentView(mLogoutConfirmationDialogBinding.getRoot());
        mConfirmationLogoutDialog.setTitle(R.string.logout_confirmation_title);

    }

    @Override
    public void cancelLogoutListener(View view) {
        mConfirmationLogoutDialog.dismiss();
    }

    @Override
    public void confirmLogoutListener(View view) {
        mMainMenuPresenter.logout();
        mActivity.finishAffinity();

        Intent closeActivity = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(closeActivity);
    }

    @Override
    public void clickToCloseListener(View view) {
         mMainMenuDialog.dismiss();
    }

    @Override
    public void clickOnLogoutOptionListener(View view) {
        mConfirmationLogoutDialog.show();
    }

    @Override
    public void showRemoteRequestLoader() {

    }

    @Override
    public void hideRemoteRequestLoader() {

    }

    @Override
    public void setPresenter(MainMenuContract.Presenter presenter) {
        mMainMenuPresenter = presenter;
    }
}
