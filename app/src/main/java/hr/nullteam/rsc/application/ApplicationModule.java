package hr.nullteam.rsc.application;

import android.app.NotificationManager;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.Resources;
import android.location.LocationManager;
import android.net.ConnectivityManager;

import com.facebook.stetho.okhttp.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.otto.Bus;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hr.nullteam.rsc.business.api.JudgeApi;
import hr.nullteam.rsc.business.api.PlayerApi;
import hr.nullteam.rsc.business.api.TeamApi;
import hr.nullteam.rsc.business.api.UserApi;
import hr.nullteam.rsc.business.interactor.CacheInteractor;
import hr.nullteam.rsc.config.Urls;
import hr.nullteam.rsc.util.DateUtils;
import hr.nullteam.rsc.util.DisplayUtils;
import hr.nullteam.rsc.util.HttpUtils;
import hr.nullteam.rsc.util.IntentUtils;
import hr.nullteam.rsc.util.KeyboardUtils;
import hr.nullteam.rsc.util.ListUtils;
import hr.nullteam.rsc.util.NetworkUtils;
import hr.nullteam.rsc.util.PreferenceUtils;
import hr.nullteam.rsc.util.StringUtils;
import hr.nullteam.rsc.util.ToastUtils;
import hr.nullteam.rsc.util.VibratorUtils;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

@Module
public final class ApplicationModule {

    private static final int TIMEOUT = 5 * 60 * 1000;

    private final RscApplication rscApplication;

    public ApplicationModule(RscApplication rscApplication) {
        this.rscApplication = rscApplication;
    }

    @Provides
    @Singleton
    RscApplication provideRscApplication() {
        return this.rscApplication;
    }

    @Provides
    @Singleton
    Bus provideBus() {
        return new Bus();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat(DateUtils.GSON_DATE_PATTERN);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    GsonConverter provideGsonConverter(Gson gson) {
        return new GsonConverter(gson);
    }

    @Provides
    @Singleton
    OkClient provideOkClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.networkInterceptors().add(new StethoInterceptor());
        okHttpClient.setConnectTimeout(TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.setWriteTimeout(TIMEOUT, TimeUnit.MILLISECONDS);
        return new OkClient(okHttpClient);
    }

    @Provides
    @Singleton
    UserApi provideUserApi(OkClient okClient, GsonConverter gsonConverter) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(okClient)
                .setEndpoint(Urls.BASE_URL + Urls.USER_ENDPOINT)
                .setConverter(gsonConverter)
                .build();
        return restAdapter.create(UserApi.class);
    }

    @Provides
    @Singleton
    JudgeApi provideJudgeApi(OkClient okClient, GsonConverter gsonConverter) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(okClient)
                .setEndpoint(Urls.BASE_URL + Urls.JUDGE_ENDPOINT)
                .setConverter(gsonConverter)
                .build();
        return restAdapter.create(JudgeApi.class);
    }

    @Provides
    @Singleton
    PlayerApi providePlayerApi(OkClient okClient, GsonConverter gsonConverter) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(okClient)
                .setEndpoint(Urls.BASE_URL + Urls.PLAYER_ENDPOINT)
                .setConverter(gsonConverter)
                .build();
        return restAdapter.create(PlayerApi.class);
    }

    @Provides
    @Singleton
    TeamApi provideTeamApi(OkClient okClient, GsonConverter gsonConverter) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(okClient)
                .setEndpoint(Urls.BASE_URL + Urls.TEAM_ENDPOINT)
                .setConverter(gsonConverter)
                .build();
        return restAdapter.create(TeamApi.class);
    }

    @Provides
    @Singleton
    Resources provideResources() {
        return this.rscApplication.getResources();
    }

    @Provides
    @Singleton
    VibratorUtils provideVibratorUtils() {
        return new VibratorUtils(this.rscApplication);
    }

    @Provides
    @Singleton
    CacheInteractor provideCacheInteractor() {
        return null;        // TODO
    }

    @Provides
    @Singleton
    DisplayUtils provideDisplayUtils() {
        return new DisplayUtils(rscApplication);
    }

    @Provides
    @Singleton
    HttpUtils provideHttpUtils() {
        return new HttpUtils();
    }

    @Provides
    @Singleton
    ToastUtils provideToastUtils(DisplayUtils displayUtils, Resources resources) {
        return new ToastUtils(rscApplication, displayUtils, resources);
    }

    @Provides
    @Singleton
    DateUtils provideDateUtils() {
        return new DateUtils();
    }

    @Provides
    @Singleton
    IntentUtils provideIntentUtils() {
        return new IntentUtils(rscApplication);
    }

    @Provides
    @Singleton
    StringUtils provideStringUtils() {
        return new StringUtils(rscApplication);
    }

    @Provides
    @Singleton
    PreferenceUtils providePreferenceUtils(Gson gson) {
        return new PreferenceUtils(rscApplication, gson);
    }

    @Provides
    @Singleton
    KeyboardUtils provideKeyboardUtils() {
        return new KeyboardUtils(rscApplication);
    }

    @Provides
    @Singleton
    NetworkUtils provideNetworkUtils(ConnectivityManager connectivityManager) {
        return new NetworkUtils(connectivityManager);
    }

    @Provides
    @Singleton
    ConnectivityManager provideConnectivityManager() {
        return (ConnectivityManager) rscApplication.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Provides
    @Singleton
    ListUtils provideListUtils() {
        return new ListUtils();
    }

    @Provides
    @Singleton
    LocationManager provideLocationManager() {
        return (LocationManager) rscApplication.getSystemService(Context.LOCATION_SERVICE);
    }

    @Provides
    @Singleton
    NotificationManager provideNotificationManager() {
        return (NotificationManager) rscApplication.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Provides
    @Singleton
    ClipboardManager provideClipboardManager() {
        return (ClipboardManager) rscApplication.getSystemService(Context.CLIPBOARD_SERVICE);
    }

}
