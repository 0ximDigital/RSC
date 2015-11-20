package hr.nullteam.rsc.ui.module;

import hr.nullteam.rsc.application.RscApplication;
import hr.nullteam.rsc.ui.activity.DaggerActivity;

public final class ComponentFactory {

    private ComponentFactory() {
    }

    public static ActivityComponent createActivityComponent(DaggerActivity daggerActivity, RscApplication rscApplication) {
        return ActivityComponent.Initializer.init(daggerActivity, rscApplication.getApplicationComponent());
    }

    public static FragmentComponent createFragmentComponent(ActivityComponent activityComponent) {
        return FragmentComponent.Initializer.init(activityComponent);
    }

}
