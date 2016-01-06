package joostwagenaar.laughder;

/**
 * Created by startklaar on 5-1-2016.
 */

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.parse.Parse;
import com.parse.ParseObject;

public class BrowseActivity extends AppCompatActivity {

    Intent usernameIntent;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usernameIntent = getIntent();
        username = usernameIntent.getExtras().toString();
        Log.d("Joost", "Browse onCreate started");
        setContentView(R.layout.activity_browse);
    }

}
