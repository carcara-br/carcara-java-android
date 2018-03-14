package br.com.stant.stant_android_fiscal.util.bindings;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.widget.TextView;

/**
 * Created by denisvieira on 18/01/17.
 */

public class TextBinding {

    @BindingAdapter("android:text")
    public static void setText(TextView view, Integer value) {
        if(value == null)
            view.setText("--");
        else
            view.setText(String.valueOf(value));
    }

    @BindingAdapter("android:text")
    public static void setText(TextView view, Float value) {
        if(value == null)
            view.setText("--");
        else
            view.setText(String.valueOf(value));
    }

    @InverseBindingAdapter(attribute = "android:text")
    public static int getText(TextView view) {
        return Integer.parseInt(view.getText().toString());
    }

    @BindingAdapter({"textResource"})
    public static void setStringResource(TextView textView, Integer resourceId){
        int stringId = Integer.valueOf(resourceId);
        textView.setText(textView.getResources().getString(stringId));
    }

    @BindingAdapter({"textColor"})
    public static void setTextColor(TextView textView, Integer resourceId){
        int colorId = Integer.valueOf(resourceId);
        textView.setTextColor(textView.getResources().getColor(colorId));
    }
}
