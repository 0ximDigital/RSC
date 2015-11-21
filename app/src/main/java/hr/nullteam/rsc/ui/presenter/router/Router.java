package hr.nullteam.rsc.ui.presenter.router;

import android.content.Intent;

public interface Router {

    void finishCurrentActivity();

    void startIntent(Intent intent);

    void showUserProfile();

    void showUserProfile(long profileId);

}
