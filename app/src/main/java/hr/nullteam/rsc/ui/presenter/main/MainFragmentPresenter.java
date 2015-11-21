package hr.nullteam.rsc.ui.presenter.main;

import javax.inject.Inject;

import hr.nullteam.rsc.ui.fragment.main.MainFragment;
import hr.nullteam.rsc.ui.presenter.BusPresenter;
import hr.nullteam.rsc.ui.presenter.router.Router;

public class MainFragmentPresenter extends BusPresenter<MainFragment> {

    @Inject
    Router router;

    public void onPlayButtonClick() {
        router.showPreGameScreen();
    }

    public void onSpectateButtonClick() {

    }

}
