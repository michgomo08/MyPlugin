package com.mgm;

import android.app.Activity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import android.os.Bundle;

public class NewActivity extends Activity {
    String action = "";

    @Override
    public void onStart() {

        super.onStart();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            action = extras.getString("action");

            if (action.equals("buscarLector")) {

                buscarLector();

            } else if (action.equals("buscarLector2")) {

                buscarLector2();

            }

        }
    }

    private void buscarLector() {

        Intent intent = new Intent();
        intent.putExtra("action", action);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void buscarLector2() {

        Intent intent = new Intent();
        intent.putExtra("action", action);
        setResult(RESULT_OK, intent);
        finish();
    }

}