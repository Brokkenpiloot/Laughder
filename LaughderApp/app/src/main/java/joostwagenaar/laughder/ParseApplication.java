package joostwagenaar.laughder;

/**
 * Created by startklaar on 5-1-2016.
 */

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.Parse;

public class ParseApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        // [Optional] Power your app with Local Datastore. For more info, go to
        // https://parse.com/docs/android/guide#local-datastore
        Parse.enableLocalDatastore(this);
        Parse.initialize(this);
        Log.d("Joost", "Parse up and running");
    }
}


