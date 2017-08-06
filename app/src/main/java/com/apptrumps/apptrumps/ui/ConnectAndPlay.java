package com.apptrumps.apptrumps.ui;

import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.apptrumps.apptrumps.R;
import com.apptrumps.apptrumps.model.Device;
import com.apptrumps.apptrumps.utils.WifiDirectReceiver;

import java.util.ArrayList;
import java.util.List;

public class ConnectAndPlay extends AppCompatActivity implements WifiP2pManager.PeerListListener{
    private WifiP2pManager wifiP2pManager;
    private WifiP2pManager.Channel channel;
    private WifiDirectReceiver receiver;
    private IntentFilter intentFilter;
    private ListView deviceListView;
    private DeviceAdapter deviceAdapter;
    private ArrayList<Device> listOfDevices;
    private static final String TAG = ConnectAndPlay.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "in onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_and_play);

        deviceListView = (ListView) findViewById(R.id.device_list_view);

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

        scanForDevices();
    }

    private void scanForDevices() {
        Log.d(TAG, "in scanForDevices()");
        wifiP2pManager.discoverPeers(channel, new WifiP2pManager.ActionListener(){

           @Override
           public void onSuccess() {
               Log.d(TAG, "in onSuccess() - device detected");
               wifiP2pManager.requestPeers(channel, ConnectAndPlay.this);
           }

           @Override
           public void onFailure(int reason) {
               Log.d(TAG, "in onFailure() - device not detected");
           }
        });
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "in onResume()");
        super.onResume();
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "in onPause()");
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    public void onPeersAvailable(WifiP2pDeviceList peers) {
        Log.d(TAG, "in onPeersAvailable() - num peers available: " + peers.getDeviceList().size());

        if(peers.getDeviceList().size() > 0){
            Log.d(TAG, "peers divce list is greater than 0 - try to connect");

            listOfDevices= new ArrayList<>(10);
//            while (peers.getDeviceList().iterator().hasNext()){
//                WifiP2pDevice device = peers.getDeviceList().iterator().next();
//                devices.add(new Device(device.deviceName, device.deviceAddress, device.isGroupOwner()));
//            }


            for(WifiP2pDevice device: peers.getDeviceList()){
                Log.d(TAG, "in for loop adding devices");
                listOfDevices.add(new Device(device.deviceName, device.deviceAddress, device.isGroupOwner()));
            }

            Log.d(TAG, "creating device adapter & setting adapter for list view");
            deviceAdapter = new DeviceAdapter(this, R.layout.device_list_item, listOfDevices);
            showItems();


            //TODO list items not showing - getView not being called
//            final WifiP2pDevice device = peers.getDeviceList().iterator().next();
//            WifiP2pConfig config = new WifiP2pConfig();
//            config.deviceAddress = device.deviceAddress;
//            wifiP2pManager.connect(channel, config, new WifiP2pManager.ActionListener() {
//                @Override
//                public void onSuccess() {
//                    Log.d(TAG, "connected successfully!!!! - - device name is: " + device.deviceName);
//                    Log.d(TAG, " - - device contents (int) are: " + device.describeContents());
//                    Log.d(TAG, " - - device address is: " + device.deviceAddress);
//                    Log.d(TAG, " - - device primary device type is: " + device.primaryDeviceType);
//                    Log.d(TAG, " - - device secondary device type is: " + device.secondaryDeviceType);
//                    Log.d(TAG, " - - device is group owner: " + device.isGroupOwner());
//                }
//
//                @Override
//                public void onFailure(int reason) {
//                    Log.d(TAG, "unsuccessful connection");
//                }
//            });
        }else{
            Log.d(TAG, "scanning - no devices currently availble to connect to");
        }
    }

    private void showItems() {
        deviceListView.setAdapter(deviceAdapter);
    }

}
