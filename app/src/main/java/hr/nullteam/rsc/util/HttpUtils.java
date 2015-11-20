package hr.nullteam.rsc.util;

import retrofit.RetrofitError;

public final class HttpUtils {

    public boolean isHttpError(Throwable e) {
        return e != null && e instanceof RetrofitError && ((RetrofitError) e).getKind() == RetrofitError.Kind.HTTP;
    }
}
