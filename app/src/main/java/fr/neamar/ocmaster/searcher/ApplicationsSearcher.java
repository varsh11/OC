package fr.neamar.ocmaster.searcher;

import java.util.List;

import fr.neamar.ocmaster.OCApplication;
import fr.neamar.ocmaster.MainActivity;
import fr.neamar.ocmaster.pojo.Pojo;

/**
 * Returns the list of all applications on the system
 */
public class ApplicationsSearcher extends Searcher {
    public ApplicationsSearcher(MainActivity activity) {
        super(activity);
    }

    @Override
    protected List<Pojo> doInBackground(Void... voids) {
        // Ask for records
        return OCApplication.getDataHandler(activity).getApplications();
    }
}
