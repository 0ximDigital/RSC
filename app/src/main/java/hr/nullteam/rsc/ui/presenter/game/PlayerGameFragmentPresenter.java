package hr.nullteam.rsc.ui.presenter.game;

import javax.inject.Inject;

import hr.nullteam.rsc.ui.fragment.game.PlayerGameFragment;
import hr.nullteam.rsc.ui.presenter.BusPresenter;
import hr.nullteam.rsc.util.PreferenceUtils;

public class PlayerGameFragmentPresenter extends BusPresenter<PlayerGameFragment> {

    @Inject
    PreferenceUtils preferenceUtils;

    @Override
    protected void onTakeView(PlayerGameFragment playerGameFragment) {
        super.onTakeView(playerGameFragment);

        long teamId = preferenceUtils.getTeamId();
        fireJoinTeamEvent(teamId);
    }

    private void fireJoinTeamEvent(long teamId) {
        bus.post(new JoinTeamEvent(teamId));
    }

    public static final class JoinTeamEvent {
        public final long teamId;

        public JoinTeamEvent(long teamId) {
            this.teamId = teamId;
        }
    }
}
