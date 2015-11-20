package hr.nullteam.rsc.ui.presenter.router;

import android.app.Activity;
import android.content.Intent;

import javax.inject.Inject;

public final class RouterDefault implements Router {

    private final Activity activity;

    @Inject
    public RouterDefault(final Activity activity) {
        this.activity = activity;
    }

    @Override
    public void finishCurrentActivity() {
        activity.finish();
    }

    @Override
    public void startIntent(Intent intent) {
        activity.startActivity(intent);
    }

}
