package hr.nullteam.rsc.ui.module;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;

import dagger.Component;
import hr.nullteam.rsc.application.ApplicationComponent;
import hr.nullteam.rsc.ui.activity.DaggerActivity;
import hr.nullteam.rsc.ui.presenter.router.Router;

@ActivityScope
@Component(
        dependencies = {ApplicationComponent.class},
        modules = {ActivityModule.class}
)
public interface ActivityComponent extends ApplicationComponent, ActivityComponentInjects {

    final class Initializer {
        static public ActivityComponent init(DaggerActivity daggerActivity,
                                             ApplicationComponent applicationComponent) {
            return DaggerActivityComponent.builder()
                    .applicationComponent(applicationComponent)
                    .activityModule(new ActivityModule(daggerActivity))
                    .build();
        }

        // No instances
        private Initializer() {
        }
    }

    @ForActivity
    Context context();

    Router router();

    FragmentManager fragmentManager();

    LayoutInflater layoutInflater();

}
