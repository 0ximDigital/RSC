package hr.nullteam.rsc.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import eu.livotov.labs.android.camview.ScannerLiveView;
import hr.nullteam.rsc.R;
import hr.nullteam.rsc.ui.module.FragmentComponent;
import hr.nullteam.rsc.ui.presenter.QrScanningFragmentPresenter;
import nucleus.factory.PresenterFactory;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(QrScanningFragmentPresenter.class)
public class QrScanningFragment extends DaggerFragment<QrScanningFragmentPresenter> {

    public static final String TAG = QrScanningFragment.class.getSimpleName();

    public static QrScanningFragment newInstance() {
        QrScanningFragment qrScanningFragment = new QrScanningFragment();
        return qrScanningFragment;
    }

    @Bind(R.id.qr_scanner_view)
    ScannerLiveView scannerView;

    @Override
    protected void setUpPresenter() {
        final PresenterFactory<QrScanningFragmentPresenter> superFactory = super.getPresenterFactory();
        setPresenterFactory(() -> {
            QrScanningFragmentPresenter presenter = superFactory.createPresenter();
            getFragmentComponent().inject(presenter);
            return presenter;
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        extractArguments();
    }

    @Override
    public void onResume() {
        super.onResume();
        setupScannerView();
    }

    private void extractArguments() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_qr_scanner, container, false);
        injectViews(fragmentView);
        return fragmentView;
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    private void setupScannerView() {
        // TODO scannerView.setHudImageResource();
        scannerView.setScannerViewEventListener(getPresenter().getScannerListener());
        scannerView.setPlaySound(false);         // TODO ?
        scannerView.startScanner();
    }

    @Override
    public void onPause() {
        super.onPause();
        scannerView.stopScanner();
    }

}
