package hr.nullteam.rsc.ui.fragment.game;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

    private GestureDetector gestureDetector;
    private GestureListener gestureListener;

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
        initialiseGestureDetector(getActivity());
        return fragmentView;
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    private void initialiseGestureDetector(Activity activity) {
        this.gestureListener = new GestureListener();
        this.gestureDetector = new GestureDetector(activity, gestureListener);
        rootFrameLayout.setOnTouchListener((v, event) -> gestureDetector.onTouchEvent(event));
    }

    private void populateData() {
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private final int SWIPE_MIN_DISTANCE = 120;
        private final int SWIPE_MAX_OFF_PATH = 250;
        private final int SWIPE_THRESHOLD_VELOCITY = 200;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.i("GESUTE", "Some fling");
            try {
                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                    return false;
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    Log.i("GESTURE", "Right to Left");
                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    Log.i("GESTURE", "Left to Right");
                } else if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                    Log.i("GESTURE", "Down up");
                } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                    Log.i("GESTURE", "Top down");
                }
            } catch (Exception e) {
                // nothing
            }

            return super.onFling(e1, e2, velocityX, velocityY);
        }

    }

}
