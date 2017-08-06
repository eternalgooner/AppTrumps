package com.apptrumps.apptrumps.model;

/**
 * Created by David on 06-Aug-17.
 */

public class Device {
    private String name;
    private String address;
    private boolean isGroupOwner;

    public Device(String name, String address, boolean isGroupOwner) {
        this.name = name;
        this.address = address;
        this.isGroupOwner = isGroupOwner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isGroupOwner() {
        return isGroupOwner;
    }

    public void setGroupOwner(boolean groupOwner) {
        isGroupOwner = groupOwner;
    }
}
