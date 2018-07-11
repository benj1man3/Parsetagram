package me.benj1man3.mainactivity;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

import me.benj1man3.mainactivity.model.Post;

public class ParseApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);

        final Parse.Configuration configuration= new Parse.Configuration.Builder(this)
                .applicationId("benjamin")
                .clientKey("benjiman4573")
                .server("http://benj1man3-fbu-instagram.herokuapp.com/parse")
                .build();

        Parse.initialize(configuration);

    }
}
