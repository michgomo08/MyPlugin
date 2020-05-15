package com.mgm;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;

import com.ftsafe.readerScheme.FTReader;
import com.ftsafe.readerScheme.FTException;
import com.ftsafe.DK;


import android.os.Handler;
import android.os.Message;
import android.bluetooth.BluetoothDevice;


/**
 * This class echoes a string called from JavaScript.
 */
public class MyPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("testMichael")) {
            String message = args.getString(0) + " (desde MyPlugin) --- ";
            this.testMichael(message, callbackContext);
            return true;
        }
        return false;
    }

 /*   private Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg){
            super.handleMessage(msg);
        }
	};*/

    private void testMichael(String message, CallbackContext callbackContext) {
        FTReader ftReader = null;
        ftReader = new FTReader(MyPlugin.this,mHandler,DK.FTREADER_TYPE_BT3);
        try {
            ftReader.readerFind();
            message = message +" Device Found---------->";
        } catch (FTException e) {
            message = message +" No Device Found---------->" + e.getMessage();
        }
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg){
			super.handleMessage(msg);
			
			String log=null;
			
			switch (msg.what) {
			case -1:
				System.out.println("-1---");
				return;
			case 0:
                log = msg.obj.toString();
				System.out.println("LOG---------->"+log+"\n");
				break;				
			case DK.USB_IN:
            System.out.println("NOTICE------->USB Device In\n");
				/*try {
					ftReader.readerFind();
				} catch (Exception e) {
					textView.append("NOTICE------->USB Device In------->this should not happend------>" + e.getMessage() + "\n");
				}*/				
				break;
			case DK.USB_OUT:
            System.out.println("NOTICE------->USB Device Out\n");
				break;
			case DK.PCSCSERVER_LOG:
				//textView.append("[PcscServerLog]:"+msg.obj+"\n");
				break;
			case DK.USB_LOG:
				//textView.append("[USBLog]:"+msg.obj+"\n");
				break;
			case DK.BT3_LOG:
				//textView.append("[BT3Log]:"+msg.obj+"\n");
				break;
			case DK.BT4_LOG:
				//textView.append("[BT4Log]:"+msg.obj+"\n");
				break;
			case DK.FTREADER_LOG:
				//textView.append("[FTReaderLog]:"+msg.obj+"\n");
				break;
			case DK.CCIDSCHEME_LOG:
				//textView.append("[CCIDSchemeLog]:"+msg.obj+"\n");
				break;
			case DK.BT3_NEW:
				BluetoothDevice dev1 = (BluetoothDevice) msg.obj;
				System.out.println("[BT3_NEW]:"+dev1.getName()+"\n");
				System.out.println(dev1.getName());
				break;
		
			case DK.BT4_NEW:
				BluetoothDevice dev2 = (BluetoothDevice) msg.obj;
				System.out.println.append("[BT4_NEW]:"+dev2.getName()+"\n");
				System.out.println(dev2.getName());
				break;
			case DK.BT4_ACL_DISCONNECTED:
				BluetoothDevice dev3 = (BluetoothDevice) msg.obj;
				System.out.println("[BT4_ACL_DISCONNECTED]: "+dev3.getName()+" disconnected\n");
				break;
			default:
				if((msg.what & DK.CARD_IN_MASK) == DK.CARD_IN_MASK){
					System.out.println("NOTICE------->" + "slot"+(msg.what%DK.CARD_IN_MASK)+":card in\n");
					return;
				}else if((msg.what & DK.CARD_OUT_MASK) == DK.CARD_OUT_MASK){
					System.out.println("NOTICE------->" + "slot"+(msg.what%DK.CARD_OUT_MASK)+":card out\n");
					return;
				}
				break;
			}
		}
	};
	

    
}
