package hr.nullteam.rsc.application;

import hr.nullteam.rsc.business.service.SignalService;
import hr.nullteam.rsc.ui.view.GestureFrameLayout;

public interface ApplicationComponentInjects {

    void inject(RscApplication rscApplication);

    void inject(GestureFrameLayout gestureFrameLayout);

    void inject(SignalService signalService);

}
