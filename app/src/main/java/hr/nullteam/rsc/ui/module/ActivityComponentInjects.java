package hr.nullteam.rsc.ui.module;

import hr.nullteam.rsc.ui.activity.MainActivity;
import hr.nullteam.rsc.ui.presenter.MainPresenter;

public interface ActivityComponentInjects {

    void inject(MainActivity mainActivity);

    void inject(MainPresenter mainPresenter);

}
