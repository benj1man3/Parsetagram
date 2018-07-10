package me.benj1man3.mainactivity;

import android.app.Application;

import com.parse.Parse;

public class ParseApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        final Parse.Configuration configuration= new Parse.Configuration.Builder(this)
                .applicationId("benjamin")
                .clientKey("benjiman4573")
                .server("http://benj1man3-fbu-instagram.herokuapp.com/parse")
                .build();
        Parse.initialize(configuration);

    }
}
