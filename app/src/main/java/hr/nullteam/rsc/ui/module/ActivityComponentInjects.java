package hr.nullteam.rsc.ui.module;

import hr.nullteam.rsc.ui.activity.LoginActivity;
import hr.nullteam.rsc.ui.activity.ProfileActivity;
import hr.nullteam.rsc.ui.presenter.login.LoginActivityPresenter;
import hr.nullteam.rsc.ui.presenter.profile.ProfileActivityPresenter;

public interface ActivityComponentInjects {

    void inject(LoginActivity mainActivity);

    void inject(LoginActivityPresenter mainPresenter);

    void inject(ProfileActivity profileActivity);

    void inject(ProfileActivityPresenter profileActivityPresenter);

}
