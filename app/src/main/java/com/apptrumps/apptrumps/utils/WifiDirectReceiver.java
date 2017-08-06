package com.apptrumps.apptrumps.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;

import com.apptrumps.apptrumps.ui.ChooseConnectionType;

/**
 * Created by David on 06-Aug-17.
 */

public class WifiDirectReceiver extends BroadcastReceiver {
    private WifiP2pManager wifiP2pManager;
    private WifiP2pManager.Channel channel;
    private ChooseConnectionType gameScreen;
    private static final String TAG = WifiDirectReceiver.class.getSimpleName();

    public WifiDirectReceiver(WifiP2pManager wifiP2pManager, WifiP2pManager.Channel channel, ChooseConnectionType gameScreen){
        super();
        this.wifiP2pManager = wifiP2pManager;
        this.channel = channel;
        this.gameScreen = gameScreen;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "in onReceive()");
        String action = intent.getAction();

        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            // Check to see if Wi-Fi is enabled and notify appropriate activity
            Log.d(TAG, "wifi state changed");
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                // Wifi P2P is enabled
                Log.d(TAG, "wifi p2p is enabled");
            } else {
                // Wi-Fi P2P is not enabled
                Log.d(TAG, "wifi p2p is NOT enabled");
            }
        } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
            Log.d(TAG, "list of peers has changed");
            // Call WifiP2pManager.requestPeers() to get a list of current peers
        } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
            Log.d(TAG, "connection update - connect or disconnect");
            // Respond to new connection or disconnections
        } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {
            Log.d(TAG, "this device wifi state has changed");
            // Respond to this device's wifi state changing
        }
    }
}
