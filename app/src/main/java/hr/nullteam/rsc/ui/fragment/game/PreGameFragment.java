package hr.nullteam.rsc.ui.fragment.game;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import hr.nullteam.rsc.R;
import hr.nullteam.rsc.business.api.model.Team;
import hr.nullteam.rsc.ui.fragment.DaggerFragment;
import hr.nullteam.rsc.ui.module.FragmentComponent;
import hr.nullteam.rsc.ui.presenter.game.PreGameFragmentPresenter;
import nucleus.factory.PresenterFactory;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(PreGameFragmentPresenter.class)
public class PreGameFragment extends DaggerFragment<PreGameFragmentPresenter> {

    public static String TAG = PreGameFragment.class.getSimpleName();

    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    @Bind(R.id.team_details_container)
    FrameLayout teamDetailsContainer;

    @Bind(R.id.team_name_text)
    TextView teamNamelabel;

    private Team team;

    public static PreGameFragment newInstance() {
        PreGameFragment fragment = new PreGameFragment();
        return fragment;
    }

    @Override
    protected void setUpPresenter() {
        final PresenterFactory<PreGameFragmentPresenter> superFactory = super.getPresenterFactory();
        setPresenterFactory(() -> {
            PreGameFragmentPresenter presenter = superFactory.createPresenter();
            getFragmentComponent().inject(presenter);
            return presenter;
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_pre_game, container, false);
        injectViews(fragmentView);
        teamDetailsContainer.setVisibility(View.INVISIBLE);
        return fragmentView;
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    public void setTeamData(Team team) {
        this.team = team;
        populateData();
    }

    private void populateData() {
        progressBar.setVisibility(View.INVISIBLE);
        teamDetailsContainer.setVisibility(View.VISIBLE);
        teamNamelabel.setText(team.getName());
    }

    @OnClick(R.id.confirm_team_button)
    public void onTeamConfirmationClick() {
        getPresenter().onTeamConfirmationClick();
    }
}
