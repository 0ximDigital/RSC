package hr.nullteam.rsc.ui.presenter.game;

import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import hr.nullteam.rsc.business.api.PlayerApi;
import hr.nullteam.rsc.business.api.model.Player;
import hr.nullteam.rsc.business.service.SignalService;
import hr.nullteam.rsc.ui.activity.PlayerGameActivity;
import hr.nullteam.rsc.ui.presenter.BusPresenter;
import hr.nullteam.rsc.ui.view.GestureFrameLayout;
import hr.nullteam.rsc.util.PreferenceUtils;
import hr.nullteam.rsc.util.VibratorUtils;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PlayerGameActivityPresenter extends BusPresenter<PlayerGameActivity> {

    @Inject
    VibratorUtils vibratorUtils;

    @Inject
    PlayerApi playerApi;

    @Inject
    PreferenceUtils preferenceUtils;

    private Subscription playerSubscription;

    public void onButtonClick() {
        long playerId = preferenceUtils.getPlayerId();
        playerSubscription = playerApi.updatePlayerAlive(playerId, false, "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onPlayerUpdateSuccess, this::onPlayerUpdateError, this::onPlayerUpdateCompletion);
        add(playerSubscription);
    }

    private void onPlayerUpdateSuccess(Player player) {

    }

    private void onPlayerUpdateError(Throwable throwable) {
        remove(playerSubscription);
        throwable.printStackTrace();
    }

    private void onPlayerUpdateCompletion() {
        remove(playerSubscription);
    }

    @Subscribe
    public void on(GestureFrameLayout.FlingEvent event){
        handleFlingEvent(event);
    }

    @Subscribe
    public void on(PlayerGameFragmentPresenter.JoinTeamEvent event) {
        PlayerGameActivity activity = getView();
        if(activity == null) {
            return;
        }
        activity.startService(SignalService.getjoinTeamIntent(activity, event.teamId));
    }

    private void handleFlingEvent(GestureFrameLayout.FlingEvent event) {
        switch (event.flingDirection) {
            case UP :
                handleUpFling();
                break;
            case DOWN:
                handleDownFling();
                break;
            case LEFT:
                handleLeftFling();
                break;
            case RIGHT:
                handleRightFling();
                break;
        }
    }

    private void handleUpFling(){
        vibratorUtils.vibrate(VibratorUtils.DANGER_PATTERN);
    }

    private void handleDownFling() {
        vibratorUtils.vibrate(VibratorUtils.KILLED_PATTERN);
    }

    private void handleLeftFling() {
        vibratorUtils.vibrate(VibratorUtils.SPOTTED_PATTERN);
    }

    private void handleRightFling() {
        vibratorUtils.vibrate(VibratorUtils.DANGER_PATTERN);
    }

}
