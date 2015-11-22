package hr.nullteam.rsc.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import javax.inject.Inject;

import hr.nullteam.rsc.R;
import hr.nullteam.rsc.ui.fragment.MapFragment;
import hr.nullteam.rsc.ui.fragment.game.PlayerGameFragment;
import hr.nullteam.rsc.ui.module.ActivityComponent;
import hr.nullteam.rsc.ui.presenter.game.PlayerGameActivityPresenter;
import nucleus.factory.PresenterFactory;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(PlayerGameActivityPresenter.class)
public class PlayerGameActivity extends PresenterActivity<PlayerGameActivityPresenter> {

    @Inject
    FragmentManager fragmentManager;

    private MapFragment mapFragment;
    private PlayerGameFragment playerGameFragment;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, PlayerGameActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        if (savedInstanceState == null) {
            mapFragment = MapFragment.newInstance();
            fragmentManager.beginTransaction().add(R.id.activity_container, mapFragment, MapFragment.TAG).commit();
            playerGameFragment = PlayerGameFragment.newInstance();
            fragmentManager.beginTransaction().add(R.id.activity_container, playerGameFragment, PlayerGameFragment.TAG).commit();
        } else {
            mapFragment = (MapFragment) fragmentManager.findFragmentByTag(MapFragment.TAG);
            playerGameFragment = (PlayerGameFragment) fragmentManager.findFragmentByTag(PlayerGameFragment.TAG);
        }
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void setUpPresenter() {
        final PresenterFactory<PlayerGameActivityPresenter> superFactory = super.getPresenterFactory();
        setPresenterFactory(() -> {
            PlayerGameActivityPresenter presenter = superFactory.createPresenter();
            getActivityComponent().inject(presenter);
            return presenter;
        });
    }

}
