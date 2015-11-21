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
import hr.nullteam.rsc.business.api.model.RegisterPlayer;
import hr.nullteam.rsc.ui.fragment.DaggerFragment;
import hr.nullteam.rsc.ui.module.FragmentComponent;
import hr.nullteam.rsc.ui.presenter.login.RegisterFragmentPresenter;
import nucleus.factory.PresenterFactory;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(RegisterFragmentPresenter.class)
public final class RegisterFragment extends DaggerFragment<RegisterFragmentPresenter> {

    public static String TAG = RegisterFragment.class.getSimpleName();

    @Bind(R.id.email_edit_text)
    public EditText email;

    @Bind(R.id.password_edit_text)
    public EditText password;

    @Bind(R.id.name_edit_text)
    public EditText name;

    @Bind(R.id.surname_edit_text)
    public EditText surname;

    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        return fragment;
    }

    @Override
    protected void setUpPresenter() {
        final PresenterFactory<RegisterFragmentPresenter> superFactory = super.getPresenterFactory();
        setPresenterFactory(() -> {
            RegisterFragmentPresenter presenter = superFactory.createPresenter();
            getFragmentComponent().inject(presenter);
            return presenter;
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_register, container, false);
        injectViews(fragmentView);
        return fragmentView;
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @OnClick(R.id.register_button)
    void onRegisterButtonClick() {
        RegisterPlayer newPlayer = new RegisterPlayer(
                email.getText().toString(),
                password.getText().toString(),
                name.getText().toString(),
                surname.getText().toString());
        getPresenter().onRegisterButtonClick(newPlayer);
    }

}
