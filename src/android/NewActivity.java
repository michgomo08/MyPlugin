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
               String title = extras.getString("title");
               Intent intent= new Intent();
                intent.putExtra("title",title);
                setResult(RESULT_OK,intent);
                finish();
               
            }
        }
    }

    
}