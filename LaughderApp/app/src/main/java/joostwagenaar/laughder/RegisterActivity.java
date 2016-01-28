package joostwagenaar.laughder;

/**
 * Created by startklaar on 10-1-2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.parse.ParseException;

import java.util.Collections;

public class RegisterActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;
    EditText password2EditText;
    EditText phoneNumberEditText;
    String username;
    String password;
    String password2;
    String phoneNumber;
    ParseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Joost", "Main onCreate started");
        setContentView(R.layout.activity_register);

        // Assign the edit texts
        usernameEditText = (EditText) findViewById(R.id.registerUserNameEditText);
        passwordEditText = (EditText) findViewById(R.id.registerPasswordEditText);
        password2EditText = (EditText) findViewById(R.id.registerPasswordEditText2);
        phoneNumberEditText = (EditText) findViewById(R.id.registerPhoneNumberEditText);
    }

    public boolean onRegisterButton2Clicked(View arg0) {
        Log.d("Joost", "Register button 2 clicked");

        // Import the strings put in the edit texts.
        username = usernameEditText.getText().toString().toLowerCase();
        password = passwordEditText.getText().toString();
        password2 = password2EditText.getText().toString();
        phoneNumber = phoneNumberEditText.getText().toString();

        // Check to see if the passwords are identical.
        if (!password2.equals(password)){
            Log.d("Joost", "Passwords are not identical");
            Toast.makeText(getApplicationContext(), "Passwords don't match",
                    Toast.LENGTH_LONG).show();
            return false;
        }

        // Check to see if the password is of useable length.
        if (password.length() < 5 || password.length() > 15  ){
            Log.d("Joost", "Passwords too long or too short");
            Toast.makeText(getApplicationContext(), "Passwords must be between 5 and 15 characters",
                    Toast.LENGTH_LONG).show();
            return false;
        }

        // Register account if username doesn't already exist.
        // Save new user data into Parse.com Data Storage
        user = new ParseUser();

        // Set user's username and password.
        user.setUsername(username);
        user.setPassword(password);

        // Create a matchlist keyvalue pair.
        user.put("matches", Collections.emptyList());

        // Add the user's phone number.
        user.put("phoneNumber", phoneNumber);

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {

                    // Log user in and show a toast.
                    Intent editProfile = new Intent( RegisterActivity.this,
                            EditProfileActivity.class);
                    startActivity(editProfile);
                    Toast.makeText(getApplicationContext(), "Successfully signed up!",
                            Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(),
                            "Sign up Error", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
        return true;
    }
}
