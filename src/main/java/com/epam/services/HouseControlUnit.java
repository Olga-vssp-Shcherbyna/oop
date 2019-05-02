package com.epam.services;

import com.epam.devices.Device;
import com.epam.house.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HouseControlUnit {



    //method for room addition
    public static Room addRoom(House house, int numberOfFreeSockets, String name) {
        if (RoomControlUnit.getRoomByName(house, name)==null) {
            if (checkName(name)) {
                if (numberOfFreeSockets > 0) {
                    Room room = new Room(numberOfFreeSockets, name);
                    house.getRooms().add(room);
                    house.setTotalPower(house.getTotalPower() + room.getRoomPower());
                 //   houseStatus(house);
                    return room;
                }
                else {System.out.println("Sorry, wrong number of free sockets"); return null;}
            }
            else {System.out.println("Sorry, invalid name"); return null;}
        }
        else
            {System.out.println("Sorry, this room already exists. Please, try again");
            return null;}
    }


    //method for room removing
    public static Room deleteRoom(House house, String name){
        Room r = null;
        for (Room room:house.getRooms()) {
            if (room.getName().equalsIgnoreCase(name)) {
                r=room;}
        }
        if (r != null) {
            house.getRooms().remove(r);
            house.setTotalPower(house.getTotalPower()-r.getRoomPower());
        }
        else
            System.out.println("Sorry, there is no such room. Please, try again");
        return r;
    }

    //method for house status display
    public static void houseStatus(House house){
        System.out.println("In house there are " + house.getRooms().size()
                + " rooms and its total power is " + house.getTotalPower());
    }

    //method for device search by parameters: power borders, state (on/off), type
    public static Device findDevice(House house, int power1, int power2, String state, String type){
        String position;
        Device deviceReturnable = null;
        ArrayList<Device> devices = getAllDevices(house);
        if (power1<power2&&power2>=0&&power1>=0)
            for (Device device:devices) {
                if ((device.getPower() > power1) && (device.getPower() < power2) &&
                        (device.getState().equalsIgnoreCase(state)) && (device.getType().equalsIgnoreCase(type))) {
                    position = device.getPosition();
                    System.out.println(position);
                    deviceReturnable = device;
                }
            }
        else System.out.println("Wrong power range");
        return deviceReturnable;
    }
    //method for getting device by position
    public static Device getDeviceByPosition(House house, String position) {
        Device device = null;
        for (Device deviceIterable : getAllDevices(house))
            if (deviceIterable.getPosition().equalsIgnoreCase(position))
                device = deviceIterable;
            return device;
    }


    //method for device sort by power
    public static ArrayList<Device> sortByPower(House house){
        ArrayList<Device> devicesInHouse = getAllDevices(house);
            Collections.sort(devicesInHouse, new Comparator<Device>() {
                public int compare(Device a, Device b) {
                    if (a.getPower()>b.getPower()) return 1;
                    else if (a.getPower()<b.getPower()) return -1;
                    else return 0;
                }
            });
        printDevices(devicesInHouse);
        return devicesInHouse;
    }

    public static void printDevices (ArrayList<Device> devices){
        System.out.println("[ ");
        for (Device device : devices){
            System.out.println(device.toString());
        }
        System.out.println(" ]");
    }

    public static ArrayList<Device> getAllDevices(House house){
        ArrayList<Device> devicesInHouse = new ArrayList<Device>();
        for (Room room:house.getRooms()) {
            devicesInHouse.addAll(room.getDevices());
        }
        return devicesInHouse;
    }

    public static boolean checkName(String input){
        Pattern p = Pattern.compile("[A-Z]{1}[a-z]{2,}");
        Matcher m = p.matcher(input);
        return m.matches();
    }
}
