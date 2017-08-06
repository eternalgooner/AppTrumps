package com.apptrumps.apptrumps.ui;

import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.apptrumps.apptrumps.R;
import com.apptrumps.apptrumps.utils.WifiDirectReceiver;

public class ChooseConnectionType extends AppCompatActivity {
    private TextView txtWifiP2P;
    private TextView txtMobileData;
    private TextView txtBluetooth;
    private static final String TAG = ChooseConnectionType.class.getSimpleName();
    private WifiP2pManager wifiP2pManager;
    private WifiP2pManager.Channel channel;
    private WifiDirectReceiver receiver;
    private IntentFilter intentFilter;
    private WifiP2pManager.PeerListListener peerListListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_connection_type);

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

        getSupportActionBar().setTitle("Choose connection type");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtWifiP2P = (TextView) findViewById(R.id.txt_wifi_p2p);
        addClickListener(txtWifiP2P);
        txtMobileData = (TextView) findViewById(R.id.txt_connect_mobile_data);
        addClickListener(txtMobileData);
        txtBluetooth = (TextView) findViewById(R.id.txt_connect_bluetooth);
        addClickListener(txtBluetooth);
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

    private void addClickListener(TextView view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processUserClick(v.getId());
            }
        });
    }

    private void processUserClick(int id) {
        switch (id) {
            case R.id.txt_wifi_p2p:
                lookForWifiP2PConnections();
                break;
            case R.id.txt_connect_mobile_data:
                lookForMobileDataConnections();
                break;
            case R.id.txt_connect_bluetooth:
                lookForBluetoothConnections();
                break;
            default:
                Log.e(TAG, " ERROR: NO MATCH IN SWITCH CHECKING BUTTON CLICK FOR CONNECTIONS");
        }
    }

    private void lookForBluetoothConnections() {

    }

    private void lookForMobileDataConnections() {

    }

    private void lookForWifiP2PConnections() {
        Log.d(TAG, "in lookForWifiP2PConnections()");
        wifiP2pManager.discoverPeers(channel, new WifiP2pManager.ActionListener(){

           @Override
           public void onSuccess() {
               Log.d(TAG, "in onSuccess() - device detected");
           }

           @Override
           public void onFailure(int reason) {
               Log.d(TAG, "in onFailure() - device not detected");
           }
        });
    }
}
