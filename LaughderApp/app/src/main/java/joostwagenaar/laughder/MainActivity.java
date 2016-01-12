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
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;


public class MainActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;
    String username;
    String password;

    Intent registerScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Joost", "Main onCreate started");
        setContentView(R.layout.activity_main);
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
                                // If user exist and authenticated, send user to browseScreen.class
                                Log.d("Joost", "Log in 'accepted'");
                                Intent intent = new Intent(
                                        MainActivity.this,
                                        BrowseActivity.class);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(),
                                        "Successfully Logged in",
                                        Toast.LENGTH_LONG).show();
                                finish();
                            } else {
                                Toast.makeText(
                                        getApplicationContext(),
                                        "No such user exist, please signup",
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
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
