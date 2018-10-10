package fr.neamar.ocmaster.loader;

import android.content.Context;

import java.util.ArrayList;

import fr.neamar.ocmaster.dataprovider.PhoneProvider;
import fr.neamar.ocmaster.pojo.PhonePojo;

public class LoadPhonePojos extends LoadPojos<PhonePojo> {

    public LoadPhonePojos(Context context) {
        super(context, PhoneProvider.PHONE_SCHEME);
    }

    @Override
    protected ArrayList<PhonePojo> doInBackground(Void... params) {
        return null;
    }
}
