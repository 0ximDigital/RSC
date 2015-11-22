package hr.nullteam.rsc.ui.presenter.game;

import android.util.Log;
import android.view.MotionEvent;

import hr.nullteam.rsc.ui.activity.PlayerGameActivity;
import hr.nullteam.rsc.ui.presenter.BusPresenter;

public class PlayerGameActivityPresenter extends BusPresenter<PlayerGameActivity> {

    private final int SWIPE_MIN_DISTANCE = 120;
    private final int SWIPE_MAX_OFF_PATH = 250;
    private final int SWIPE_THRESHOLD_VELOCITY = 200;

    public void onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        try {
            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)

            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                fireFlingEvent(FlingEvent.FlingDirection.LEFT);
            } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                fireFlingEvent(FlingEvent.FlingDirection.RIGHT);
            } else if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE
                    && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                fireFlingEvent(FlingEvent.FlingDirection.UP);
            } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE
                    && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                fireFlingEvent(FlingEvent.FlingDirection.DOWN);
            }
        } catch (Exception e) {
            // nothing
        }
    }

    private void fireFlingEvent(FlingEvent.FlingDirection direction){
        Log.e("FLING", "Sending fling in " + direction + " direction");
        bus.post(new FlingEvent(direction));
    }

    public static final class FlingEvent {
        public enum FlingDirection {
            UP, DOWN, LEFT, RIGHT
        }
        public final FlingDirection flingDirection;

        public FlingEvent(FlingDirection flingDirection) {
            this.flingDirection = flingDirection;
        }
    }

}
