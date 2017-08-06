package com.apptrumps.apptrumps.ui;

import android.bluetooth.BluetoothClass;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apptrumps.apptrumps.R;
import com.apptrumps.apptrumps.model.Device;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 06-Aug-17.
 */

public class DeviceAdapter extends ArrayAdapter<Device> {
    private static final String TAG = DeviceAdapter.class.getSimpleName();
    private List deviceList;

    public DeviceAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    public DeviceAdapter(Context context, int resource, List<Device> deviceList){
        super(context, resource);
        Log.d(TAG, "in DeviceAdapter constuctor(3)");
        this.deviceList = deviceList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.d(TAG, "in getView() binding the item to the list view");
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.device_list_item, parent, false);
        }

        Device device = (Device)deviceList.get(position);
        if(device != null){
            //TextView title = (TextView) convertView.findViewById(R.id.list_item_title);
            TextView name = (TextView) convertView.findViewById(R.id.list_item_device_name);
            //ImageView icon = (ImageView) convertView.findViewById(R.id.list_item_image);

//            if(title != null){
//                title.setText("Device Name");
//            }

            if(name != null){
                name.setText(device.getName());
            }
        }
        return convertView;
    }
}
