package hr.nullteam.rsc.util.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;

import hr.nullteam.rsc.R;

public final class TypefacedToolbar extends Toolbar implements TypefacedView {

    private TypefacedTextView typefacedTitle;

    public TypefacedToolbar(Context context) {
        super(context);
        init(context, null);
    }

    public TypefacedToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TypefacedToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        checkTypeface();
        if (attrs != null) {
            TypedArray attributes = context.getTheme().obtainStyledAttributes(
                    attrs, R.styleable.TypefacedToolbar, 0, 0);
            try {
                setTitleTextAppearance(getContext(), attributes.getResourceId(R.styleable.TypefacedToolbar_titleTextAppearance, 0));
                TypefaceUtils.extractAndApplyTypeface(this, context, attrs, R.styleable.TypefacedToolbar,
                        R.styleable.TypefacedToolbar_typeface);
            } finally {
                attributes.recycle();
            }
        }
        addView(this.typefacedTitle);
    }

    @Override
    public void setTitle(int resId) {
        checkTypeface();
        typefacedTitle.setText(resId);
    }

    @Override
    public void setTitle(CharSequence title) {
        checkTypeface();
        typefacedTitle.setText(title);
    }

    @Override
    public void setTitleTextAppearance(Context context, int resId) {
        checkTypeface();
        typefacedTitle.setTextAppearance(context, resId);
    }

    @Override
    public void setTitleTextColor(int color) {
        checkTypeface();
        typefacedTitle.setTextColor(color);
    }

    @Override
    public CharSequence getTitle() {
        checkTypeface();
        return typefacedTitle.getText();
    }

    @Override
    public void setTypeface(Typeface typeface) {
        checkTypeface();
        typefacedTitle.setTypeface(typeface);
    }

    private void checkTypeface() {
        if (typefacedTitle == null) {
            typefacedTitle = new TypefacedTextView(getContext());
        }
    }
}
