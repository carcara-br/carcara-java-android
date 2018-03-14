package br.com.stant.stant_android_fiscal.util;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by gabrielrosa on 13/02/17.
 */

public class MetricUtil {

    public static int dpToPx(int dp, Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
}
