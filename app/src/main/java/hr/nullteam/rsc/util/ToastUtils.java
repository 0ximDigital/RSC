package hr.nullteam.rsc.util;

import android.content.Context;
import android.content.res.Resources;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import hr.nullteam.rsc.R;

public final class ToastUtils {

    private final Context context;
    private final DisplayUtils displayUtils;
    private final Resources resources;

    @Inject
    public ToastUtils(Context context, DisplayUtils displayUtils, Resources resources) {
        this.context = context;
        this.displayUtils = displayUtils;
        this.resources = resources;
    }

    public void showToast(View aboveView, String message) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.rsc_toast, null);
        TextView text = (TextView) layout.findViewById(R.id.textToShow);
        text.setText(message);

        Toast toast = new Toast(context);
        int[] position = new int[2];
        aboveView.getLocationOnScreen(position);
        int y = position[1] + aboveView.getHeight();
        toast.setGravity(Gravity.TOP, 0, y);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    public void showToast(String message) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.rsc_toast, null);
        TextView text = (TextView) layout.findViewById(R.id.textToShow);
        text.setText(message);

        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

}
