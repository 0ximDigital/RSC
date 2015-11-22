package hr.nullteam.rsc.ui.fragment.game;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import butterknife.Bind;
import hr.nullteam.rsc.R;
import hr.nullteam.rsc.ui.fragment.DaggerFragment;
import hr.nullteam.rsc.ui.module.FragmentComponent;
import hr.nullteam.rsc.ui.presenter.game.PlayerGameFragmentPresenter;
import nucleus.factory.PresenterFactory;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(PlayerGameFragmentPresenter.class)
public class PlayerGameFragment extends DaggerFragment<PlayerGameFragmentPresenter> {

    public static String TAG = PlayerGameFragment.class.getSimpleName();

    @Bind(R.id.root_frame_layout)
    FrameLayout rootFrameLayout;

    public static PlayerGameFragment newInstance() {
        PlayerGameFragment fragment = new PlayerGameFragment();
        return fragment;
    }

    @Override
    protected void setUpPresenter() {
        final PresenterFactory<PlayerGameFragmentPresenter> superFactory = super.getPresenterFactory();
        setPresenterFactory(() -> {
            PlayerGameFragmentPresenter presenter = superFactory.createPresenter();
            getFragmentComponent().inject(presenter);
            return presenter;
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_player_game, container, false);
        injectViews(fragmentView);
        return fragmentView;
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    private void populateData() {
    }

}
