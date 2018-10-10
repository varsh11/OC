package fr.neamar.ocmaster.searcher;


import android.os.AsyncTask;

import java.util.List;

import fr.neamar.ocmaster.MainActivity;
import fr.neamar.ocmaster.pojo.Pojo;
import fr.neamar.ocmaster.result.Result;

public abstract class Searcher extends AsyncTask<Void, Void, List<Pojo>> {

    final MainActivity activity;

    Searcher(MainActivity activity) {
        super();
        this.activity = activity;
    }

    @Override
    protected void onPostExecute(List<Pojo> pojos) {
        super.onPostExecute(pojos);
        activity.adapter.clear();

        if (pojos != null) {
            for (int i = pojos.size() - 1; i >= 0; i--) {
                activity.adapter.add(Result.fromPojo(activity, pojos.get(i)));
            }
        }
        activity.resetTask();
    }
}
