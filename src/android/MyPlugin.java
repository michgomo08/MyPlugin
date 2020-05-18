package com.mgm;

import android.content.Context;
import android.content.Intent;
import android.app.Activity;
import android.os.Build;
   
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.PluginResult;
   
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



/**
 * This class echoes a string called from JavaScript.
 */
public class MyPlugin extends CordovaPlugin {
    
    public final int MY_OP = 8;
    private CallbackContext callback = null;
    private String title ="";

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
		
		try {
            
            callback = callbackContext;
            
            if (action.equals("testMichael")) {
                String message = args.getString(0) + " (desde MyPlugin) --- testMichael";
                this.testMichael(message, callbackContext);
                return true;
            }
            else if(action.equals("new_activity")) {
                title= args.getString(0) + " (desde MyPlugin) --- new_activity";

                cordova.setActivityResultCallback (this);
        
               /* Intent intent = new Intent();
                intent.setClassName("com.mgm","com.mgm.NewActivity");*/

                Intent intent = new Intent(this.cordova.getActivity(), NewActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("title", title);
        
                cordova.startActivityForResult (this, intent, MY_OP);
                return true;
            }
            return false;
        } catch (JSONException e) {
			callbackContext.error("Reminder exception occured: "+e.toString());
			return false;
		}
		
		
	}

    private void testMichael(String message, CallbackContext callbackContext) {

		
            if (message != null && message.length() > 0) {
                callbackContext.success(message);
            } else {
                callbackContext.error("Expected one non-empty string argument.");
            }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) 
    {
        if( requestCode == MY_OP )
        {
            if( resultCode == Activity.RESULT_OK && data.hasExtra("return_val") )
            {
                PluginResult result = new PluginResult(PluginResult.Status.OK, data.getStringExtra("return_val"));
                result.setKeepCallback(true);
                callback.sendPluginResult(result);
            }
            else
            {
                PluginResult result = new PluginResult(PluginResult.Status.ERROR, "no params returned successfully" );
                result.setKeepCallback(true);
                callback.sendPluginResult(result);
            }
        }
    }

}
