package hr.nullteam.rsc.util.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import hr.nullteam.rsc.R;

public final class TypefacedTextView extends TextView implements TypefacedView {

    public TypefacedTextView(Context context) {
        super(context);
    }

    public TypefacedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypefaceUtils.extractAndApplyTypeface(this, context, attrs, R.styleable.TypefacedTextView,
                R.styleable.TypefacedTextView_typeface);
    }

    public TypefacedTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypefaceUtils.extractAndApplyTypeface(this, context, attrs, R.styleable.TypefacedTextView,
                R.styleable.TypefacedTextView_typeface);
    }

    public void setTypeface(String typeface) {
        TypefaceUtils.applyTypefaceToTextView(this, typeface);
    }

}