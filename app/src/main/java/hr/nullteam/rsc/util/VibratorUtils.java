package hr.nullteam.rsc.util;

import android.content.Context;
import android.os.Vibrator;

import javax.inject.Inject;

import hr.nullteam.rsc.application.ForApplication;

public final class VibratorUtils {

    public static long[] DANGER_PATTERN = new long[]{400, 200, 400, 200, 400};      // TODO
    public static long[] SPOTTED_PATTERN = new long[]{600, 250, 600};
    public static long[] KILLED_PATTERN = new long[]{1200};

    private static final int DO_NOT_REPEAT = -1;

    Vibrator vibrator;

    @Inject
    public VibratorUtils(@ForApplication Context context) {
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public void vibrate(long[] vibrationPattern) {
        vibrator.vibrate(vibrationPattern, DO_NOT_REPEAT);
    }


}
