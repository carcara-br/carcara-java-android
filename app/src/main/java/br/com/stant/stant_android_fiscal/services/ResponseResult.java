package br.com.stant.stant_android_fiscal.services;

import java.util.List;

import okhttp3.Headers;

/**
 * Created by rachidcalazans on 1/27/17.
 */

public class ResponseResult<T extends List> {
    public interface NextPageCallback<B> {
        void nextPage(B body);
    }

    public interface NoNextPageCallback<B> {
        void noNextPage(B body);
    }

    private retrofit2.Response response;
    private Headers headers;
    private T  body;
    private String currentPage;
    private String nextPage;
    private NextPageCallback<T> nextPageCallback;
    private NoNextPageCallback<T> noNextPageCallback;

    public ResponseResult() {
        this.currentPage        = "1";
        this.nextPageCallback   = b -> {};
        this.noNextPageCallback = b -> {};
    }

    public ResponseResult<T> responseResult(retrofit2.Response<T> response) {
        this.response = response;
        this.headers  = this.response.headers();
        nextPage      = getNextPageFromHeaders();
        return this;
    }

    public ResponseResult<T> nextPage(NextPageCallback<T> nextPageCallback) {
        this.nextPageCallback = nextPageCallback;
        return this;
    }

    public ResponseResult<T> noNextPage(NoNextPageCallback<T> noNextPageCallback) {
        this.noNextPageCallback = noNextPageCallback;
        return this;
    }

    public void execute() {
        addOnBody();

        if (nextPage == null) {
            noNextPageCallback.noNextPage(getBody());
        } else {
            currentPage = nextPage;
            nextPageCallback.nextPage(getBody());
        }
    }

    public Integer currentPage() {
        return Integer.parseInt(currentPage);
    }

    private T getBody() {
        return body;
    }

    private void addOnBody() {
        if (getBody() == null) {
            this.body = getBodyFromResponse();
        } else {
            getBody().addAll(getBodyFromResponse());
        }
    }

    private T getBodyFromResponse() {
        return (T) this.response.body();
    }

    private String getNextPageFromHeaders() {
        return headers.get("Next-page");
    }
}
