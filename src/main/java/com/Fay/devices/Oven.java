package com.Fay.devices;

public class Oven extends Device {
    private int power;
    private int id;
    private String position;
    private static int count = 0;
    private static final String TYPE = "OVEN";
    private boolean isOn;

    public Oven(int power, boolean isOn) {
        this.power = power;
        this.isOn = isOn;
        this.id = count;
        count++;
    }

    public boolean getState() {
        return isOn;
    }

    @Override
    public void setState(boolean isOn) {
        this.isOn = isOn;
    }

    @Override
    public String getType() {
        return TYPE;
    }


    @Override
    public int getPower() {
        return this.power;
    }

    @Override
    public String getPosition() {
        return position;
    }

    @Override
    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public int getId() {
        return this.id;
    }


    @Override
    public String toString() {
        return this.getPosition() + " with power " + this.getPower() + " and it is on " + this.getState();
    }
}
