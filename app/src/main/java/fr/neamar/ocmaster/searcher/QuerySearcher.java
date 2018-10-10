package fr.neamar.ocmaster.searcher;

import java.util.List;

import fr.neamar.ocmaster.OCApplication;
import fr.neamar.ocmaster.MainActivity;
import fr.neamar.ocmaster.pojo.Pojo;

/**
 * AsyncTask retrieving data from the providers and updating the view
 *
 * @author dorvaryn
 */
public class QuerySearcher extends Searcher {
    private static final int MAX_RECORDS = 15;

    private final String query;

    public QuerySearcher(MainActivity activity, String query) {
        super(activity);
        this.query = query;
    }


    @Override
    protected List<Pojo> doInBackground(Void... voids) {
        // Ask for records
        final List<Pojo> pojos = OCApplication.getDataHandler(activity).getResults(
                activity, query);

        // Trim items
        if (pojos.size() > MAX_RECORDS) {
            return pojos.subList(0, MAX_RECORDS);
        }

        return pojos;
    }
}
