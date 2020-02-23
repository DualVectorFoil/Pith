package com.dualvector.pith.mvp.ui.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.dualvector.pith.R;

public class PasswordEditText extends EditText {

    private boolean mVisibililty = false;

    private Drawable mVisibilityDrawable;

    private OnChangePwdStatusListener mListener;

    public PasswordEditText(Context context) {
        this(context, null);
    }

    public PasswordEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Drawable[] compoundDrawables = getCompoundDrawables();
        mVisibilityDrawable = compoundDrawables[2];
        if (mVisibilityDrawable == null) {
            mVisibilityDrawable = getResources().getDrawable(R.mipmap.ic_password_hide_icon);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {
                if (event.getX() > getWidth() - (mVisibilityDrawable.getIntrinsicWidth() + getCompoundPaddingRight())
                        && event.getX() < getWidth() - (getTotalPaddingRight() - getCompoundPaddingRight())) {
                    changePwdStatus();
                    if (mListener != null) {
                        mListener.onChange(this);
                    }
                }
            }
        }
        return super.onTouchEvent(event);
    }

    public boolean isVisibililty() {
        return mVisibililty;
    }

    public void changePwdStatus() {
        mVisibililty = !mVisibililty;
        if (mVisibililty) {
            mVisibilityDrawable = getResources().getDrawable(R.mipmap.ic_password_show_icon);
            this.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            mVisibilityDrawable = getResources().getDrawable(R.mipmap.ic_password_hide_icon);
            this.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        Spannable text = this.getText();
        if (text != null) {
            Selection.setSelection(text, text.length());
        }
        mVisibilityDrawable.setBounds(0, 0, mVisibilityDrawable.getMinimumWidth(),
                mVisibilityDrawable.getMinimumHeight());
        setCompoundDrawables(getCompoundDrawables()[0],
                getCompoundDrawables()[1], mVisibilityDrawable, getCompoundDrawables()[3]);
    }

    public void setOnChangePwdStatusListener(OnChangePwdStatusListener listener) {
        mListener = listener;
    }

    public interface OnChangePwdStatusListener {

        void onChange(PasswordEditText et);
    }
}
