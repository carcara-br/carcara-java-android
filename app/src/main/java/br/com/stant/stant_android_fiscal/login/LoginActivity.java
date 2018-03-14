package br.com.stant.stant_android_fiscal.login;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import br.com.stant.stant_android_fiscal.AppPermissions;
import br.com.stant.stant_android_fiscal.InjectionUseCase;
import br.com.stant.stant_android_fiscal.R;
import br.com.stant.stant_android_fiscal.StantFiscalApplication;
import br.com.stant.stant_android_fiscal.domain.entity.user.UserSession;
import br.com.stant.stant_android_fiscal.selectconstructionsite.SelectConstructionSiteActivity;
import br.com.stant.stant_android_fiscal.util.ActivityUtils;

import static br.com.stant.stant_android_fiscal.StantFiscalApplication.getAppContext;

/**
 * Created by denisvieira on 09/05/17.
 */

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_act);

        if (!AppPermissions.hasPermissions(this))
            AppPermissions.requestPermissions(this);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.login_content_frame);

        if (loginFragment == null){
            loginFragment = LoginFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), loginFragment, R.id.login_content_frame);
        }

        new LoginPresenter(
                loginFragment,
                InjectionUseCase.provideGetAuth(getApplicationContext()),
                getAppContext());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        verifySessionToCurrentUser();
    }

    @Override
    protected void onResume() {
        super.onResume();
        verifySessionToCurrentUser();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void verifySessionToCurrentUser() {
        UserSession currentUser = StantFiscalApplication.currentUserSession();
        if (currentUser == null) return;

        finishAffinity();
        Intent i = new Intent(this, SelectConstructionSiteActivity.class);
        startActivity(i);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == AppPermissions.PERMISSIONS_CODE) {
            for (int i = 0, len = permissions.length; i < len; i++) {
                String permission = permissions[i];
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    boolean showRationale = shouldShowRequestPermissionRationale(permission);
                    if (!showRationale) {
                        AppPermissions.setDoNotAskAsChecked();
                    }
                }
            }
        }
    }
}
