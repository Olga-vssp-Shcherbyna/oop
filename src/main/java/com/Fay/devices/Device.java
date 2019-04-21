package com.Fay.devices;

/**
 * This class describes device template
 */
public abstract class Device {

    public abstract boolean getState();

    public abstract int getPower();

    public abstract String getPosition();

    public abstract int getId();

    public abstract void setPosition(String position);

    public abstract String getType();

    public abstract void setState(boolean isOn);

}
