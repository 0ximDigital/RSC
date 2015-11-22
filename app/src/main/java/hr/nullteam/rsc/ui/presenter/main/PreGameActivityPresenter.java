package hr.nullteam.rsc.ui.presenter.main;

import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import hr.nullteam.rsc.business.api.QrData;
import hr.nullteam.rsc.ui.activity.PreGameActivity;
import hr.nullteam.rsc.ui.presenter.BusPresenter;
import hr.nullteam.rsc.ui.presenter.QrScanningFragmentPresenter;
import hr.nullteam.rsc.ui.presenter.game.PreGameFragmentPresenter;
import hr.nullteam.rsc.ui.presenter.router.Router;

public class PreGameActivityPresenter extends BusPresenter<PreGameActivity> {

    @Inject
    Router router;

    @Subscribe
    public void on(QrScanningFragmentPresenter.QrScannedEvent event) {
        final QrData qrData = event.qrData;
        final PreGameActivity activity = getView();
        if(activity == null) {
            return;
        }
        activity.showProcessing();
    }

    @Subscribe
    public void on(PreGameFragmentPresenter.StartPlayerGameEvent event) {
        router.showPlayerGame();
        router.finishCurrentActivity();
    }
}
