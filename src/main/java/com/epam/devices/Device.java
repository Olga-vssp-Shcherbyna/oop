package com.epam.devices;

public abstract class Device {
    //template for device
    public abstract boolean getBooleanState();
    public abstract String getState();
    public abstract int getPower();
    public abstract String getPosition();
    public abstract int getId();
    public abstract void setPosition(String position);
    public abstract String getType();
    public abstract void setState(String state);


    @Override
    public String toString(){
        return "State = "+this.getState()+", power = "+this.getPower();
    }
}
