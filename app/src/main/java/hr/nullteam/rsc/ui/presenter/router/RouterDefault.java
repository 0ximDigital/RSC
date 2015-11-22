package hr.nullteam.rsc.ui.presenter.router;

import android.app.Activity;
import android.content.Intent;

import javax.inject.Inject;

import hr.nullteam.rsc.ui.activity.MainActivity;
import hr.nullteam.rsc.ui.activity.PlayerGameActivity;
import hr.nullteam.rsc.ui.activity.PreGameActivity;
import hr.nullteam.rsc.ui.activity.ProfileActivity;

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

    @Override
    public void showUserProfile() {
        startIntent(ProfileActivity.createIntent(activity, -1));
    }

    @Override
    public void showUserProfile(long profileId) {
        startIntent(ProfileActivity.createIntent(activity, profileId));
    }

    @Override
    public void showMainScreen() {
        startIntent(MainActivity.createIntent(activity));
    }

    @Override
    public void showPreGameScreen() {
        startIntent(PreGameActivity.createIntent(activity));
    }

    @Override
    public void showPlayerGame() {
        startIntent(PlayerGameActivity.createIntent(activity));
    }

}
