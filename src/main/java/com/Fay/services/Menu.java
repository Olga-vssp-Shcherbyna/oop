package com.Fay.services;

import com.Fay.factory.DeviceFactory;
import com.Fay.house.House;

import java.io.*;

import static com.Fay.services.HouseService.houseStatus;
import static com.Fay.services.RoomService.checkName;

public class Menu {

    public static final String MENU = ".\\src\\main\\resources\\Readme.txt";

    public static void printMenu() throws IOException {
        File f = new File(MENU);
        BufferedReader fr = new BufferedReader(new FileReader(f));
        String line;
        while ((line = fr.readLine()) != null) System.out.println(line);
    }

    public static void menu(House house) throws IOException {
        System.out.println("Enter task please");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine().toUpperCase();
        TaskNumber task;
        if (TaskNumber.contains(line)) {
            task = TaskNumber.valueOf(line);
            switch (task) {
                case MENU:
                    printMenu();
                    break;
                case AR: {
                    addRoom(house, reader);
                    break;
                }
                case RR: {
                    removeRoom(house, reader);
                    break;
                }
                case HS:
                    System.out.println("House state");
                    houseStatus(house);
                    break;
                case RS: {
                    roomStatus(house, reader);
                    break;
                }
                case FD: {
                    findDevice(house, reader);
                    break;
                }
                case ND: {
                    addDevice(house, reader);
                    break;
                }
                case RD: {
                    removeDevice(house, reader);
                    break;
                }
                case SD: {
                    switchDevice(house, reader);
                    break;
                }
                case SORT:
                    System.out.println("Sort devices by power consumption");
                    HouseService.sortByPower(house);
                    break;
                case DP:
                    totalPowerConsumptionDisplay(house);
                    break;
                case EXIT:
                    System.out.println("Exit");
                    System.exit(0);
                    break;
            }
        } else System.out.println("Invalid input. Enter task again please");
        menu(house);
    }

    public static void totalPowerConsumptionDisplay(House house) {
        System.out.println("Total power consumption");
        System.out.printf("Power consumption is %d \n", house.getTotalPower());
    }

    public static void switchDevice(House house, BufferedReader reader) throws IOException {
        System.out.println("Switch device state");
        System.out.println("Enter name of room where you want to switch device");
        String roomName = reader.readLine();
        if (RoomService.getRoomByName(house, roomName) != null) {
            System.out.println("Enter device id");
            int id = Integer.parseInt(reader.readLine());
            if (id >= 0) {
                System.out.println("Enter device type - iron/kettle/pc/razor/oven");
                String type = reader.readLine();
                if (DeviceFactory.typeChecker(type)) {
                    RoomService.switchDevice(house, roomName, id, type);
                } else System.out.println("Wrong type");
            } else System.out.println("Wrong id");
        } else System.out.println("Wrong room name");
    }

    public static void removeDevice(House house, BufferedReader reader) throws IOException {
        System.out.println("Remove device");
        System.out.println("Enter name of room you want remove device from");
        String roomName = reader.readLine();
        if (RoomService.getRoomByName(house, roomName) != null) {
            System.out.println("Enter device id");
            int id;
            try {
                id = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid id format");
                return;
            }
            if (id >= 0) {
                System.out.println("Enter device type");
                String type = reader.readLine();
                if (DeviceFactory.typeChecker(type)) {
                    RoomService.deleteDevice(house, roomName, type + " " + roomName + " " + id);
                } else System.out.println("Wrong type");
            } else System.out.println("Wrong id");
        } else System.out.println("Wrong room name");
    }

    public static void addDevice(House house, BufferedReader reader) throws IOException {
        System.out.println("New device");
        System.out.println("Enter room you want add device to");
        String roomName = reader.readLine();
        if (RoomService.getRoomByName(house, roomName) != null) {
            System.out.println("Enter type of device - iron/kettle/pc/razor/oven");
            String type = reader.readLine();
            if (DeviceFactory.typeChecker(type)) {
                System.out.println("Enter device power");
                int p = 0;
                try {
                    p = Integer.parseInt(reader.readLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid power value format");
                    return;
                }
                if (p > 0) {
                    System.out.println("Enter is device on - true/false");
                    String state = reader.readLine();
                    if (state.equalsIgnoreCase("true") || state.equalsIgnoreCase("false")) {
                        RoomService.addDevice(house, roomName, type, p, state);
                    } else System.out.println("Sorry, wrong state");
                } else System.out.println("Sorry, invalid power value");
            } else System.out.println("Sorry, invalid type");
        } else System.out.println("Sorry, invalid room name");
    }

    public static void findDevice(House house, BufferedReader reader) throws IOException {
        System.out.println("Find device");
        System.out.println("Enter start point for power range");
        int p1;
        try {
            p1 = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid start point format");
            return;
        }
        System.out.println("Enter final point for power range");
        int p2;
        try {
            p2 = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid final point format");
            return;
        }
        if (p2 > 0 && p2 > p1) {
            System.out.println("Enter is device on - true/false");
            String state = reader.readLine();
            if (state.equalsIgnoreCase("true") || state.equalsIgnoreCase("false")) {
                System.out.println("Enter type of device - iron/kettle/pc/razor/oven");
                String type = reader.readLine();
                if (DeviceFactory.typeChecker(type)) {
                    HouseService.findDevice(house, p1, p2, state, type);
                } else System.out.println("Wrong type");
            } else System.out.println("Wrong state");
        } else System.out.println("Wrong power range");
    }

    public static void roomStatus(House house, BufferedReader reader) throws IOException {
        System.out.println("Room state");
        System.out.println("Enter name of room you want to check");
        String name = reader.readLine();
        RoomService.roomStatus(house, name);
    }

    public static void removeRoom(House house, BufferedReader reader) throws IOException {
        System.out.println("Remove room");
        System.out.println("Enter name of room you want to remove");
        String name = reader.readLine();
        HouseService.deleteRoom(house, name);
    }

    public static void addRoom(House house, BufferedReader reader) throws IOException {
        System.out.println("New room");
        System.out.println("Enter unique name of room");
        String name = reader.readLine();
        if (checkName(name)) {
            System.out.println("Enter positive number of sockets in room");
            int n = 0;
            try {
                n = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format");
                return;
            }
            if (n < 0) {
                System.out.println("Sorry, invalid number");
            } else HouseService.addRoom(house, n, name);
        } else System.out.println("Sorry, invalid name");
    }

    private enum TaskNumber {
        MENU, AR, RR, HS, RS, FD, ND, RD, SD, SORT, DP, EXIT;

        public static boolean contains(String task) {
            for (TaskNumber t : TaskNumber.values()) {
                if (t.name().equals(task)) {
                    return true;
                }
            }
            return false;
        }
    }
}
