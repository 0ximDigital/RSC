package hr.nullteam.rsc.application;

import android.app.NotificationManager;
import android.content.ClipboardManager;
import android.content.res.Resources;
import android.location.LocationManager;

import com.google.gson.Gson;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;
import hr.nullteam.rsc.business.dao.DbModule;
import hr.nullteam.rsc.business.interactor.CacheInteractor;
import hr.nullteam.rsc.util.DateUtils;
import hr.nullteam.rsc.util.DisplayUtils;
import hr.nullteam.rsc.util.HttpUtils;
import hr.nullteam.rsc.util.IntentUtils;
import hr.nullteam.rsc.util.KeyboardUtils;
import hr.nullteam.rsc.util.ListUtils;
import hr.nullteam.rsc.util.PreferenceUtils;
import hr.nullteam.rsc.util.StringUtils;
import hr.nullteam.rsc.util.ToastUtils;

@Singleton
@Component(
        modules = {ApplicationModule.class,
                DbModule.class}
)
public interface ApplicationComponent extends ApplicationComponentInjects {

    final static class Initializer {
        static public ApplicationComponent init(RscApplication rscApplication) {
            return DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(rscApplication))
                    .build();
        }

        private Initializer() {
        }
    }

    RscApplication rscApplication();

    Bus bus();

    Resources resources();

    Gson gson();

    CacheInteractor cacheInteractor();

    DisplayUtils displayUtils();

    HttpUtils httpUtils();

    ToastUtils toastUtils();

    DateUtils dateUtils();

    IntentUtils intentUtils();

    StringUtils stringUtils();

    PreferenceUtils preferenceUtils();

    KeyboardUtils keyboardUtils();

    ListUtils listUtils();

    LocationManager locationManager();

    NotificationManager notificationManager();

    ClipboardManager clipboardManager();

}
