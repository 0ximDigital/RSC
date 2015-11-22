package hr.nullteam.rsc.application;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;
import com.raizlabs.android.dbflow.config.FlowManager;

import hr.nullteam.rsc.BuildConfig;
import microsoft.aspnet.signalr.client.Platform;
import microsoft.aspnet.signalr.client.http.android.AndroidPlatformComponent;

public final class RscApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = ApplicationComponent.Initializer.init(this);
        applicationComponent.inject(this);

        Platform.loadPlatformComponent(new AndroidPlatformComponent());

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
