package hr.nullteam.rsc.ui.presenter.login;

import javax.inject.Inject;

import hr.nullteam.rsc.business.api.UserApi;
import hr.nullteam.rsc.business.api.model.Player;
import hr.nullteam.rsc.ui.fragment.login.LoginFragment;
import hr.nullteam.rsc.ui.presenter.BusPresenter;
import hr.nullteam.rsc.util.PreferenceUtils;
import hr.nullteam.rsc.util.ToastUtils;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public final class LoginFragmentPresenter extends BusPresenter<LoginFragment> {

    @Inject
    UserApi userApi;

    @Inject
    PreferenceUtils preferenceUtils;

    @Inject
    ToastUtils toastUtils;

    Subscription userLoginSubscription;

    public void onLoginButtonClick(String email, String password) {

        userLoginSubscription = userApi.loginPlayer(email, password, "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onLoginSuccess, this::onLoginError, this::onLoginCompletion);
        add(userLoginSubscription);
    }

    private void onLoginSuccess(Player player) {
        preferenceUtils.setUser(player);
        fireSuccessfulLoginEvent();
    }

    private void onLoginError(Throwable throwable) {
        remove(userLoginSubscription);
        throwable.printStackTrace();
        toastUtils.showToast("Login error !");
        fireSuccessfulLoginEvent(); // TODO - debug line
    }

    private void onLoginCompletion() {
        remove(userLoginSubscription);
    }

    public void onRegisterButtonClick() {
        fireShowRegisterEvent();
    }

    private void fireShowRegisterEvent() {
        bus.post(new ShowRegisterEvent());
    }

    private void fireSuccessfulLoginEvent() {
        bus.post(new SuccessfulLoginEvent());
    }

    public static class ShowRegisterEvent {
    }

    public static class SuccessfulLoginEvent {
    }

}
