package hr.nullteam.rsc.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import javax.inject.Inject;

import hr.nullteam.rsc.R;
import hr.nullteam.rsc.ui.fragment.TodoFragment;
import hr.nullteam.rsc.ui.module.ActivityComponent;
import hr.nullteam.rsc.ui.presenter.MainPresenter;
import nucleus.factory.PresenterFactory;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(MainPresenter.class)
public final class MainActivity extends PresenterActivity<MainPresenter> {

    @Inject
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            fragmentManager.beginTransaction().add(R.id.activity_container, TodoFragment.newInstance()).commit();
        }
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void setUpPresenter() {
        final PresenterFactory<MainPresenter> superFactory = super.getPresenterFactory();
        setPresenterFactory(() -> {
            MainPresenter presenter = superFactory.createPresenter();
            getActivityComponent().inject(presenter);
            return presenter;
        });
    }
}
