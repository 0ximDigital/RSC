package hr.nullteam.rsc.ui.module;

import hr.nullteam.rsc.ui.fragment.QrScanningFragment;
import hr.nullteam.rsc.ui.fragment.login.LoginFragment;
import hr.nullteam.rsc.ui.fragment.TodoFragment;
import hr.nullteam.rsc.ui.fragment.login.RegisterFragment;
import hr.nullteam.rsc.ui.fragment.profile.EditProfileFragment;
import hr.nullteam.rsc.ui.fragment.profile.ProfileDetailsFragment;
import hr.nullteam.rsc.ui.presenter.QrScanningFragmentPresenter;
import hr.nullteam.rsc.ui.presenter.TodoFragmentPresenter;
import hr.nullteam.rsc.ui.presenter.login.LoginFragmentPresenter;
import hr.nullteam.rsc.ui.presenter.login.RegisterFragmentPresenter;
import hr.nullteam.rsc.ui.presenter.profile.EditProfileFragmentPresenter;
import hr.nullteam.rsc.ui.presenter.profile.ProfileDetailsFragmentPresenter;

public interface FragmentComponentInjects {

    void inject(TodoFragment todoFragment);

    void inject(TodoFragmentPresenter todoFragmentPresenter);

    void inject(LoginFragment loginFragment);

    void inject(LoginFragmentPresenter loginFragmentPresenter);

    void inject(RegisterFragment registerFragment);

    void inject(RegisterFragmentPresenter registerFragmentPresenter);

    void inject(ProfileDetailsFragment profileDetailsFragment);

    void inject(ProfileDetailsFragmentPresenter profileDetailsFragmentPresenter);

    void inject(EditProfileFragment editProfileFragment);

    void inject(EditProfileFragmentPresenter editProfileFragmentPresenter);

    void inject(QrScanningFragment qrScanningFragment);

    void inject(QrScanningFragmentPresenter qrScanningFragmentPresenter);

}
