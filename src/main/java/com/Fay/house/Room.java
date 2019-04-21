package com.Fay.house;

import com.Fay.devices.Device;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private int numberOfFreeSockets, roomPower = 0;
    private String name;
    private List<Device> devices;

    public void setRoomPower(int roomPower) {
        this.roomPower = roomPower;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public int getNumberOfFreeSockets() {
        return numberOfFreeSockets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberOfFreeSockets(int numberOfFreeSockets) {
        this.numberOfFreeSockets = numberOfFreeSockets;
    }

    public int getRoomPower() {
        return roomPower;
    }


    public Room(int numberOfFreeSockets, String name) {
        this.numberOfFreeSockets = numberOfFreeSockets;
        this.name = name;
        this.devices = new ArrayList<>();
    }
}
