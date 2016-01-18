package joostwagenaar.laughder;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.parse.ParseUser;

/**
 * Created by startklaar on 7-1-2016.
 */
public class EditProfileActivity extends AppCompatActivity{

    Intent logOut;
    Intent viewMatches;
    Intent viewProfile;
    EditText profileTextEditText;
    EditText youTubeURLEditText;
    String profileText;
    String youTubeURL;
    ParseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d("Joost", "Edit profile activity created");
        setContentView(R.layout.activity_editprofile);

        // Clear variables.
        profileText = null;
        youTubeURL = null;

        // Find out which user is currently logged in.
        user = ParseUser.getCurrentUser();

        // Get two stored strings, and put them in the edit text, unless it's a new user.
        profileTextEditText = (EditText) findViewById(R.id.profileTextEditText);
        youTubeURLEditText = (EditText) findViewById(R.id.youTubeEditText);
        profileText = user.getString("profileText");
        youTubeURL = user.getString("youTubeURL");
        if (youTubeURL != null) {
            profileTextEditText.setText(profileText);
            youTubeURLEditText.setText(youTubeURL);
            Log.d("Joost", "User already had text and URL");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_editprofile, menu);
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
            logOut = new Intent (this, LogInActivity.class);
            // Add Parse log out function.
            startActivity(logOut);
            finish();
        }

        if (id == R.id.view_matches) {
            // Go to the matches screen.
            viewMatches = new Intent (this, MatchActivity.class);
            startActivity(viewMatches);
            finish();
        }

        if (id == R.id.view_profile) {
            // Go to the browse screen.
            viewProfile = new Intent (this, BrowseActivity.class);
            startActivity(viewProfile);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void onSaveProfileButtonClicked(View view) {

        // Store the new values.
        profileText = profileTextEditText.getText().toString();
        youTubeURL = youTubeURLEditText.getText().toString();
        user.put("profileText", profileText);
        user.put("youTubeURL", youTubeURL);
        user.saveInBackground();
        Log.d("Joost", "Text and URL put in username");
    }
}
