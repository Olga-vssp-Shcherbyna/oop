package com.epam.services;

import com.epam.house.House;
import com.epam.house.Room;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static com.epam.services.HouseControlUnit.addRoom;
import static com.epam.services.RoomControlUnit.addDevice;
import static java.lang.Integer.*;


public class init {
    public static House initHouse() throws IOException {
        File f = new File("D:\\Java_\\HomeDevices\\src\\main\\resources\\parameters.txt");
        House house = House.getInstance(new ArrayList<Room>());
        BufferedReader fr = new BufferedReader(new FileReader(f));
        for (int i = 1; i<5; i++)
            addRoom(house, parseInt(fr.readLine()), fr.readLine());
        for (int j = 1; j<6; j++)
            addDevice(house, fr.readLine(), fr.readLine(), parseInt(fr.readLine()), fr.readLine());
        return house;
    }
}
