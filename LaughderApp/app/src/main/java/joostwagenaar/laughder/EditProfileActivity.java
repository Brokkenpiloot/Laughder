package joostwagenaar.laughder;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
    EditText phoneNumberEditText;
    String profileText;
    String youTubeURL;
    String phoneNumber;
    ParseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d("Joost", "Edit profile activity created");
        setContentView(R.layout.activity_editprofile);

        // Clear variables. Fixes a specific bug.
        profileText = null;
        youTubeURL = null;
        phoneNumber = null;

        // Find out which user is currently logged in.
        user = ParseUser.getCurrentUser();

        // Get stored strings, and put them in the edit text, unless it's a new user.
        profileTextEditText = (EditText) findViewById(R.id.profileTextEditText);
        youTubeURLEditText = (EditText) findViewById(R.id.youTubeEditText);
        phoneNumberEditText = (EditText) findViewById(R.id.phoneNumberEditText);
        profileText = user.getString("profileText");
        youTubeURL = user.getString("youTubeURL");
        phoneNumber = user.getString("phoneNumber");

        // Enter existing strings into Edit Text. If this user has not yet set this, null will
        // be entered, which will show the hints in the view.
        profileTextEditText.setText(profileText);
        youTubeURLEditText.setText(youTubeURL);
        phoneNumberEditText.setText(phoneNumber);
        Log.d("Joost", "User already had things stored.");
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

        // Extract string from EditTexts.
        profileText = profileTextEditText.getText().toString();
        youTubeURL = youTubeURLEditText.getText().toString();
        phoneNumber = phoneNumberEditText.getText().toString();

        // Check to see if relevant input has been given.
        if (!youTubeURL.toLowerCase().contains("youtu") && !youTubeURL.equals("")) {
            Toast.makeText(getApplicationContext(), "That is not a relevant YouTube Link",
                    Toast.LENGTH_LONG).show();
        }

        // Store the new values.
        else {
            user.put("profileText", profileText);
            user.put("youTubeURL", youTubeURL);
            user.put("phoneNumber", phoneNumber);
            user.saveInBackground();
            Log.d("Joost", "Text and URL put in username");
            Toast.makeText(getApplicationContext(), "Profile has been saved!",
                    Toast.LENGTH_LONG).show();
        }
    }
}
