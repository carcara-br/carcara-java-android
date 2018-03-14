package br.com.stant.stant_android_fiscal.login;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.HashMap;
import java.util.List;

import br.com.stant.stant_android_fiscal.AppPermissions;
import br.com.stant.stant_android_fiscal.R;
import br.com.stant.stant_android_fiscal.databinding.LoginFragBinding;
import br.com.stant.stant_android_fiscal.domain.enums.auth.AuthRemoteErrorCodeEnum;
import br.com.stant.stant_android_fiscal.selectconstructionsite.SelectConstructionSiteActivity;
import br.com.stant.stant_android_fiscal.util.validator.ValidationErrorWrapper;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by denisvieira on 09/05/17.
 */
public class LoginFragment  extends Fragment implements LoginContract.View, Validator.ValidationListener{

    private LoginContract.Presenter mPresenter;
    private LoginFragBinding mLoginFragBinding;
    private Validator mValidator;
    private HashMap<AuthRemoteErrorCodeEnum,String> authRemoteErrorCodeEnumStringHashMap;

    @NotEmpty(sequence = 1, messageResId = R.string.login_frag_saripaar_errors_not_empty)
    @Email(sequence    = 2, messageResId = R.string.login_frag_saripaar_errors_email)
    EditText mEmail;

    @NotEmpty(sequence = 1, messageResId = R.string.login_frag_saripaar_errors_not_empty)
    @Password(sequence = 2, messageResId = R.string.login_frag_saripaar_errors_password, min = 8)
    EditText mPassword;

    public LoginFragment() {}

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLoginFragBinding = DataBindingUtil.inflate(inflater, R.layout.login_frag,container,false);
        mLoginFragBinding.setHandler(this);

        mValidator = new Validator(this);
        mValidator.setValidationListener(this);

        mEmail = mLoginFragBinding.loginFragEmailEditText;
        mPassword = mLoginFragBinding.loginFragPasswordEditText;

        mLoginFragBinding.loginFragOnLoginLoadingProgressBar.getIndeterminateDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);

        setupAuthRemoteErrorCodeEnumStringHashMap();

        return mLoginFragBinding.getRoot();
    }
    @Override
    public void showRemoteRequestLoader() {
        mLoginFragBinding.loginFragJoinTextView.setVisibility(View.GONE);
        mLoginFragBinding.loginFragOnLoginLoadingProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRemoteRequestLoader() {
        mLoginFragBinding.loginFragJoinTextView.setVisibility(View.VISIBLE);
        mLoginFragBinding.loginFragOnLoginLoadingProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setPresenter(@NonNull LoginContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void login(View view) {
        if (AppPermissions.hasPermissions(getActivity())) {
            View viewInputMethod = getActivity().getCurrentFocus();
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(viewInputMethod.getWindowToken(), 0);
            mValidator.validate();
        }else{
            AppPermissions.requestPermissions(getActivity());
        }
    }

    @Override
    public void goToSelectConstructionSite() {
        Intent intent = new Intent(getContext(), SelectConstructionSiteActivity.class);
        startActivity(intent);
    }

    @Override
    public void showLoginErrors(AuthRemoteErrorCodeEnum error) {
        mLoginFragBinding.loginFragErrorAuthTextView.setVisibility(View.GONE);
        String errorMessage = authRemoteErrorCodeEnumStringHashMap.get(error);

        mLoginFragBinding.loginFragErrorAuthTextView.setVisibility(View.VISIBLE);
        mLoginFragBinding.loginFragErrorAuthTextView.setText(errorMessage);
    }

    @Override
    public void showInitTutorial() {

    }

    @Override
    public void onValidationSucceeded() {
        final String email    = mEmail.getText().toString().trim();
        final String password = mPassword.getText().toString().trim();
        mPresenter.login(email, password, generateDeviceId());
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            ValidationErrorWrapper errorWrapper = new ValidationErrorWrapper(error, getContext());
            View view = error.getView();
//            EditText field = (EditText) view.getParent();
//            field.setError(errorWrapper.getFirstErrorMessage());
            System.out.println("onValidationFailed : "+errorWrapper.getFirstErrorMessage());
        }
    }

    private String generateDeviceId() {
        TelephonyManager tManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        return tManager.getDeviceId();
    }

    private void setupAuthRemoteErrorCodeEnumStringHashMap(){
        authRemoteErrorCodeEnumStringHashMap = new HashMap<>();
        authRemoteErrorCodeEnumStringHashMap.put(AuthRemoteErrorCodeEnum.INVALID,getResources().getString(R.string.login_frag_saripaar_errors_invalid_grant));
        authRemoteErrorCodeEnumStringHashMap.put(AuthRemoteErrorCodeEnum.UNAUTHORIZED,getResources().getString(R.string.login_frag_saripaar_errors_invalid_grant));
        authRemoteErrorCodeEnumStringHashMap.put(AuthRemoteErrorCodeEnum.SERVER_UNAVAILABLE,getResources().getString(R.string.login_frag_saripaar_errors_no_network_connection));
    }
}
