package br.com.stant.stant_android_fiscal;

/**
 * Created by root on 19/07/16.
 */
public interface BaseView<T extends BasePresenter> {
    void showRemoteRequestLoader();
    void hideRemoteRequestLoader();
    void setPresenter(T presenter);
}
