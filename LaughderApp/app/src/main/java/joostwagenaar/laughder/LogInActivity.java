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

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.Collections;


public class LogInActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;
    String username;
    String password;
    Intent registerScreen;
    ParseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Joost", "LogIn onCreate started");
        setContentView(R.layout.activity_log_in);

        // Sign out any users that might still be logged to clear the cache.
        // This fixes a bug where logging in or registering wasn't possible when app was wrongfully terminated.
        // Also ensures users are logged out if the click back and enter this screen.
        ParseUser.logOut();
        user = ParseUser.getCurrentUser();

    }

    public void onLoginButtonClicked(View view)  {
        Log.d("Joost", "Login button clicked");

        // Find the edit texts and import the strings.
        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        username = usernameEditText.getText().toString().toLowerCase();
        password = passwordEditText.getText().toString();

        // Check to see if both boxes have been filled in.
        if(username.length() != 0 && password.length() != 0) {
            ParseUser.logInInBackground(username, password,
                    new LogInCallback() {
                        public void done(ParseUser user, ParseException e) {

                            if (user != null) {
                                // If user exist and authenticated, send user to browseScreen.class.
                                Log.d("Joost", "Log in 'accepted'");
                                Intent intent = new Intent(LogInActivity.this,
                                        BrowseActivity.class);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(),
                                        "Successfully Logged in",
                                        Toast.LENGTH_LONG).show();

                                // Check if user already has a matches list.
                                // For users made before matches implementation.
                                if (user.getList("matches") == null) {
                                    user.put("matches", Collections.emptyList());
                                    Log.d("Joost", "Old account, empty match list made");
                                    user.saveInBackground();
                                }
                                
                                finish();


                            } else {
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Password and username don't match",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }

        // If one of the boxes hasn't been filled in, request completion.
        else {
            Log.d("Joost", "Log in 'failed'");
            Toast.makeText(getApplicationContext(), "Please fill in both text boxes",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void onRegisterButtonClicked(View view) {
        Log.d("Joost", "Register button clicked");
        registerScreen = new Intent(this, RegisterActivity.class);
        startActivity(registerScreen);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
