package hr.nullteam.rsc.ui.presenter.login;

import hr.nullteam.rsc.ui.fragment.login.LoginFragment;
import hr.nullteam.rsc.ui.presenter.BusPresenter;

public final class LoginFragmentPresenter extends BusPresenter<LoginFragment> {

    public void onLoginButtonClick(String username, String password) {

    }

    public void onRegisterButtonClick() {
        fireShowRegisterevent();
    }

    private void fireShowRegisterevent() {
        bus.post(new ShowRegisterEvent());
    }

    public static class ShowRegisterEvent {
    }

}
