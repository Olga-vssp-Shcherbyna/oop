package com.epam.house;

import com.epam.devices.Device;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private int numberOfFreeSockets;
    private int roomPower = 0;
    private String name;
    private List<Device> devices;

    public Room(int numberOfFreeSockets, String name) {
        this.numberOfFreeSockets = numberOfFreeSockets;
        this.name = name;
        this.devices = new ArrayList<>();
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

    public void setNumberOfFreeSockets(int numberOfFreeSockets) {
        this.numberOfFreeSockets = numberOfFreeSockets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoomPower() {
        return roomPower;
    }

    public void setRoomPower(int roomPower) {
        this.roomPower = roomPower;
    }
}
