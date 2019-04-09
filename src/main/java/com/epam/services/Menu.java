package com.epam.services;

import com.epam.factory.Factory;
import com.epam.house.House;
import com.epam.house.Room;
import java.io.*;
import java.util.ArrayList;

public class Menu {

    public static void printMenu() throws IOException {
        File f = new File("..\\src\\main\\resources\\Readme.txt");
        BufferedReader fr = new BufferedReader(new FileReader(f));
        String line;
        while ((line = fr.readLine()) != null) System.out.println(line);
    }

    private enum TaskNumber{
        MENU, AR, RR, HS, RS, FD, ND, RD, SD, SORT, DP, EXIT;

        public static boolean contains(String task){
            for (TaskNumber t : TaskNumber.values()){
                if (t.name().equals(task))
                    return true;
            }
            return false;
        }
    }
    public static void menu(House house) throws IOException {
        boolean run = true;
        System.out.println("Enter task please");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (run) {
            String line = reader.readLine().toUpperCase();
            TaskNumber task;
            if (TaskNumber.contains(line)) {
                task = TaskNumber.valueOf(line);
                switch (task) {
                    case MENU:
                        printMenu();
                        System.out.println("Enter task please");
                        break;
                    case AR: {
                        System.out.println("New room");
                        System.out.println("Enter unique name of room");
                        String name = reader.readLine();
                        if (HouseControlUnit.checkName(name)) {
                            System.out.println("Enter positive number of sockets in room");
                            int n = Integer.parseInt(reader.readLine());
                            if (n < 0)
                                System.out.println("Sorry, invalid number");
                            else HouseControlUnit.addRoom(house, n, name);
                        } else System.out.println("Sorry, invalid name");
                        System.out.println("Enter task please");
                        break;
                    }
                    case RR: {
                        System.out.println("Remove room");
                        System.out.println("Enter name of room you want to remove");
                        String name = reader.readLine();
                        HouseControlUnit.deleteRoom(house, name);
                        System.out.println("Enter task please");
                        break;
                    }
                    case HS:
                        System.out.println("House state");
                        HouseControlUnit.houseStatus(house);
                        System.out.println("Enter task please");
                        break;
                    case RS: {
                        System.out.println("Room state");
                        System.out.println("Enter name of room you want to check");
                        String name = reader.readLine();
                        RoomControlUnit.roomStatus(house, name);
                        System.out.println("Enter task please");
                        break;
                    }
                    case FD: {
                        System.out.println("Find device");
                        System.out.println("Enter start point for power range");
                        int p1 = Integer.parseInt(reader.readLine());
                        System.out.println("Enter final point for power range");
                        int p2 = Integer.parseInt(reader.readLine());
                        if (p2 > 0 && p2 > p1) {
                            System.out.println("Enter state of device - on/off");
                            String state = reader.readLine();
                            if (state.equalsIgnoreCase("on") || state.equalsIgnoreCase("off")) {
                                System.out.println("Enter type of device - iron/kettle/pc/razor/oven");
                                String type = reader.readLine();
                                if (Factory.typeChecker(type))
                                    HouseControlUnit.findDevice(house, p1, p2, state, type);
                                else System.out.println("Wrong type");
                            } else System.out.println("Wrong state");
                        } else System.out.println("Wrong power range");
                    }
                    System.out.println("Enter task please");
                    break;
                    case ND: {
                        System.out.println("New device");
                        System.out.println("Enter room you want add device to");
                        String roomName = reader.readLine();
                        if (RoomControlUnit.getRoomByName(house, roomName) != null) {
                            System.out.println("Enter type of device - iron/kettle/pc/razor/oven");
                            String type = reader.readLine();
                            if (Factory.typeChecker(type)) {
                                System.out.println("Enter device power");
                                int p = Integer.parseInt(reader.readLine());
                                if (p > 0) {
                                    System.out.println("Enter device state - on/off");
                                    String state = reader.readLine();
                                    if (state.equalsIgnoreCase("on") || state.equalsIgnoreCase("off"))
                                        RoomControlUnit.addDevice(house, roomName, type, p, state);
                                    else System.out.println("Sorry, wrong state");
                                } else System.out.println("Sorry, invalid power value");
                            } else System.out.println("Sorry, invalid type");
                        } else System.out.println("Sorry, invalid room name");
                        System.out.println("Enter task please");
                        break;
                    }
                    case RD: {
                        System.out.println("Remove device");
                        System.out.println("Enter name of room you want remove device from");
                        String roomName = reader.readLine();
                        if (RoomControlUnit.getRoomByName(house, roomName) != null) {
                            System.out.println("Enter device id");
                            int id = Integer.parseInt(reader.readLine());
                            if (id > 0) {
                                System.out.println("Enter device type");
                                String type = reader.readLine();
                                if (Factory.typeChecker(type))
                                    RoomControlUnit.deleteDevice(house, roomName, type + " " + roomName + " " + id);
                                else System.out.println("Wrong type");
                            } else System.out.println("Wrong id");
                        } else System.out.println("Wrong room name");
                        System.out.println("Enter task please");
                        break;
                    }
                    case SD: {
                        System.out.println("Switch device state");
                        System.out.println("Enter name of room where you want to switch device");
                        String roomName = reader.readLine();
                        if (RoomControlUnit.getRoomByName(house, roomName) != null) {
                            System.out.println("Enter device id");
                            int id = Integer.parseInt(reader.readLine());
                            if (id >= 0) {
                                System.out.println("Enter device type - iron/kettle/pc/razor/oven");
                                String type = reader.readLine();
                                if (Factory.typeChecker(type))
                                    RoomControlUnit.switchDevice(house, roomName, id, type);
                                else System.out.println("Wrong type");
                            } else System.out.println("Wrong id");
                        } else System.out.println("Wrong room name");
                        System.out.println("Enter task please");
                        break;
                    }
                    case SORT:
                        System.out.println("Sort devices by power consumption");
                        HouseControlUnit.sortByPower(house);
                        System.out.println("Enter task please");
                        break;
                    case DP:
                        System.out.println("Total power consumption");
                        System.out.println("Power consumption is " + house.getTotalPower());
                        System.out.println("Enter task please");
                        break;
                    case EXIT:
                        System.out.println("Exit");
                        run = false;
                        System.exit(0);
                        break;
                }
            }
            else System.out.println("Invalid input. Enter task again please");
        }
    }
}
