package hr.nullteam.rsc.ui.presenter.game;

import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import hr.nullteam.rsc.business.api.QrData;
import hr.nullteam.rsc.business.api.TeamApi;
import hr.nullteam.rsc.business.api.model.Team;
import hr.nullteam.rsc.ui.fragment.game.PreGameFragment;
import hr.nullteam.rsc.ui.presenter.BusPresenter;
import hr.nullteam.rsc.ui.presenter.QrScanningFragmentPresenter;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PreGameFragmentPresenter extends BusPresenter<PreGameFragment> {

    @Inject
    TeamApi teamApi;

    private Subscription teamSubscription;

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

    private void onTeamSuccess(Team team) {
        PreGameFragment fragment = getView();
        if(fragment == null) {
            return;
        }
        fragment.setTeamData(team);
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
