package hr.nullteam.rsc.ui.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.OnClick;
import hr.nullteam.rsc.R;
import hr.nullteam.rsc.ui.fragment.DaggerFragment;
import hr.nullteam.rsc.ui.module.FragmentComponent;
import hr.nullteam.rsc.ui.presenter.main.MainFragmentPresenter;
import nucleus.factory.PresenterFactory;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(MainFragmentPresenter.class)
public class MainFragment extends DaggerFragment<MainFragmentPresenter> {

    public static String TAG = MainFragment.class.getSimpleName();

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    protected void setUpPresenter() {
        final PresenterFactory<MainFragmentPresenter> superFactory = super.getPresenterFactory();
        setPresenterFactory(() -> {
            MainFragmentPresenter presenter = superFactory.createPresenter();
            getFragmentComponent().inject(presenter);
            return presenter;
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_main, container, false);
        injectViews(fragmentView);
        return fragmentView;
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @OnClick(R.id.play_button)
    public void onPlayButtonClick() {
        getPresenter().onPlayButtonClick();
    }

    @OnClick(R.id.spectate_button)
    public void onSpectateButtonClick() {
        getPresenter().onSpectateButtonClick();
    }

    @OnClick(R.id.view_profile_button)
    public void onViewProfileClick() {
        getPresenter().onViewProfileClick();
    }

}
