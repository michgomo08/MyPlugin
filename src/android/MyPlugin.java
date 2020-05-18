package com.mgm;

import android.content.Context;
import android.content.Intent;
   
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaInterface;
   
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



/**
 * This class echoes a string called from JavaScript.
 */
public class MyPlugin extends CordovaPlugin {
   
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }


    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		Context context = cordova.getActivity().getApplicationContext();
        if (action.equals("testMichael")) {
            String message = args.getString(0) + " (desde MyPlugin) --- testMichael";
            this.testMichael(message, callbackContext);
            return true;
        }
        else if(action.equals("new_activity")) {
            String message = args.getString(0) + " (desde MyPlugin) --- testMichael";
            this.openNewActivity(context, callbackContext, message);
            return true;
        }
        return false;
    }

    private void testMichael(String message, CallbackContext callbackContext) {

		
            if (message != null && message.length() > 0) {
                callbackContext.success(message);
            } else {
                callbackContext.error("Expected one non-empty string argument.");
            }
    }

    private void openNewActivity(Context context, CallbackContext callbackContext, String message) {
        Intent intent = new Intent(context, NewActivity.class);
		this.cordova.getActivity().startActivity(intent);
		callbackContext.success(message);
		
    }
	

    
}
