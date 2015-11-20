package hr.nullteam.rsc.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import javax.inject.Inject;

public final class KeyboardUtils {

    private final Context context;

    @Inject
    public KeyboardUtils(Context context) {
        this.context = context;
    }

    public void showSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public void hideSoftKeyboard(View v) {
        if (v != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

}
