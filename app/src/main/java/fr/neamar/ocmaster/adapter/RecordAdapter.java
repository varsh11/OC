package fr.neamar.ocmaster.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.PopupMenu;

import java.util.ArrayList;

import fr.neamar.ocmaster.OCApplication;
import fr.neamar.ocmaster.result.AppResult;
import fr.neamar.ocmaster.result.ContactsResult;
import fr.neamar.ocmaster.result.PhoneResult;
import fr.neamar.ocmaster.result.Result;
import fr.neamar.ocmaster.result.SearchResult;
import fr.neamar.ocmaster.result.SettingsResult;
import fr.neamar.ocmaster.result.ShortcutsResult;
import fr.neamar.ocmaster.result.TogglesResult;
import fr.neamar.ocmaster.searcher.QueryInterface;

public class RecordAdapter extends ArrayAdapter<Result> {

    private final QueryInterface parent;
    /**
     * Array list containing all the results currently displayed
     */
    private ArrayList<Result> results = new ArrayList<>();

    public RecordAdapter(Context context, QueryInterface parent, int textViewResourceId,
                         ArrayList<Result> results) {
        super(context, textViewResourceId, results);

        this.parent = parent;
        this.results = results;
    }

    public int getViewTypeCount() {
        return 7;
    }

    public int getItemViewType(int position) {
        if (results.get(position) instanceof AppResult)
            return 0;
        else if (results.get(position) instanceof SearchResult)
            return 1;
        else if (results.get(position) instanceof ContactsResult)
            return 2;
        else if (results.get(position) instanceof TogglesResult)
            return 3;
        else if (results.get(position) instanceof SettingsResult)
            return 4;
        else if (results.get(position) instanceof PhoneResult)
            return 5;
        else if (results.get(position) instanceof ShortcutsResult)
            return 6;
        else
            return -1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return results.get(position).display(getContext(), results.size() - position, convertView);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onLongClick(final int pos, View v) {
        // Popup menu is not available before Honeycomb.
        // We simply remove the item from history
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            removeResult(results.get(pos));
            return;
        }

        PopupMenu menu = results.get(pos).getPopupMenu(getContext(), this, v);

        //check if menu contains elements and if yes show it
        if (menu.getMenu().size() > 0) {
            menu.show();
        }
    }

    public void onClick(final int position, View v) {
        final Result result;

        try {
            result = results.get(position);
            result.launch(getContext(), v);
        } catch (ArrayIndexOutOfBoundsException ignored) {
            return;
        }

        // Record the launch after some period,
        // * to ensure the animation runs smoothly
        // * to avoid a flickering -- launchOccurred will refresh the list
        // Thus TOUCH_DELAY * 3
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                parent.launchOccurred(results.size() - position, result);
            }
        }, OCApplication.TOUCH_DELAY * 3);

    }

    public void removeResult(Result result) {
        results.remove(result);
        result.deleteRecord(getContext());
        notifyDataSetChanged();
    }
}
