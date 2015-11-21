package hr.nullteam.rsc.ui.presenter.main;

import com.squareup.otto.Subscribe;

import hr.nullteam.rsc.ui.activity.PreGameActivity;
import hr.nullteam.rsc.ui.presenter.BusPresenter;
import hr.nullteam.rsc.ui.presenter.QrScanningFragmentPresenter;

public class PreGameActivityPresenter extends BusPresenter<PreGameActivity> {

    @Subscribe
    public void on(QrScanningFragmentPresenter.QrScannedEvent event) {
        final String qrData = event.data;
        final PreGameActivity activity = getView();
        if(activity == null) {
            return;
        }
        activity.showProcessing();
    }
}
