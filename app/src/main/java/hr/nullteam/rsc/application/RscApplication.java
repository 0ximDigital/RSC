package hr.nullteam.rsc.application;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.raizlabs.android.dbflow.config.FlowManager;

import hr.nullteam.rsc.BuildConfig;

public class RscApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = ApplicationComponent.Initializer.init(this);
        applicationComponent.inject(this);

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }

        FlowManager.init(this);
        Fresco.initialize(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
