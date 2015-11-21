package hr.nullteam.rsc.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import javax.inject.Inject;

import hr.nullteam.rsc.R;
import hr.nullteam.rsc.ui.fragment.main.MainFragment;
import hr.nullteam.rsc.ui.module.ActivityComponent;
import hr.nullteam.rsc.ui.presenter.main.MainActivityPresenter;
import nucleus.factory.PresenterFactory;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(MainActivityPresenter.class)
public class MainActivity extends PresenterActivity<MainActivityPresenter> {

    @Inject
    FragmentManager fragmentManager;

    private MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            mainFragment = MainFragment.newInstance();
            fragmentManager.beginTransaction().add(R.id.activity_container, mainFragment, MainFragment.TAG).commit();
        } else {
            mainFragment = (MainFragment) fragmentManager.findFragmentByTag(MainFragment.TAG);
        }
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void setUpPresenter() {
        final PresenterFactory<MainActivityPresenter> superFactory = super.getPresenterFactory();
        setPresenterFactory(() -> {
            MainActivityPresenter presenter = superFactory.createPresenter();
            getActivityComponent().inject(presenter);
            return presenter;
        });
    }

}
