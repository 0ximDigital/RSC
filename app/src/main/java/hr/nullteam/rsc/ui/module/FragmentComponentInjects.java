package hr.nullteam.rsc.ui.module;

import hr.nullteam.rsc.ui.fragment.TodoFragment;
import hr.nullteam.rsc.ui.presenter.TodoFragmentPresenter;

public interface FragmentComponentInjects {

    void inject(TodoFragment todoFragment);

    void inject(TodoFragmentPresenter todoFragmentPresenter);

}
