package hr.nullteam.rsc.ui.fragment;

import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import hr.nullteam.rsc.ui.activity.DaggerActivity;
import hr.nullteam.rsc.ui.module.ComponentFactory;
import hr.nullteam.rsc.ui.module.FragmentComponent;
import nucleus.presenter.Presenter;
import nucleus.view.NucleusSupportFragment;

public abstract class DaggerFragment<T extends Presenter> extends NucleusSupportFragment<T> {

    private FragmentComponent fragmentComponent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentComponent = getFragmentComponent();
        inject(fragmentComponent);
        setUpPresenter();
    }

    public FragmentComponent getFragmentComponent() {
        if (fragmentComponent == null) {
            fragmentComponent = ComponentFactory.createFragmentComponent(getDaggerActivity().getActivityComponent());
        }
        return fragmentComponent;
    }

    protected void injectViews(View view) {
        ButterKnife.bind(this, view);
    }

    public DaggerActivity<Presenter> getDaggerActivity() {
        return (DaggerActivity) getActivity();
    }

    /* Implementation should look like this:
    @Override
    protected void setUpPresenter() {
        final PresenterFactory<ExamplePresenter> superFactory = super.getPresenterFactory();
        setPresenterFactory(() -> {
            ExamplePresenter presenter = superFactory.createPresenter();
            getFragmentComponent().inject(presenter);
            return presenter;
        });
    }
    Just switch "ExamplePresenter" with required presenter.
     */
    protected abstract void setUpPresenter();

    protected abstract void inject(FragmentComponent fragmentComponent);

    @Override
    public void onDestroy() {
        ButterKnife.unbind(this);
        getPresenter().destroy();
        super.onDestroy();
    }
}
