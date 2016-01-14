package joostwagenaar.laughder;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by startklaar on 7-1-2016.
 */
public class EditProfileActivity extends AppCompatActivity{

    Intent logOut;
    Intent viewMatches;
    Intent viewProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d("Joost", "Edit profile activity created");
        setContentView(R.layout.activity_editprofile);
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
}
