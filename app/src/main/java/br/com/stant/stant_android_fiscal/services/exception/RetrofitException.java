package br.com.stant.stant_android_fiscal.services.exception;

import java.io.IOException;
import java.lang.annotation.Annotation;

import br.com.stant.stant_android_fiscal.services.RemoteErrorResponse;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by rachidcalazans on 8/8/16.
 */

public class RetrofitException extends RuntimeException {
    public static RetrofitException httpError(String url, Response response, Retrofit retrofit) {
        String message = response.code() + " " + response.message();
        return new RetrofitException(message, url, response, Kind.HTTP, null, retrofit);
    }

    public static RetrofitException networkError(IOException exception) {
        return new RetrofitException(exception.getMessage(), null, null, Kind.NETWORK, exception, null);
    }

    public static RetrofitException unexpectedError(Throwable exception) {
        return new RetrofitException(exception.getMessage(), null, null, Kind.UNEXPECTED, exception, null);
    }

    /** Identifies the event kind which triggered a {@link RetrofitException}. */
    public enum Kind {
        /** An {@link IOException} occurred while communicating to the server. */
        NETWORK,
        /** A non-200 HTTP status code was received from the server. */
        HTTP,
        /**
         * An internal error occurred while attempting to execute a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
        UNEXPECTED
    }

    private final String url;
    private final Response response;
    private final Kind kind;
    private final Retrofit retrofit;

    RetrofitException(String message, String url, Response response, Kind kind, Throwable exception, Retrofit retrofit) {
        super(message, exception);
        this.url = url;
        this.response = response;
        this.kind = kind;
        this.retrofit = retrofit;
    }

    /** The request URL which produced the error. */
    public String getUrl() {
        return url;
    }

    /** RemoteErrorResponse object containing status code, headers, body, etc. */
    public Response getResponse() {
        return response;
    }

    /** The event kind which triggered this error. */
    public Kind getKind() {
        return kind;
    }

    /** The Retrofit this request was executed on */
    public Retrofit getRetrofit() {
        return retrofit;
    }

    /**
     * HTTP response body converted to specified {@code type}. {@code null} if there is no
     * response.
     *
     */
    public <T extends RemoteErrorResponse> T getErrorBodyAs(Class<T> type) {
        if (getResponse() == null || getResponse().errorBody() == null) {
            return buildRemoteResponseByException(type);
        }

        ResponseBody errorBody = getResponse().errorBody();
        Converter<ResponseBody, T> converter = getRetrofit().responseBodyConverter(type, new Annotation[0]);
        T errorInstance = null;
        RemoteErrorResponse remoteRemoteErrorResponse = null;

        try {
            errorInstance = converter.convert(errorBody);
            remoteRemoteErrorResponse = errorInstance;
            remoteRemoteErrorResponse.setCode(getResponse().code());
            remoteRemoteErrorResponse.setDetailMessage(getMessage());
        } catch (IOException e) {
            try {
                errorInstance = type.newInstance();
                remoteRemoteErrorResponse = errorInstance;
                remoteRemoteErrorResponse.setDetailMessage("Error when tried to convert body response");
                errorInstance = (T) remoteRemoteErrorResponse;
            } catch (InstantiationException e1) {
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            }
        }

        remoteRemoteErrorResponse.setUrl(getUrl());

        return errorInstance;
    }

    private <T extends RemoteErrorResponse> T buildRemoteResponseByException(Class<T> type) {
        if (typeSuperClassNameIsRemoteErrorResponseName(type) || typeNameIsRemoteErrorResponseName(type)) {
            try {
                T errorInstance = type.newInstance();
                RemoteErrorResponse remoteRemoteErrorResponse = errorInstance;
                remoteRemoteErrorResponse.setUrl(getUrl());

                if (getKind() == Kind.NETWORK) {
                    int INTERNAL_ERROR = 500;
                    remoteRemoteErrorResponse.setCode(INTERNAL_ERROR);
                    remoteRemoteErrorResponse.setDetailMessage("Server Unavailable");
                }
                if (getKind() == Kind.UNEXPECTED) {
                    remoteRemoteErrorResponse.setDetailMessage("Unknown Error");
                }

                return errorInstance;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private <T extends RemoteErrorResponse> boolean typeNameIsRemoteErrorResponseName(Class<T> type) {
        return type.getName().equals(RemoteErrorResponse.class.getName());
    }

    private <T extends RemoteErrorResponse> boolean typeSuperClassNameIsRemoteErrorResponseName(Class<T> type) {
        return type.getSuperclass().getName().equals(RemoteErrorResponse.class.getName());
    }
}
