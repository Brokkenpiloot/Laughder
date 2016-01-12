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
        // Starts Parse in background activity, which runs at start up.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this);
        Log.d("Joost", "Parse up and running");
    }
}


