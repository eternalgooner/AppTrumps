package com.apptrumps.apptrumps.ui;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.apptrumps.apptrumps.R;
import com.apptrumps.apptrumps.model.Device;

import java.util.List;

/**
 * Created by David on 06-Aug-17.
 */

public class DeviceAdapter extends ArrayAdapter<Device> implements View.OnClickListener{

    private static final String TAG = DeviceAdapter.class.getSimpleName();
    private List deviceList;
    private int currentItem;
    private ListItemClickListener onClickListener;

    public DeviceAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    public DeviceAdapter(Context context, int resource, List<Device> deviceList){
        super(context, resource);
        Log.d(TAG, "in DeviceAdapter constuctor(), size of deviceList: " + deviceList.size());
        onClickListener = (ConnectAndPlay) context;
        this.deviceList = deviceList;

        //notifyDataSetChanged();
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
        convertView.setOnClickListener(this);
        return convertView;
    }

    @Override
    public int getCount() {
        Log.d(TAG, "in getCount(), size: " + deviceList.size());
        return deviceList.size();
    }

    @Nullable
    @Override
    public Device getItem(int position) {
        Log.d(TAG, "in getItem(), position is: " + position);
        currentItem = position;
        return (Device) deviceList.get(position);
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "in onClick()");
        onClickListener.onListItemClick(currentItem);
    }

    public interface ListItemClickListener{
        void onListItemClick(int clickedItem);
    }
}
