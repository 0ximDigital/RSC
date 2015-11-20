package hr.nullteam.rsc.ui.activity;

import android.os.Bundle;

import hr.nullteam.rsc.application.RscApplication;
import hr.nullteam.rsc.ui.module.ActivityComponent;
import hr.nullteam.rsc.ui.module.ComponentFactory;
import nucleus.presenter.Presenter;
import nucleus.view.NucleusAppCompatActivity;

public abstract class DaggerActivity<T extends Presenter> extends NucleusAppCompatActivity<T> {

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectMe();
    }

    protected abstract void inject(ActivityComponent activityComponent);

    public ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = ComponentFactory.createActivityComponent(this, getCilabsApplication());
        }
        return activityComponent;
    }

    private void injectMe() {
        activityComponent = getActivityComponent();
        inject(activityComponent);
    }

    protected RscApplication getCilabsApplication() {
        return (RscApplication) getApplication();
    }

}
