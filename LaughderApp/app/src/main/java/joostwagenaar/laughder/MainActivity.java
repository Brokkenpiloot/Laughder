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
import com.parse.Parse;
import com.parse.ParseObject;


public class MainActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;
    String username;
    String password;
    Intent browseScreen;
    Intent registerScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Joost", "Main onCreate started");
        setContentView(R.layout.activity_main);
        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);


        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("username", "Joost");
        testObject.put("bit", "bot");
    }

    public void onLoginButtonClicked(View view)  {
        Log.d("Joost", "Login button clicked");
        username = usernameEditText.getText().toString();
        password = passwordEditText.getText().toString();

        if(username.length() != 0 && password.length() != 0) {
            // add a password check using parse
            Log.d("Joost", "Log in 'accepted'");
            browseScreen = new Intent(this, BrowseActivity.class);
            Log.d("Joost", "Intent initialised");
            browseScreen.putExtra("User", username);
            Log.d("Joost", "Added string to Intent ");
            startActivity(browseScreen);
            Log.d("Joost", "Activity started");
            finish();
            Log.d("Joost", "Main activity finished");
        }
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
