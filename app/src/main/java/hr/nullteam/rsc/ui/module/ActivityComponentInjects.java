package hr.nullteam.rsc.ui.module;

import hr.nullteam.rsc.ui.activity.LoginActivity;
import hr.nullteam.rsc.ui.presenter.login.LoginActivityPresenter;

public interface ActivityComponentInjects {

    void inject(LoginActivity mainActivity);

    void inject(LoginActivityPresenter mainPresenter);

}
