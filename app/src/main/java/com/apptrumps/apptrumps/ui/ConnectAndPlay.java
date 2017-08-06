package com.apptrumps.apptrumps.ui;

import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.apptrumps.apptrumps.R;
import com.apptrumps.apptrumps.utils.WifiDirectReceiver;

public class ConnectAndPlay extends AppCompatActivity implements WifiP2pManager.PeerListListener{
    private WifiP2pManager wifiP2pManager;
    private WifiP2pManager.Channel channel;
    private WifiDirectReceiver receiver;
    private IntentFilter intentFilter;
    private static final String TAG = ConnectAndPlay.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_and_play);

        //register broadcst receiver
        wifiP2pManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        channel = wifiP2pManager.initialize(this, getMainLooper(), null);
        receiver = new WifiDirectReceiver(wifiP2pManager, channel, this);

        //add intents that receiver checks for
        intentFilter = new IntentFilter();
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    public void onPeersAvailable(WifiP2pDeviceList peers) {
        Log.d(TAG, "in onPeersAvailable() - num peers available: " + peers.getDeviceList().size());

        final WifiP2pDevice device = peers.getDeviceList().iterator().next();
        WifiP2pConfig config = new WifiP2pConfig();
        config.deviceAddress = device.deviceAddress;
        wifiP2pManager.connect(channel, config, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "connected successfully!!!! - - device name is: " + device.deviceName);
                Log.d(TAG, " - - device contents (int) are: " + device.describeContents());
                Log.d(TAG, " - - device address is: " + device.deviceAddress);
                Log.d(TAG, " - - device primary device type is: " + device.primaryDeviceType);
                Log.d(TAG, " - - device secondary device type is: " + device.secondaryDeviceType);
                Log.d(TAG, " - - device is group owner: " + device.isGroupOwner());
            }

            @Override
            public void onFailure(int reason) {
                Log.d(TAG, "unsuccessful connection");
            }
        });
    }
}
