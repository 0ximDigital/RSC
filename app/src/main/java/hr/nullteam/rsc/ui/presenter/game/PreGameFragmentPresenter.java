package hr.nullteam.rsc.ui.presenter.game;

import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import hr.nullteam.rsc.business.api.PlayerApi;
import hr.nullteam.rsc.business.api.QrData;
import hr.nullteam.rsc.business.api.TeamApi;
import hr.nullteam.rsc.business.api.model.Player;
import hr.nullteam.rsc.business.api.model.Team;
import hr.nullteam.rsc.business.api.model.User;
import hr.nullteam.rsc.ui.fragment.game.PreGameFragment;
import hr.nullteam.rsc.ui.presenter.BusPresenter;
import hr.nullteam.rsc.ui.presenter.QrScanningFragmentPresenter;
import hr.nullteam.rsc.util.PreferenceUtils;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PreGameFragmentPresenter extends BusPresenter<PreGameFragment> {

    @Inject
    TeamApi teamApi;

    @Inject
    PlayerApi playerApi;

    @Inject
    PreferenceUtils preferenceUtils;

    private Subscription teamSubscription;
    private Subscription playerSubscription;

    private long teamId;

    public void onTeamConfirmationClick() {
        fireStartPlayerGameEvent();
    }

    public void onGameConfirmationClick() {
        fireStartJudgeGameEvent();
    }

    @Subscribe
    public void on(QrScanningFragmentPresenter.QrScannedEvent event) {
        final QrData qrData = event.qrData;
        if(QrData.TYPE_PLAYER.equals(qrData.getType())) {
            teamId = qrData.getId();
            fetchTeam(teamId);
            createPlayer();
        } else if(QrData.TYPE_JUDGE.equals(qrData.getType())) {
            // TODO
        } else {
            // TODO
        }
    }

    private void fetchTeam(long teamId) {
        teamSubscription = teamApi.getTeamWithId(teamId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onTeamSuccess, this::onTeamError, this::onTeamCompletion);
        add(teamSubscription);
    }

    private void createPlayer() {
        User user = preferenceUtils.getUser();
        Player newPlayer = new Player();
        newPlayer.setTeamId(teamId);
        newPlayer.setUserId(user.getId());
        playerSubscription = playerApi.createNewPlayer(newPlayer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onPlayerSuccess, this::onPlayerError, this::onPlayerCompletion);
        add(playerSubscription);
    }

    private void onPlayerSuccess(Player player) {
        preferenceUtils.setPlayerId(player.getId());
    }

    private void onPlayerError(Throwable throwable) {
        remove(playerSubscription);
        throwable.printStackTrace();
    }

    private void onPlayerCompletion() {
        remove(playerSubscription);
    }

    private void onTeamSuccess(Team team) {
        PreGameFragment fragment = getView();
        if(fragment == null) {
            return;
        }
        fragment.setTeamData(team);
        preferenceUtils.setTeamId(teamId);
    }

    private void onTeamError(Throwable throwable) {
        throwable.printStackTrace();
        remove(teamSubscription);
    }

    private void onTeamCompletion() {
        remove(teamSubscription);
    }

    private void fetchGame(long gameId) {
        // TODO
    }

    private void fireStartPlayerGameEvent() {
        bus.post(new StartPlayerGameEvent());
    }

    private void fireStartJudgeGameEvent() {
        bus.post(new StartJudgeGameEvent());
    }

    public static final class StartPlayerGameEvent {
    }

    public static final class StartJudgeGameEvent {
    }

}
