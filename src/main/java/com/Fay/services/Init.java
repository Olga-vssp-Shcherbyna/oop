package com.Fay.services;

import com.Fay.house.House;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static com.Fay.services.HouseService.addRoom;
import static com.Fay.services.RoomService.addDevice;
import static java.lang.Integer.parseInt;


public class Init {

    public static final String PARAMETERS = ".\\src\\main\\resources\\parameters.txt";

    public static House initHouse() throws IOException {
        File f = new File(PARAMETERS);
        House house = House.getInstance(new ArrayList<>());
        BufferedReader fr = new BufferedReader(new FileReader(f));
        for (int i = 1; i < 5; i++) {
            addRoom(house, parseInt(fr.readLine()), fr.readLine());
        }
        for (int j = 1; j < 6; j++) {
            addDevice(house, fr.readLine(), fr.readLine(), parseInt(fr.readLine()), fr.readLine());
        }
        return house;
    }
}
