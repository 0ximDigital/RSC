package hr.nullteam.rsc.ui.presenter;

import com.squareup.otto.Bus;

import javax.inject.Inject;

import nucleus.presenter.RxPresenter;

public class BusPresenter<View> extends RxPresenter<View> {

    @Inject
    protected Bus bus;

    @Override
    protected void onTakeView(View view) {
        super.onTakeView(view);
        bus.register(this);
    }

    @Override
    protected void onDropView() {
        bus.unregister(this);
        super.onDropView();
    }

}
