package com.epam.devices;


public class Kettle extends Device {
    private int power, id;
    private String position, state;
    private static int count = 0;
    private static final String type = "KETTLE";

    // constructor for class Kettle
    public Kettle(int power, String state) {
        this.power = power;
        this.setState(state);
        this.id=count;
        count++;
    }

    public String getState() {
        return state;
    }

    //getter for device type
    @Override
    public String getType() {
        return type;
    }

    //getter for state
    @Override
    public boolean getBooleanState() {
        if (state.equalsIgnoreCase("on")) return true;
        else return false;
    }

    // setter for state
    @Override
    public void setState(String state) {
        if (state.equalsIgnoreCase("on")||state.equalsIgnoreCase("off")) this.state = state;
        else System.out.println("Wrong state");
    }


    //getter for power value
    @Override
    public int getPower() {
        return this.power;
    }

    // getter for position used in HouseControlUnit
    @Override
    public String getPosition() {
        return position;
    }

    // setter for position used in RoomControlUnit
    @Override
    public void setPosition(String position){
        this.position=this.getType()+" "+position + " "+this.getId();
    }

    //getter for device id used in position setter
    @Override
    public int getId() {
        return this.id;
    }


    @Override
    public String toString(){
        return this.getPosition()+" with power "+this.getPower()+" and it is "+this.getState();
    }
}
