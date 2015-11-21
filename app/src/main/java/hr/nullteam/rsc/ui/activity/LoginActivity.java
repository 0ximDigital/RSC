package hr.nullteam.rsc.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import javax.inject.Inject;

import hr.nullteam.rsc.R;
import hr.nullteam.rsc.ui.fragment.login.LoginFragment;
import hr.nullteam.rsc.ui.fragment.login.RegisterFragment;
import hr.nullteam.rsc.ui.module.ActivityComponent;
import hr.nullteam.rsc.ui.presenter.login.LoginActivityPresenter;
import nucleus.factory.PresenterFactory;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(LoginActivityPresenter.class)
public final class LoginActivity extends PresenterActivity<LoginActivityPresenter> {

    @Inject
    FragmentManager fragmentManager;

    LoginFragment loginFragment;
    RegisterFragment registerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            loginFragment = LoginFragment.newInstance();
            fragmentManager.beginTransaction().add(R.id.activity_container, loginFragment, LoginFragment.TAG).commit();
        } else {
            loginFragment = (LoginFragment) fragmentManager.findFragmentByTag(LoginFragment.TAG);
            registerFragment = (RegisterFragment) fragmentManager.findFragmentByTag(RegisterFragment.TAG);
        }
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void setUpPresenter() {
        final PresenterFactory<LoginActivityPresenter> superFactory = super.getPresenterFactory();
        setPresenterFactory(() -> {
            LoginActivityPresenter presenter = superFactory.createPresenter();
            getActivityComponent().inject(presenter);
            return presenter;
        });
    }

    public void showLoginFragment() {
        if (loginFragment == null) {
            loginFragment = LoginFragment.newInstance();
        }
        fragmentManager.beginTransaction().add(R.id.activity_container, loginFragment, LoginFragment.TAG).commit();
    }

    public void showRegisterFragment() {
        if (registerFragment == null) {
            registerFragment = RegisterFragment.newInstance();
        }
        fragmentManager.beginTransaction().addToBackStack(null).add(R.id.activity_container, registerFragment, RegisterFragment.TAG).commit();
    }
}
