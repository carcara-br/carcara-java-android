package br.com.stant.stant_android_fiscal.services;

/**
 * Created by rachidcalazans on 8/8/16.
 */

public class Response {
    private String mUrl;
    private int mCode;
    private String mDetailMessage;

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public int getCode() {
        return mCode;
    }

    public void setCode(int code) {
        this.mCode = code;
    }

    public String getDetailMessage() {
        return mDetailMessage;
    }

    public void setDetailMessage(String detailMessage) {
        this.mDetailMessage = detailMessage;
    }
}
