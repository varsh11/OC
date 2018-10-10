package fr.neamar.ocmaster.searcher;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

import fr.neamar.ocmaster.OCApplication;
import fr.neamar.ocmaster.MainActivity;
import fr.neamar.ocmaster.pojo.Pojo;

/**
 * Retrieve pojos from history
 */
public class HistorySearcher extends Searcher {
    private static final int MAX_RECORDS = 25;
    private SharedPreferences prefs;

    public HistorySearcher(MainActivity activity) {
        super(activity);
        prefs = PreferenceManager.getDefaultSharedPreferences(activity);
    }

    @Override
    protected List<Pojo> doInBackground(Void... voids) {
        // Ask for records
        boolean smartHistory = !prefs.getString("history-mode", "recency").equals("recency");
        boolean excludeFavorites = prefs.getBoolean("exclude-favorites", false);

        //Gather favorites
        ArrayList<Pojo> favoritesPojo = new ArrayList<Pojo>(0);
        if(excludeFavorites){
            favoritesPojo = OCApplication.getDataHandler(activity).getFavorites(activity.tryToRetrieve);
        }

        return OCApplication.getDataHandler(activity).getHistory(activity, MAX_RECORDS, smartHistory, favoritesPojo);
    }
}
