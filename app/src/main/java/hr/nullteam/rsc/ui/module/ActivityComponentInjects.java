package hr.nullteam.rsc.ui.module;

import hr.nullteam.rsc.ui.activity.LoginActivity;
import hr.nullteam.rsc.ui.activity.MainActivity;
import hr.nullteam.rsc.ui.activity.PreGameActivity;
import hr.nullteam.rsc.ui.activity.ProfileActivity;
import hr.nullteam.rsc.ui.presenter.login.LoginActivityPresenter;
import hr.nullteam.rsc.ui.presenter.main.MainActivityPresenter;
import hr.nullteam.rsc.ui.presenter.main.PreGameActivityPresenter;
import hr.nullteam.rsc.ui.presenter.profile.ProfileActivityPresenter;

public interface ActivityComponentInjects {

    void inject(LoginActivity mainActivity);

    void inject(LoginActivityPresenter mainPresenter);

    void inject(ProfileActivity profileActivity);

    void inject(ProfileActivityPresenter profileActivityPresenter);

    void inject(MainActivity mainActivity);

    void inject(MainActivityPresenter mainActivityPresenter);

    void inject(PreGameActivity preGameActivity);

    void inject(PreGameActivityPresenter preGameActivityPresenter);

}
