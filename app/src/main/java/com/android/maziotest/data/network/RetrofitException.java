package com.android.maziotest.data.network;


import android.support.annotation.NonNull;
import com.android.maziotest.data.model.CommonResponseModel;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;


public class RetrofitException extends RuntimeException {
    private final String url;
    private final Response response;
    @NonNull
    private final Kind kind;
    private final Retrofit retrofit;
    private CommonResponseModel commonResponseModel;

    RetrofitException(String message, String url, Response response, Kind kind, Throwable exception, Retrofit retrofit) {
        super(message, exception);
        this.url = url;
        this.response = response;
        this.kind = kind;
        this.retrofit = retrofit;
    }

    public static RetrofitException httpError(String url, Response response, Retrofit retrofit) {
        String message = null;
        if (response != null && response.message() != null) {
            message = response.code() + " " + response.message();
        }
        return new RetrofitException(message, url, response, Kind.HTTP, null, retrofit);
    }

    public static RetrofitException networkError(IOException exception) {
        return new RetrofitException(exception.getMessage(), null, null, Kind.NETWORK, exception, null);
    }

    public static RetrofitException unexpectedError(Throwable exception) {
        return new RetrofitException(exception.getMessage(), null, null, Kind.UNEXPECTED, exception, null);
    }

    public static RetrofitException networkNotConnectedError() {
        return new RetrofitException("", null, null, Kind.DISCONNECT, null, null);
    }

    /**
     * The request URL which produced the error.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Response object containing status code, headers, body, etc.
     */
    public Response getResponse() {
        return response;
    }

    /**
     * The event kind which triggered this error.
     */
    @NonNull
    public Kind getKind() {
        return kind;
    }

    /**
     * The Retrofit this request was executed on
     */
    public Retrofit getRetrofit() {
        return retrofit;
    }

    /**
     * HTTP response body converted to specified {@code type}. {@code null} if there is no
     * response.
     */
    public CommonResponseModel getErrorBodyAsCommonResponseModel() {
        if (this.commonResponseModel != null) {
            return commonResponseModel;
        }
        if (response == null || response.errorBody() == null) {
            return null;
        }
        Converter<ResponseBody, CommonResponseModel> converter = retrofit.responseBodyConverter(CommonResponseModel.class, new Annotation[0]);
        try {
            commonResponseModel = converter.convert(response.errorBody());
            return commonResponseModel;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Identifies the event kind which triggered a {@link RetrofitException}.
     */
    public enum Kind {
        /**
         * An {@link IOException} occurred while communicating to the server.
         */
        NETWORK,
        /**
         * A non-200 HTTP status code was received from the server.
         */
        HTTP,
        /**
         * Internet is disconnected
         * just try to avoid making the request
         */
        DISCONNECT,
        /**
         * An internal error occurred while attempting to execute a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
        UNEXPECTED
    }
}