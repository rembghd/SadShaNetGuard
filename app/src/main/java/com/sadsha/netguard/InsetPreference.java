package com.sadsha.netguard;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Space;

public class InsetPreference extends Preference {

    public InsetPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setSelectable(false);
    }

    @Override
    protected View onCreateView(ViewGroup parent) {
        super.onCreateView(parent);
        Space space = new Space(getContext());
        space.setMinimumHeight((int) (48 * getContext().getResources().getDisplayMetrics().density));
        return space;
    }
}