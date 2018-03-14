package br.com.stant.stant_android_fiscal;

import android.support.v4.app.FragmentActivity;

public class StantBackground {

    public interface BackGround {
        void run();
    }

    public static void runOnBackground(BackGround backGround) {
        new Thread(() -> {
            backGround.run();
        }).start();
    }

    public static void runOnUiThread(BackGround backGround, FragmentActivity fragmentActivity) {
        fragmentActivity.runOnUiThread(() -> backGround.run());
    }
}
