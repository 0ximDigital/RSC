package hr.nullteam.rsc.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.squareup.otto.Bus;

import javax.inject.Inject;

import hr.nullteam.rsc.application.RscApplication;

public class GestureFrameLayout extends FrameLayout {

    private final int SWIPE_MIN_DISTANCE = 80;
    private final int SWIPE_THRESHOLD_VELOCITY = 150;

    public enum FlingDirection {
        UP, DOWN, LEFT, RIGHT
    }

    @Inject
    Bus bus;

    public GestureFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        gestureDetector = new GestureDetector(context, new GestureListener());
        ((RscApplication) context.getApplicationContext()).getApplicationComponent().inject(this);
    }

    private GestureDetector gestureDetector;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        gestureDetector.onTouchEvent(ev);
        return false;
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            try {
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    fireFlingEvent(FlingDirection.LEFT);
                    return true;
                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    fireFlingEvent(FlingDirection.RIGHT);
                    return true;
                } else if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                    fireFlingEvent(FlingDirection.UP);
                    return true;
                } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                    fireFlingEvent(FlingDirection.DOWN);
                    return true;
                }
            } catch (Exception e) {
                // nothing
            }
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            return false;
        }
    }

    private void fireFlingEvent(FlingDirection direction) {
        bus.post(new FlingEvent(direction));
    }

    public static final class FlingEvent {

        public final FlingDirection flingDirection;

        public FlingEvent(FlingDirection flingDirection) {
            this.flingDirection = flingDirection;
        }
    }

}
