package br.com.stant.stant_android_fiscal.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public abstract class CallBuilder<T extends List> implements Call<T> {

    protected final Retrofit mRetrofit;
    protected boolean failure;
    protected Integer perPage;
    protected Integer page;


    public CallBuilder(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    public CallBuilder(Retrofit retrofit, Integer perPage, boolean failure) {
        mRetrofit = retrofit;
        this.perPage = perPage;
        this.failure = failure;
    }

    protected abstract T getList();

    protected void setPage(Integer page) {
        this.page = page;
    }

    @Override
    public Response<T> execute() throws IOException {
        return null;
    }

    @Override
    public void enqueue(Callback<T> callback) {

        if (this.failure) {
            callback.onFailure(null, new Throwable("Request Fail"));
            return;
        } else {

            T resultResponses = (T) new ArrayList<>();
            Headers.Builder builder = new Headers.Builder();
            if (perPage == null) {
                resultResponses.addAll(getList());
            } else {
                T responses = getList();
                int initial = (page - 1) * perPage;
                int finalPosition = perPage * page;
                if (finalPosition <= responses.size()) {
                    responses = (T) responses.subList(initial, finalPosition);
                } else {
                    responses = (T) responses.subList(initial, responses.size());
                }
                if (finalPosition <= getList().size()) {
                    builder.add("Next-page", String.valueOf(page + 1));
                }

                resultResponses.addAll(responses);
            }
            Headers headers = builder.build();
            Response<T> success = Response.success(resultResponses, headers);
            callback.onResponse(null, success);
        }
    }

    @Override
    public boolean isExecuted() {
        return false;
    }

    @Override
    public void cancel() {

    }

    @Override
    public boolean isCanceled() {
        return false;
    }

    @Override
    public Call<T> clone() {
        return null;
    }

    @Override
    public Request request() {
        return null;
    }
}
