package fr.neamar.ocmaster.preference;

import android.content.Context;
import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.widget.Toast;

import fr.neamar.ocmaster.OCApplication;
import fr.neamar.ocmaster.R;
import fr.neamar.ocmaster.db.DB;

public class ResetPreference extends DialogPreference {

    public ResetPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        super.onClick(dialog, which);
        if (which == DialogInterface.BUTTON_POSITIVE) {
            getContext().deleteDatabase(DB.DB_NAME);
            OCApplication.getDataHandler(getContext()).reloadAll();
            PreferenceManager.getDefaultSharedPreferences(getContext()).edit()
                    .putBoolean("layout-updated", true).commit();

            Toast.makeText(getContext(), R.string.history_erased, Toast.LENGTH_LONG).show();
        }

    }

}
