package hr.nullteam.rsc.util;

import android.content.Context;

import javax.inject.Inject;

public final class StringUtils {

    public static final String EMAIL_REGEX = ".+@.+\\..+";

    private final Context context;

    @Inject
    public StringUtils(Context context) {
        this.context = context;
    }

}
