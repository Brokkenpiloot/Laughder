package joostwagenaar.laughder;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by startklaar on 21-1-2016.
 */
public class MatchQuery {

    ArrayAdapter<String> myAdapter;
    List myMatchArray;
    ArrayList<String> matchesStringList;
    ParseUser user;
    int counter;

    //
    MatchQuery (ArrayAdapter<String> adapter, List matchArray, ArrayList<String> matchesList){
        myAdapter = adapter;
        myMatchArray = matchArray;
        user = ParseUser.getCurrentUser();
        counter = 0;
        matchesStringList = matchesList;
    }

    public ArrayAdapter<String> getMatches () {
        for (Object objectId : myMatchArray) {
            Log.d("Joost", "MatchQuery loop started");
            ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
            query.whereEqualTo("objectId", objectId);
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if (e == null) {
                        Log.d("Joost", "User found: " + objects);
                        matchesStringList.add(objects.get(0).getString("username")+" ("+objects.get(0).getString("phoneNumber")+")");
                        myAdapter.notifyDataSetChanged();
                        counter++;
                        Log.d("Joost", "Match added, with counter: "+counter);
                    } else {
                        // Didn't work.
                        Log.d("Joost", "User not found");
                    }
                }
            });
        }

        return null;
    }

}
