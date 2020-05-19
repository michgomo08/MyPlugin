package com.mgm;

import android.app.Activity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import android.os.Bundle;
   
public class NewActivity extends Activity {
    private boolean firstTime = true;

    @Override
    public void onStart() {
        super.onStart();
        // Write your code inside this condition
        // Here should start the process that expects the onActivityResult
        if(firstTime == true){
            // Do something at first initialization
            // And retrieve the parameters that we sent before in the Main file of the plugin
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
               String tittle = extras.getString("tittle");
               
            }
        }
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        firstTime = false;// There's a result, allow to exit the activity !
        
        // Do something with the result of the Intent data
 
        // Send parameters to retrieve in cordova.
        Intent intent = new Intent();
        intent.putExtra("data", "This is the sent information from the 2 activity :) ");
        setResult(Activity.RESULT_OK, intent);
        finish();// Exit of this activity !
    }
}