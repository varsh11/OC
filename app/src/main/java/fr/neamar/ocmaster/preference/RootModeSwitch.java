package fr.neamar.ocmaster.preference;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.preference.SwitchPreference;
import android.util.AttributeSet;

import fr.neamar.ocmaster.OCApplication;
import fr.neamar.ocmaster.R;

public class RootModeSwitch extends SwitchPreference {

    public RootModeSwitch(Context context) {
        this(context, null);
    }

    public RootModeSwitch(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.switchPreferenceStyle);
    }

    public RootModeSwitch(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onClick() {
        if (!isChecked() && !OCApplication.getRootHandler(getContext()).isRootAvailable()) {
            //show error dialog
            new AlertDialog.Builder(getContext()).setMessage(R.string.root_mode_error)
                    .setPositiveButton(android.R.string.ok, new OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // does nothing
                        }
                    }).show();
        } else {
            super.onClick();
        }

        try {
            OCApplication.resetRootHandler(getContext());
        } catch (NullPointerException e) {
            // uninitialized roothandler.
        }
    }
}
