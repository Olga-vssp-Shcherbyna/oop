package com.epam.devices;


public class Razor extends Device {
    private static final String TYPE = "RAZOR";
    private static int count = 0;
    private int power;
    private int id;
    private String position;
    private boolean isOn;

    public Razor(int power, boolean isOn) {
        this.power = power;
        this.isOn = isOn;
        this.id = count;
        count++;
    }

    @Override
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
