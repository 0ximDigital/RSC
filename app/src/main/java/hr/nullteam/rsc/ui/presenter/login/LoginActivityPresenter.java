package hr.nullteam.rsc.ui.presenter.login;

import com.squareup.otto.Subscribe;

import hr.nullteam.rsc.ui.activity.LoginActivity;
import hr.nullteam.rsc.ui.presenter.BusPresenter;

public final class LoginActivityPresenter extends BusPresenter<LoginActivity> {


    @Subscribe
    public void on(LoginFragmentPresenter.ShowRegisterEvent event){
        LoginActivity activity = getView();
        if(activity == null) {
            return;
        }
        activity.showRegisterFragment();
    }

}
