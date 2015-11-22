package hr.nullteam.rsc.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import javax.inject.Inject;

import hr.nullteam.rsc.R;
import hr.nullteam.rsc.ui.fragment.QrScanningFragment;
import hr.nullteam.rsc.ui.fragment.game.PreGameFragment;
import hr.nullteam.rsc.ui.module.ActivityComponent;
import hr.nullteam.rsc.ui.presenter.main.PreGameActivityPresenter;
import nucleus.factory.PresenterFactory;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(PreGameActivityPresenter.class)
public class PreGameActivity extends PresenterActivity<PreGameActivityPresenter> {

    @Inject
    FragmentManager fragmentManager;

    private QrScanningFragment qrScanningFragment;
    private PreGameFragment preGameFragment;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, PreGameActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        if (savedInstanceState == null) {
            preGameFragment = PreGameFragment.newInstance();
            fragmentManager.beginTransaction().add(R.id.activity_container, preGameFragment, PreGameFragment.TAG).commit();
            qrScanningFragment = QrScanningFragment.newInstance();
            fragmentManager.beginTransaction().add(R.id.activity_container, qrScanningFragment, QrScanningFragment.TAG).commit();
        } else {
            preGameFragment = (PreGameFragment) fragmentManager.findFragmentByTag(PreGameFragment.TAG);
            qrScanningFragment = (QrScanningFragment) fragmentManager.findFragmentByTag(QrScanningFragment.TAG);
        }
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void setUpPresenter() {
        final PresenterFactory<PreGameActivityPresenter> superFactory = super.getPresenterFactory();
        setPresenterFactory(() -> {
            PreGameActivityPresenter presenter = superFactory.createPresenter();
            getActivityComponent().inject(presenter);
            return presenter;
        });
    }

    public void showQrScanner() {
        if (qrScanningFragment == null) {
            qrScanningFragment = QrScanningFragment.newInstance();
        }
        fragmentManager.beginTransaction().add(R.id.activity_container, qrScanningFragment, QrScanningFragment.TAG).commit();
    }

    public void showProcessing() {
        fragmentManager.beginTransaction().remove(qrScanningFragment).commit();
    }

}
