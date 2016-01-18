package joostwagenaar.laughder;

/**
 * Created by startklaar on 5-1-2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

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
    String userTextDisplay;
    String userYouTubeURL;
    TextView userTextTextView;
    TextView userYouTubeURLTextView;
    ParseUser user;
    ParseUser userToDisplay;
    Random randomizer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Joost", "Browse onCreate started");
        setContentView(R.layout.activity_browse);
        user = ParseUser.getCurrentUser();
        userTextTextView = (TextView) findViewById(R.id.profileTextTextView);
        userYouTubeURLTextView = (TextView) findViewById(R.id.profileYTURLTextView);

        // Create a list of all parse users.
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> allParseUsers, ParseException e) {
                if (e == null) {
                    // The query was successful.
                    Log.d("Joost", "Barack found");
                    randomizer = new Random();
                    userToDisplay = allParseUsers.get(randomizer.nextInt(allParseUsers.size()));
                    userTextDisplay = userToDisplay.getString("profileText");
                    userTextTextView.setText(userTextDisplay);
                    userYouTubeURL = userToDisplay.getString("youTubeURL");
                    userYouTubeURLTextView.setText(userYouTubeURL);


                } else {
                    // Something went wrong.
                    Log.d("Joost", "Barack NOT found");
                }
            }
        });
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

    public void onLikeButtonClicked(View view) {

        // Clear TextViews incase someone doesnt have a profile filled in.
        userTextTextView.setText("");
        userYouTubeURLTextView.setText("");

        // Choose a new random user.
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> allParseUsers, ParseException e) {
                if (e == null) {
                    // The query was successful.
                    Log.d("Joost", "Next Barack found");
                    randomizer = new Random();
                    userToDisplay = allParseUsers.get(randomizer.nextInt(allParseUsers.size()));
                    userTextDisplay = userToDisplay.getString("profileText");
                    userTextTextView.setText(userTextDisplay);
                    userYouTubeURL = userToDisplay.getString("youTubeURL");
                    userYouTubeURLTextView.setText(userYouTubeURL);


                } else {
                    // Something went wrong.
                    Log.d("Joost", "Next Barack NOT found");
                }
            }
        });

    }

    public void onDeclineButtonClicked(View view) {

        // Clear TextViews incase someone doesnt have a profile filled in.
        userTextTextView.setText("");
        userYouTubeURLTextView.setText("");

        // Choose a new random user.
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> allParseUsers, ParseException e) {
                if (e == null) {
                    // The query was successful.
                    Log.d("Joost", "Next Barack found");
                    randomizer = new Random();
                    userToDisplay = allParseUsers.get(randomizer.nextInt(allParseUsers.size()));
                    userTextDisplay = userToDisplay.getString("profileText");
                    userTextTextView.setText(userTextDisplay);
                    userYouTubeURL = userToDisplay.getString("youTubeURL");
                    userYouTubeURLTextView.setText(userYouTubeURL);


                } else {
                    // Something went wrong.
                    Log.d("Joost", "Next Barack NOT found");
                }
            }
        });
    }
}
