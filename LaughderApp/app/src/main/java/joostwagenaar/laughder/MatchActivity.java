package joostwagenaar.laughder;

/**
 * Created by startklaar on 6-1-2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;


public class MatchActivity extends AppCompatActivity {

    Intent logOut;
    Intent viewProfile;
    Intent editProfile;
    ArrayAdapter<String> adapter;
    ArrayList<String> matchesList;
    ListView matchesListView;
    String matchObjectID;
    List objectList;
    ParseUser user;
    int counter;
    BlockingQueue<Boolean> conditionMet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Joost", "Match onCreate started");
        setContentView(R.layout.activity_match);
        user = ParseUser.getCurrentUser();

        // Initialize the ListView and other relevant variables.
        matchesListView = (ListView)findViewById(R.id.matchesListView);
        Log.d("Joost", "Check if match list comes through: " + user.getList("matches"));
        objectList = new ArrayList<String>();
        objectList = user.getList("matches");
        matchesList = new ArrayList<String>();
        counter = 0;
        Log.d("Joost", "Calling MatchQuery " + user.getList("matches"));
        adapter = new ArrayAdapter<String>(this,R.layout.list_item, R.id.matchTextView, matchesList);
        matchesListView.setAdapter(adapter);
        MatchQuery matchQuery = new MatchQuery(adapter, objectList, matchesList);
        matchQuery.getMatches();
        Log.d("Joost", "Matches string: " + objectList);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_match, menu);
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

        if (id == R.id.view_profile) {
            // Go to the browse screen.
            viewProfile = new Intent (this, BrowseActivity.class);
            viewProfile.putExtra("Joost", "username");
            startActivity(viewProfile);
            finish();
        }

        if (id == R.id.edit_profile) {
            // Go to the edit profile screen.
            editProfile = new Intent (this, EditProfileActivity.class);
            startActivity(editProfile);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
