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
import com.parse.Parse;
import com.parse.ParseObject;

public class RegisterActivity extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;
    EditText password2EditText;
    String username;
    String password;
    String password2;
    Intent editProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Joost", "Main onCreate started");
        setContentView(R.layout.activity_register);
        usernameEditText = (EditText) findViewById(R.id.registerUserNameEditText);
        passwordEditText = (EditText) findViewById(R.id.registerPasswordEditText);
        password2EditText = (EditText) findViewById(R.id.registerPasswordEditText2);
    }

    public boolean onRegisterButton2Clicked(View view) {
        username = usernameEditText.getText().toString();
        password = passwordEditText.getText().toString();
        password2 = password2EditText.getText().toString();
        if (!password2.equals(password)){
            Log.d("Joost", "Passwords are not identical");
            Toast.makeText(getApplicationContext(), "Passwords don't match",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        if (password.length() < 5 || password.length() > 15  ){
            Log.d("Joost", "Passwords too long or too short");
            Toast.makeText(getApplicationContext(), "Passwords must be between 5 and 15 characters",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        //add similair if statement that checks whether username is already in use.

        ParseObject accountObject = new ParseObject("AccountInfo");
        accountObject.put("username", username);
        accountObject.put("password", password);
        editProfile = new Intent (this, EditProfileActivity.class);
        startActivity(editProfile);
        finish();

        return true;

    }
}
