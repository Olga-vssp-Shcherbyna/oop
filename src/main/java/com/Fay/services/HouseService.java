package com.Fay.services;

import com.Fay.devices.Device;
import com.Fay.house.House;
import com.Fay.house.Room;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.Fay.services.RoomService.roomSetupValidation;

public class HouseService {


    /**
     * This method is used for adding new room to house
     *
     * @param house
     * @param numberOfFreeSockets
     * @param name
     * @return
     */
    public static Room addRoom(House house, int numberOfFreeSockets, String name) {
        if (roomSetupValidation(house, numberOfFreeSockets, name)) {
            house.getRooms().add(new Room(numberOfFreeSockets, name));
            return new Room(numberOfFreeSockets, name);
        } else return null;
    }


    /**
     * This method is used for removing room from house
     *
     * @param house
     * @param name
     * @return
     */
    public static Room deleteRoom(House house, String name) {
        Room r = getRoomByName(house, name);
        if (r != null) {
            house.getRooms().remove(r);
            powerDecrement(house, r);
        } else
            System.out.println("Sorry, there is no such room. Please, try again");
        return r;
    }

    /**
     * This is util method for house total power decreasing when some room is deleted from it
     *
     * @param house
     * @param r
     */
    private static void powerDecrement(House house, Room r) {
        house.setTotalPower(house.getTotalPower() - r.getRoomPower());
    }


    /**
     * This method is used to get room by its name
     *
     * @param house
     * @param name
     * @return
     */
    public static Room getRoomByName(House house, String name) {
        for (Room room : house.getRooms()) {
            if (room.getName().equalsIgnoreCase(name)) {
                return room;
            }
        }
        return null;
    }

    /**
     * This method is used to get info about rooms in the house and total power of active devices
     *
     * @param house
     */
    public static void houseStatus(House house) {
        System.out.printf("In house there are %d rooms and its total power is %d \n", house.getRooms().size(), house.getTotalPower());
    }

    /**
     * This method is used for search device by parameters
     *
     * @param house
     * @param power1
     * @param power2
     * @param state
     * @param type
     * @return
     */
    public static Device findDevice(House house, int power1, int power2, String state, String type) {
        String position;
        Device deviceReturnable = null;
        List<Device> devices = getAllDevices(house);
        if (power1 < power2 && power2 >= 0 && power1 >= 0) {
            for (Device device : devices) {
                if ((device.getPower() > power1) && (device.getPower() < power2) &&
                        (device.getState() == Boolean.parseBoolean(state)) && (device.getType().equalsIgnoreCase(type))) {
                    position = device.getPosition();
                    System.out.println(position);
                    deviceReturnable = device;
                }
            }
        } else System.out.println("Wrong power range");
        return deviceReturnable;
    }


    /**
     * This method is used for getting device by its position
     *
     * @param house
     * @param position
     * @return
     */
    public static Device getDeviceByPosition(House house, String position) {
        Device device = null;
        for (Device deviceIterable : getAllDevices(house)) {
            if (deviceIterable.getPosition().equalsIgnoreCase(position)) {
                device = deviceIterable;
            }
        }
        return device;
    }


    /**
     * This is sorting method
     *
     * @param house
     * @return
     */
    public static List<Device> sortByPower(House house) {
        List<Device> devicesInHouse = getAllDevices(house);
        Collections.sort(devicesInHouse, new Comparator<Device>() {
            public int compare(Device a, Device b) {
                if (a.getPower() > b.getPower()) return 1;
                else if (a.getPower() < b.getPower()) return -1;
                else return 0;
            }
        });
        printDevices(devicesInHouse);
        return devicesInHouse;
    }

    /**
     * This method is used for nice display of devices in the house
     *
     * @param devices
     */
    public static void printDevices(List<Device> devices) {
        System.out.println("[ ");
        for (Device device : devices) {
            System.out.println(device.toString());
        }
        System.out.println(" ]");
    }

    /**
     * This method is used for getting list of devices
     *
     * @param house
     * @return
     */
    public static List<Device> getAllDevices(House house) {
        List<Device> devices = new ArrayList<>();
        for (Room room : house.getRooms()) {
            devices.addAll(room.getDevices());
        }
        return devices;
    }

}
