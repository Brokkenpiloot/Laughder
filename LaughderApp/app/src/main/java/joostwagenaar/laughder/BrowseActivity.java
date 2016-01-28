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
import android.widget.Toast;

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
    String userNameDisplay;
    String userTextDisplay;
    String userYouTubeURL;
    String userID;
    String displayedUserId;
    TextView userNameTextView;
    TextView userTextTextView;
    TextView userYouTubeURLTextView;
    ParseUser user;
    ParseUser displayedUser;
    Random randomizer;
    List matches;
    int checker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        // Initialize all relevant variables and views.
        user = ParseUser.getCurrentUser();
        userID = user.getObjectId();
        userNameTextView = (TextView) findViewById(R.id.profileNameTextView);
        userTextTextView = (TextView) findViewById(R.id.profileTextTextView);
        userYouTubeURLTextView = (TextView) findViewById(R.id.profileYTURLTextView);
        checker = 0;

        Log.d("Joost", "Getting ready for query");

        // Create a list of all parse users.
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> allParseUsers, ParseException e) {
                if (e == null) {
                    // Keep randomizing until a user that is not current user is found.
                    while (checker == 0) {
                        Log.d("Joost", "Barack found");

                        // Pick a random entry in the list and extract the relevant information.
                        // Set the relevant views using this information.
                        randomizer = new Random();
                        displayedUser = allParseUsers.get(randomizer.nextInt(allParseUsers.size()));
                        userNameDisplay = displayedUser.getUsername();
                        userNameTextView.setText(userNameDisplay);
                        userTextDisplay = displayedUser.getString("profileText");
                        userTextTextView.setText(userTextDisplay);
                        userYouTubeURL = displayedUser.getString("youTubeURL");
                        userYouTubeURLTextView.setText(userYouTubeURL);
                        displayedUserId = displayedUser.getObjectId();
                        Log.d("Joost", " " + displayedUserId);

                        // If found user is not current user, leave the while loop.
                        if (!displayedUserId.equals(userID)) {
                            checker = 1;
                        }
                    }
                } else {
                    // Something went wrong.
                    Log.d("Joost", "Barack NOT found");
                }
            }
        });
    }

    public void onLikeButtonClicked(View view) {

        // Add a keyvalue pair with the declined User's ID as key and a 1 as value.
        Log.d("Joost", " "+displayedUser.getObjectId());
        user.put(displayedUserId, 1);
        Log.d("Joost", "This user has been liked: " + displayedUserId);
        checker = 0;

        // Check if the like is mutual. If so, add eachother to eachother's matchlist.
        if (displayedUser.getInt(user.getObjectId()) == 1){

            // Check if match is already recorded, and if not, record it.
            if (!user.getList("matches").contains(displayedUser.getObjectId())){
                matches = user.getList("matches");
                matches.add(displayedUser.getObjectId());
                user.put("matches", matches);
                user.saveInBackground();
                Toast.makeText(getApplicationContext(), "You've got a new match!",
                        Toast.LENGTH_LONG).show();
            }
            if (!displayedUser.getList("matches").contains(user.getObjectId())) {
                matches = displayedUser.getList("matches");
                matches.add(user.getObjectId());
                displayedUser.put("matches", matches);
                displayedUser.saveInBackground();
            }

            // Log, and save.
            Log.d("Joost", "Barack matched, matchlist: " + user.getList("matches"));
        }

        else {
            // Something went wrong.
            Log.d("Joost", "Barack NOT matched");
        }


        // Clear TextViews to give a sense of progress to the user.
        userTextTextView.setText("");
        userYouTubeURLTextView.setText("");

        // Choose a new random user.
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> allParseUsers, ParseException e) {
                if (e == null) {
                    // Keep randomizing until a user that is not current user is found.
                    while (checker == 0) {
                        Log.d("Joost", "Next Barack found");

                        // Pick a random entry in the list and extract the relevant information.
                        // Set the relevant views using this information.
                        randomizer = new Random();
                        displayedUser = allParseUsers.get(randomizer.nextInt(allParseUsers.size()));
                        userNameDisplay = displayedUser.getUsername();
                        userNameTextView.setText(userNameDisplay);
                        userTextDisplay = displayedUser.getString("profileText");
                        userTextTextView.setText(userTextDisplay);
                        userYouTubeURL = displayedUser.getString("youTubeURL");
                        userYouTubeURLTextView.setText(userYouTubeURL);
                        displayedUserId = displayedUser.getObjectId();

                        // If found user is not current user, leave the while loop.
                        if (!displayedUserId.equals(userID)) {
                            checker = 1;
                        }
                    }
                } else {
                    // Something went wrong.
                    Log.d("Joost", "Next Barack NOT found");
                }
            }
        });

        // Save like.
        user.saveInBackground();
    }

    public void onDeclineButtonClicked(View view) {

        // Add a keyvalue pair with the declined User's ID as key and a 0 as value.
        user.put(displayedUser.getObjectId(), 0);
        Log.d("Joost", "This poor user has been declined: " + displayedUser.getObjectId());
        checker = 0;

        // Clear TextViews incase someone doesnt have a profile filled in.
        userTextTextView.setText("");
        userYouTubeURLTextView.setText("");

        // Choose a new random user.
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> allParseUsers, ParseException e) {
                if (e == null) {
                    // Keep randomizing until a user that is not current user is found.
                    while (checker == 0) {
                        Log.d("Joost", "Next Barack found");

                        // Pick a random entry in the list and extract the relevant information.
                        // Set the relevant views using this information.
                        randomizer = new Random();
                        displayedUser = allParseUsers.get(randomizer.nextInt(allParseUsers.size()));
                        userNameDisplay = displayedUser.getUsername();
                        userNameTextView.setText(userNameDisplay);
                        userTextDisplay = displayedUser.getString("profileText");
                        userTextTextView.setText(userTextDisplay);
                        userYouTubeURL = displayedUser.getString("youTubeURL");
                        userYouTubeURLTextView.setText(userYouTubeURL);
                        displayedUserId = displayedUser.getObjectId();

                        // If found user is not current user, leave the while loop.
                        if (!displayedUserId.equals(userID)) {
                            checker = 1;
                        }
                    }
                } else {
                    // Something went wrong.
                    Log.d("Joost", "Next Barack NOT found");
                }
            }
        });

        // Save dislike.
        user.saveInBackground();
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
