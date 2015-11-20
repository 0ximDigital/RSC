package hr.nullteam.rsc.util.ui;

import android.content.Context;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;

import hr.nullteam.rsc.R;

public final class TypefacedSwitch extends SwitchCompat implements TypefacedView {
    public TypefacedSwitch(Context context) {
        super(context);
    }

    public TypefacedSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypefaceUtils.extractAndApplyTypeface(this, context, attrs, R.styleable.TypefacedSwitch,
                R.styleable.TypefacedTextView_typeface);
    }

    public TypefacedSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypefaceUtils.extractAndApplyTypeface(this, context, attrs, R.styleable.TypefacedSwitch,
                R.styleable.TypefacedTextView_typeface);
    }
}
