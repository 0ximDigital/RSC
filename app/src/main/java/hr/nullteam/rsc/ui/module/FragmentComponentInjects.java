package hr.nullteam.rsc.ui.module;

import hr.nullteam.rsc.ui.fragment.login.LoginFragment;
import hr.nullteam.rsc.ui.fragment.TodoFragment;
import hr.nullteam.rsc.ui.fragment.login.RegisterFragment;
import hr.nullteam.rsc.ui.presenter.TodoFragmentPresenter;
import hr.nullteam.rsc.ui.presenter.login.LoginFragmentPresenter;
import hr.nullteam.rsc.ui.presenter.login.RegisterFragmentPresenter;

public interface FragmentComponentInjects {

    void inject(TodoFragment todoFragment);

    void inject(TodoFragmentPresenter todoFragmentPresenter);

    void inject(LoginFragment loginFragment);

    void inject(LoginFragmentPresenter loginFragmentPresenter);

    void inject(RegisterFragment registerFragment);

    void inject(RegisterFragmentPresenter registerFragmentPresenter);

}
