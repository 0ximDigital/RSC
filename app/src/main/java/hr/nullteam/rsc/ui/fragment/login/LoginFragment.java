package hr.nullteam.rsc.ui.fragment.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.OnClick;
import hr.nullteam.rsc.R;
import hr.nullteam.rsc.ui.fragment.DaggerFragment;
import hr.nullteam.rsc.ui.module.FragmentComponent;
import hr.nullteam.rsc.ui.presenter.login.LoginFragmentPresenter;
import nucleus.factory.PresenterFactory;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(LoginFragmentPresenter.class)
public final class LoginFragment extends DaggerFragment<LoginFragmentPresenter> {

    public static String TAG = LoginFragment.class.getSimpleName();

    @Bind(R.id.username_text)
    EditText username;

    @Bind(R.id.password_text)
    EditText password;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    protected void setUpPresenter() {
        final PresenterFactory<LoginFragmentPresenter> superFactory = super.getPresenterFactory();
        setPresenterFactory(() -> {
            LoginFragmentPresenter presenter = superFactory.createPresenter();
            getFragmentComponent().inject(presenter);
            return presenter;
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_login, container, false);
        injectViews(fragmentView);
        return fragmentView;
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @OnClick(R.id.login_button)
    void onLoginButtonClick() {
        getPresenter().onLoginButtonClick(username.getText().toString(), password.getText().toString());
    }

    @OnClick(R.id.register_button)
    void onRegisterButtonClick() {
        getPresenter().onRegisterButtonClick();
    }
}
