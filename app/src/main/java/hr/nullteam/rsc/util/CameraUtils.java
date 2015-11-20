package hr.nullteam.rsc.util;

import android.content.Context;
import android.content.pm.PackageManager;

public final class CameraUtils {

    public static boolean hasCamera(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

}
