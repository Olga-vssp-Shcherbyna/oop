package com.epam.house;

import com.epam.devices.Device;

import java.util.ArrayList;

public class Room {
    private int numberOfFreeSockets, roomPower = 0;
    private String name;
    private ArrayList<Device> devices;

    //setter for roomPower
    public void setRoomPower(int roomPower) {
        this.roomPower = roomPower;
    }

    //getter for list of devices in room
    public ArrayList<Device> getDevices() {
        return devices;
    }

    //setter for list of devices
    public void setDevices(ArrayList<Device> devices) {
        this.devices = devices;
    }


    //getter for numberOfFreeSockets
    public int getNumberOfFreeSockets() {
        return numberOfFreeSockets;
    }

    //getter for room type
    public String getName() {
        return name;
    }

    //setter for room type
    public void setName(String name) {
        this.name = name;
    }
    //setter for numberOfFreeSockets
    public void setNumberOfFreeSockets(int numberOfFreeSockets) {
        this.numberOfFreeSockets = numberOfFreeSockets;
    }

    //getter for roomPower
    public int getRoomPower() {
        return roomPower;
    }


    //constructor for Room
    public Room(int numberOfFreeSockets, String name) {
        this.numberOfFreeSockets = numberOfFreeSockets;
        this.name = name;
        this.devices=new ArrayList<Device>();
    }


}
