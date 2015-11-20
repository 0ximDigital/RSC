package hr.nullteam.rsc.ui.module;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;

import dagger.Module;
import dagger.Provides;
import hr.nullteam.rsc.ui.activity.DaggerActivity;
import hr.nullteam.rsc.ui.presenter.router.Router;
import hr.nullteam.rsc.ui.presenter.router.RouterDefault;

@Module
public final class ActivityModule {

    private final DaggerActivity daggerActivity;

    public ActivityModule(DaggerActivity daggerActivity) {
        this.daggerActivity = daggerActivity;
    }

    @Provides
    @ActivityScope
    @ForActivity
    Context provideActivityContext() {
        return daggerActivity;
    }

    @Provides
    @ActivityScope
    FragmentManager provideFragmentManager() {
        return daggerActivity.getSupportFragmentManager();
    }

    @Provides
    @ActivityScope
    Router provideRouter() {
        return new RouterDefault(daggerActivity);
    }

    @Provides
    @ActivityScope
    LayoutInflater provideLayoutInflater(){
        return daggerActivity.getLayoutInflater();
    }

}
