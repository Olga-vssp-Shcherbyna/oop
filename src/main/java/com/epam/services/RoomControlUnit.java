package com.epam.services;

import com.epam.devices.Device;
import com.epam.factory.Factory;
import com.epam.house.House;
import com.epam.house.Room;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RoomControlUnit {

    /**
     * @param house
     * @param name
     * @return
     */
    public static Room getRoomByName(House house, String name) {
        Room room = null;
        for (Room roomIterable : house.getRooms()) {
            if (roomIterable.getName().equalsIgnoreCase(name)) {
                room = roomIterable;
            }
        }
        return room;
    }

    /**
     * This method is used for room name validation
     *
     * @param input
     * @return
     */
    protected static boolean checkName(String input) {
        Pattern p = Pattern.compile("[A-Z]{1}[a-z]{2,}");
        Matcher m = p.matcher(input);
        return m.matches();
    }

    /**
     * This method is used to check validity of room setup parameters
     *
     * @param house
     * @param numberOfFreeSockets
     * @param name
     * @return
     */
    public static boolean roomSetupValidation(House house, int numberOfFreeSockets, String name) {
        if (getRoomByName(house, name) == null) {
            if (checkName(name)) {
                if (numberOfFreeSockets > 0) {
                    return true;
                } else System.out.println("Sorry, wrong number of free sockets");
            } else System.out.println("Sorry, invalid name");
        } else System.out.println("Sorry, this room already exists. Please, try again");
        return false;
    }

    /**
     * This method is used for adding new device in the room
     *
     * @param house
     * @param roomName
     * @param type
     * @param power
     * @param state
     */
    public static void addDevice(House house, String roomName, String type, int power, String state) {
        Room room = getRoomByName(house, roomName);
        Device device = Factory.getDevice(type, power, Boolean.parseBoolean(state));
        if (room != null) {
            if (room.getNumberOfFreeSockets() == 0) {
                System.out.println("All sockets are filled with devices");
            }
            room.setNumberOfFreeSockets(room.getNumberOfFreeSockets() - 1);
            if (device == null) {
                System.out.println("Device was null");
                return;
            } else {
                if (device.getState()) {
                    room.setRoomPower(room.getRoomPower() + device.getPower());
                    house.setTotalPower(house.getTotalPower() + room.getRoomPower());
                }
                room.getDevices().add(device);
                String position = new StringBuilder().append(device.getType())
                        .append(" ")
                        .append(room.getName())
                        .append(" #")
                        .append(device.getId())
                        .toString();
                device.setPosition(position);
            }
        } else System.out.println("There is no such room");
    }


    /**
     * This method is used for switching device
     *
     * @param house
     * @param roomName
     * @param id
     * @param type
     */
    public static void switchDevice(House house, String roomName, int id, String type) {
        Room room = getRoomByName(house, roomName);
        if (room == null) {
            System.out.println("Sorry, there is no such room");
            return;
        }
        Device device = HouseControlUnit.getDeviceByPosition(house, type + " " + roomName + " " + id);
        if (device == null) {
            System.out.println("Sorry, there is no such device");
            return;
        }
        boolean state = device.getState();
        if (state) {
            device.setState(false);
            room.setRoomPower(room.getRoomPower() - device.getPower());
            house.setTotalPower(house.getTotalPower() - device.getPower());
            room.setNumberOfFreeSockets(room.getNumberOfFreeSockets() + 1);
        } else {
            device.setState(true);
            room.setRoomPower(room.getRoomPower() + device.getPower());
            house.setTotalPower(house.getTotalPower() + device.getPower());
        }
    }


    /**
     * This method is used for removing device from the room
     *
     * @param house
     * @param roomName
     * @param position
     */
    public static void deleteDevice(House house, String roomName, String position) {
        Room room = getRoomByName(house, roomName);
        Device device = null;
        if (room != null) {
            for (Device deviceIterable : room.getDevices()) {
                if (deviceIterable.getPosition().equalsIgnoreCase(position))
                    device = deviceIterable;
            }
            if (device != null) {
                room.setNumberOfFreeSockets(room.getNumberOfFreeSockets() + 1);
                if (device.getState()) {
                    room.setRoomPower(room.getRoomPower() - device.getPower());
                    house.setTotalPower(house.getTotalPower() - device.getPower());
                }
                room.getDevices().remove(device);
                device.setPosition("Device was deleted");
            } else System.out.println("Device was null");
        } else System.out.println("There is no such room");
    }


    /**
     * This method used to show number of free sockets in room and total power of active devices
     *
     * @param house
     * @param roomName
     */
    public static void roomStatus(House house, String roomName) {
        Room room = getRoomByName(house, roomName);
        if (room != null)
            System.out.println("This is " + room.getName().toLowerCase() + " and there is " + room.getNumberOfFreeSockets() + " free sockets and total power of devices = " + room.getRoomPower());
        else System.out.println("Sorry, there is no such room");
    }
}

