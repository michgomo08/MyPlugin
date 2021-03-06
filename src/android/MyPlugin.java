package com.mgm;

import android.content.Context;
import android.content.Intent;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

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

    private CallbackContext PUBLIC_CALLBACKS = null;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {

        PUBLIC_CALLBACKS = callbackContext;

        try {

            if (action.equals("testMichael")) {

                String message = args.getString(0) + " (desde MyPlugin) --- testMichael";
                this.testMichael(message);
                return true;

            } else if (action.equals("buscarLector")) {

                Intent intent = new Intent(this.cordova.getActivity(), NewActivity.class);
                intent.putExtra("action", action);

                // Now, cordova will expect for a result using startActivityForResult and will
                // be handle by the onActivityResult.
                cordova.startActivityForResult((CordovaPlugin) this, intent, 1);
                // Send no result, to execute the callbacks later
                PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
                pluginResult.setKeepCallback(true); // Keep callback

                return true;

            } else if (action.equals("buscarLector2")) {

                Intent intent = new Intent(this.cordova.getActivity(), NewActivity.class);
                intent.putExtra("action", action);

                // Now, cordova will expect for a result using startActivityForResult and will
                // be handle by the onActivityResult.
                cordova.startActivityForResult((CordovaPlugin) this, intent, 2);
                // Send no result, to execute the callbacks later
                PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
                pluginResult.setKeepCallback(true); // Keep callback

                return true;

            }

            return false;

        } catch (JSONException e) {
            PUBLIC_CALLBACKS.error("Reminder exception occured: " + e.toString());
            return false;
        }

    }

    private void testMichael(String message) {

        if (message != null && message.length() > 0) {
            PUBLIC_CALLBACKS.success(message);
        } else {
            PUBLIC_CALLBACKS.error("Expected one non-empty string argument.");
        }
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {

        if (resultCode == cordova.getActivity().RESULT_OK) {
            Bundle extras = data.getExtras();// Get data sent by the Intent
            String information = extras.getString("action"); // data parameter will be send from the other activity.

            PluginResult resultado = new PluginResult(PluginResult.Status.OK,
                    "EXITO---" + requestCode + "---" + information);
            resultado.setKeepCallback(true);
            PUBLIC_CALLBACKS.sendPluginResult(resultado);
            return;
        } else if (resultCode == cordova.getActivity().RESULT_CANCELED) {
            PluginResult resultado = new PluginResult(PluginResult.Status.OK,
                    "canceled action, process this in javascript");
            resultado.setKeepCallback(true);
            PUBLIC_CALLBACKS.sendPluginResult(resultado);
            return;
        }
        // Handle other results if exists.
        super.onActivityResult(requestCode, resultCode, data);
    }

}
