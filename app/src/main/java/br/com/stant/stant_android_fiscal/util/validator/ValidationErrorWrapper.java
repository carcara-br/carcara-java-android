package br.com.stant.stant_android_fiscal.util.validator;

import android.content.Context;

import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.ValidationError;

import java.util.List;

/**
 * Created by rachidcalazans on 8/12/16.
 */

public class ValidationErrorWrapper {
    private ValidationError mValidationError;
    private Context mContext;

    public ValidationErrorWrapper(ValidationError validationError, Context mContext) {
        this.mValidationError = validationError;
        this.mContext         = mContext;
    }

    public String getFirstErrorMessage() {
        List<Rule> failedRules = mValidationError.getFailedRules();
        if (failedRules.isEmpty())
            return null;

        return failedRules.get(0).getMessage(mContext);
    }
}
