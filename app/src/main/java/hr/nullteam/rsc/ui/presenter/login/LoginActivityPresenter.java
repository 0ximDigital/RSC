package hr.nullteam.rsc.ui.presenter.login;

import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import hr.nullteam.rsc.ui.activity.LoginActivity;
import hr.nullteam.rsc.ui.presenter.BusPresenter;
import hr.nullteam.rsc.ui.presenter.router.Router;

public final class LoginActivityPresenter extends BusPresenter<LoginActivity> {

    @Inject
    Router router;

    @Subscribe
    public void on(LoginFragmentPresenter.ShowRegisterEvent event){
        router.showPlayerGame();
        router.finishCurrentActivity();/*
        LoginActivity activity = getView();
        if(activity == null) {
            return;
        }
        activity.showRegisterFragment();*/ // TODO
    }

    @Subscribe
    public void on(LoginFragmentPresenter.SuccessfulLoginEvent event) {
        router.showMainScreen();
        router.finishCurrentActivity();
    }

    @Subscribe
    public void on(RegisterFragmentPresenter.SuccessfulRegisterEvent event) {
        router.showMainScreen();
        router.finishCurrentActivity();
    }

}
