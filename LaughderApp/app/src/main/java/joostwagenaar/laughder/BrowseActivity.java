package joostwagenaar.laughder;

/**
 * Created by startklaar on 5-1-2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    String userID;
    String displayedUserID;
    TextView userTextTextView;
    TextView userYouTubeURLTextView;
    ParseUser user;
    ParseUser displayedUser;
    Random randomizer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Joost", "Browse onCreate started");
        setContentView(R.layout.activity_browse);
        Log.d("Joost", "Initializing users");
        user = ParseUser.getCurrentUser();
        Log.d("Joost", "Initializing Text Views");
        userTextTextView = (TextView) findViewById(R.id.profileTextTextView);
        userYouTubeURLTextView = (TextView) findViewById(R.id.profileYTURLTextView);
        Log.d("Joost", "Getting ready for query");

        // Create a list of all parse users.
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> allParseUsers, ParseException e) {
                if (e == null) {
                    // The query was successful.
                    Log.d("Joost", "Barack found");
                    randomizer = new Random();
                    displayedUser = allParseUsers.get(randomizer.nextInt(allParseUsers.size()));
                    userTextDisplay = displayedUser.getString("profileText");
                    userTextTextView.setText(userTextDisplay);
                    userYouTubeURL = displayedUser.getString("youTubeURL");
                    userYouTubeURLTextView.setText(userYouTubeURL);


                } else {
                    // Something went wrong.
                    Log.d("Joost", "Barack NOT found");
                }
            }
        });
    }

    public void onLikeButtonClicked(View view) {

        // Add a keyvalue pair with the declined User's ID as key and a 1 as value.
        displayedUserID = displayedUser.getString("objectId");
        ParseUser.getCurrentUser().put(displayedUserID, 1);
        Log.d("Joost", "This poor user has been declined: " + displayedUser.getString("objectId"));

        // Check if the like is mutual. If so, add eachother to eachother's matchlist.
        if (displayedUser.getInt(user.getString("objectId")) == 1){
            user.getList("matches").add(displayedUser.getString("objectId"));
            displayedUser.getList("matches").add(user.getString("objectId"));
        }

        // Clear TextViews to give a sense of progress to the user.
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
                    displayedUser = allParseUsers.get(randomizer.nextInt(allParseUsers.size()));
                    userTextDisplay = displayedUser.getString("profileText");
                    userTextTextView.setText(userTextDisplay);
                    userYouTubeURL = displayedUser.getString("youTubeURL");
                    userYouTubeURLTextView.setText(userYouTubeURL);


                } else {
                    // Something went wrong.
                    Log.d("Joost", "Next Barack NOT found");
                }
            }
        });

    }

    public void onDeclineButtonClicked(View view) {

        // Add a keyvalue pair with the declined User's ID as key and a 0 as value.
        user.put(displayedUser.getString("objectId"), 0);
        Log.d("Joost", "This poor user has been declined: " + displayedUser.getString("objectId"));

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
                    displayedUser = allParseUsers.get(randomizer.nextInt(allParseUsers.size()));
                    userTextDisplay = displayedUser.getString("profileText");
                    userTextTextView.setText(userTextDisplay);
                    userYouTubeURL = displayedUser.getString("youTubeURL");
                    userYouTubeURLTextView.setText(userYouTubeURL);


                } else {
                    // Something went wrong.
                    Log.d("Joost", "Next Barack NOT found");
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
}
