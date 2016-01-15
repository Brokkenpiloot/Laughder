package joostwagenaar.laughder;

/**
 * Created by startklaar on 5-1-2016.
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;
import java.util.Random;

public class BrowseActivity extends AppCompatActivity {

    Intent logOut;
    Intent viewMatches;
    Intent editProfile;
    ParseUser user;
    Object userToDisplay;
    List userList;
    Random randomizer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Joost", "Browse onCreate started");
        setContentView(R.layout.activity_browse);
        user = ParseUser.getCurrentUser();

        // Create a list of all parse users.
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> allParseUsers, ParseException e) {
                if (e == null) {
                    // The query was successful.
                    Log.d("Joost", "Barack found");
                    userList = allParseUsers;
                } else {
                    // Something went wrong.
                    Log.d("Joost", "Barack NOT found");
                }
            }
        });

        randomizer = new Random();
        userToDisplay = userList.get(randomizer.nextInt(userList.size()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_browse, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Check which options item is clicked.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.log_out) {
            // Go back to MainActivity.
            logOut = new Intent(this, LogInActivity.class);
            // Add Parse log out function.
            startActivity(logOut);
            finish();
        }

        if (id == R.id.view_matches) {
            // Go to the matches screen.
            viewMatches = new Intent(this, MatchActivity.class);
            startActivity(viewMatches);
            finish();
        }

        if (id == R.id.edit_profile) {
            // Go to the edit profile screen.
            editProfile = new Intent(this, EditProfileActivity.class);
            startActivity(editProfile);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
