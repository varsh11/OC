package fr.neamar.ocmaster.searcher;

import java.util.ArrayList;
import java.util.List;

import fr.neamar.ocmaster.MainActivity;
import fr.neamar.ocmaster.pojo.Pojo;

/**
 * Retrieve pojos from history
 */
public class NullSearcher extends Searcher {

    public NullSearcher(MainActivity activity) {
        super(activity);
    }

    @Override
    protected List<Pojo> doInBackground(Void... voids) {
        return new ArrayList<>();
    }
}
