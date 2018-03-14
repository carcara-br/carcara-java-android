package br.com.stant.stant_android_fiscal.util.bindings;

import android.databinding.BindingAdapter;
import android.view.View;

/**
 * Created by denisvieira on 20/01/17.
 */

public class BackgroundBindings {

    @BindingAdapter({"backgroundColor"})
    public static void setBackgroundColor(View view, Integer resourceId){
        int colorId = Integer.valueOf(resourceId);
        view.setBackgroundColor(view.getResources().getColor(colorId));
    }
}
