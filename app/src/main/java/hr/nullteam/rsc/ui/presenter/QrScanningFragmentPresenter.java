package hr.nullteam.rsc.ui.presenter;

import android.util.Log;

import javax.inject.Inject;

import eu.livotov.labs.android.camview.ScannerLiveView;
import hr.nullteam.rsc.ui.fragment.QrScanningFragment;
import hr.nullteam.rsc.util.ToastUtils;

public class QrScanningFragmentPresenter extends BusPresenter<QrScanningFragment> {

    @Inject
    ToastUtils toastUtils;

    private static final String TAG = QrScanningFragmentPresenter.class.getSimpleName();

    public ScannerLiveView.ScannerViewEventListener getScannerListener() {
        return scannerListener;
    }

    private ScannerLiveView.ScannerViewEventListener scannerListener = new ScannerLiveView.ScannerViewEventListener() {
        @Override
        public void onScannerStarted(ScannerLiveView scanner) {
            Log.w(TAG, "Started scan");
        }

        @Override
        public void onScannerStopped(ScannerLiveView scanner) {
            Log.w(TAG, "Stoped scan");
        }

        @Override
        public void onScannerError(Throwable err) {
            Log.w(TAG, "Scan error -> " + err.getMessage());
            Log.w(TAG, "Scan error -> " + err.getCause());
            err.printStackTrace();
        }

        @Override
        public void onCodeScanned(String data) {
            Log.w(TAG, "Code scanned -> " + data);
            fireQrScannedEvent(data);
            toastUtils.showToast(data);
        }
    };

    private void fireQrScannedEvent(String data) {
        bus.post(new QrScannedEvent(data));
    }

    public static final class QrScannedEvent {
        public final String data;
        public QrScannedEvent(String data) {
            this.data = data;
        }
    }
}
