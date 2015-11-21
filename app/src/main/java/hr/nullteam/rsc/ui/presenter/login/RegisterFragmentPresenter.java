package hr.nullteam.rsc.ui.presenter.login;

import javax.inject.Inject;

import hr.nullteam.rsc.business.api.UserApi;
import hr.nullteam.rsc.business.api.model.User;
import hr.nullteam.rsc.business.api.model.RegisterPlayer;
import hr.nullteam.rsc.ui.fragment.login.RegisterFragment;
import hr.nullteam.rsc.ui.presenter.BusPresenter;
import hr.nullteam.rsc.util.PreferenceUtils;
import hr.nullteam.rsc.util.ToastUtils;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public final class RegisterFragmentPresenter extends BusPresenter<RegisterFragment> {

    @Inject
    UserApi userApi;

    @Inject
    PreferenceUtils preferenceUtils;

    @Inject
    ToastUtils toastUtils;

    Subscription userRegistrationSubscription;

    public void onRegisterButtonClick(RegisterPlayer registerPlayer) {
        userRegistrationSubscription = userApi.registerPlayer(registerPlayer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onRegistrationSuccess, this::onRegistrationError, this::onRegistrationCompletion);
        add(userRegistrationSubscription);
    }

    private void onRegistrationSuccess(User user){
        preferenceUtils.setUser(user);
        fireSuccessfulRegisterEvent();
    }

    private void onRegistrationError(Throwable throwable) {
        remove(userRegistrationSubscription);
        throwable.printStackTrace();
        toastUtils.showToast("Registration error");
    }

    private void onRegistrationCompletion() {
        remove(userRegistrationSubscription);
    }

    private void fireSuccessfulRegisterEvent() {
        bus.post(new SuccessfulRegisterEvent());
    }

    public static final class SuccessfulRegisterEvent {
    }

}
