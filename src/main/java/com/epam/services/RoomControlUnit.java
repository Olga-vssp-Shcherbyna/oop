package com.epam.services;
import com.epam.devices.Device;
import com.epam.factory.Factory;
import com.epam.house.House;
import com.epam.house.Room;

public class RoomControlUnit {

    public static Room getRoomByName(House house, String name){
        Room room = null;
        for (Room roomIterable:house.getRooms())
            if (roomIterable.getName().equalsIgnoreCase(name)) {
                room = roomIterable;
            }
        if (room!=null) return room;
        else return null;
    }

    //method for adding new device in the room
    public static void addDevice(House house, String roomName, String type, int power, String state){
        Room room = RoomControlUnit.getRoomByName(house,roomName);
        Device device = Factory.getDevice(type, power, state);
        if (room!=null) {
            if (room.getNumberOfFreeSockets() == 0) System.out.println("All sockets are filled with devices");
            room.setNumberOfFreeSockets(room.getNumberOfFreeSockets() - 1);
            if (device == null) {
                System.out.println("Device was null");
                return;
            } else {
                if (device.getBooleanState()) {
                    room.setRoomPower(room.getRoomPower() + device.getPower());
                    house.setTotalPower(house.getTotalPower() + room.getRoomPower());
                }
                room.getDevices().add(device);
                device.setPosition(room.getName());
            }
           // System.out.println(device.toString());
        }
        else System.out.println("There is no such room");
    }


    //method for switching device
    public static void switchDevice(House house, String roomName, int id, String type){
        Room room = RoomControlUnit.getRoomByName(house, roomName);
        if (room==null) {System.out.println("Sorry, there is no such room"); return;}
        Device device = HouseControlUnit.getDeviceByPosition(house, type + " " + roomName + " " + id);
        if (device==null) {System.out.println("Sorry, there is no such device"); return;}
        String state = device.getState();
        if (state.equalsIgnoreCase("on")) {
            device.setState("off");
            room.setRoomPower(room.getRoomPower() - device.getPower());
            house.setTotalPower(house.getTotalPower() - device.getPower());
            room.setNumberOfFreeSockets(room.getNumberOfFreeSockets()+1);
            }
        else if (state.equalsIgnoreCase("off")) {
            device.setState("on");
            room.setRoomPower(room.getRoomPower() + device.getPower());
            house.setTotalPower(house.getTotalPower() + device.getPower());
            }
      //  RoomControlUnit.roomStatus(house,roomName);
      //  System.out.println(device.toString());
        }


    //method for removing device from the room
    public static void deleteDevice(House house, String roomName, String position) {
        Room room = RoomControlUnit.getRoomByName(house,roomName);
        Device device = null;
        if (room!=null){
            for (Device deviceIterable : room.getDevices()) {
                if (deviceIterable.getPosition().equalsIgnoreCase(position))
                    device = deviceIterable;
            }
            if (device != null) {
                room.setNumberOfFreeSockets(room.getNumberOfFreeSockets() + 1);
                if (device.getBooleanState()) {
                    room.setRoomPower(room.getRoomPower() - device.getPower());
                    house.setTotalPower(house.getTotalPower()-device.getPower());
                }
                room.getDevices().remove(device);
                device.setPosition("Device was deleted");
            }
            else System.out.println("Device was null");
        }
        else System.out.println("There is no such room");
    }


    //method for room status display
    public static void roomStatus(House house, String roomName){
        Room room = RoomControlUnit.getRoomByName(house,roomName);
        if (room!=null)
            System.out.println("This is "+room.getName().toLowerCase()+" and there is "+room.getNumberOfFreeSockets()+" free sockets and total power of devices = "+room.getRoomPower());
        else System.out.println("Sorry, there is no such room");
    }
}
