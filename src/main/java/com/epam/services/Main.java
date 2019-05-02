package com.epam.services;

import com.epam.house.House;

import java.io.*;
import static com.epam.services.init.initHouse;


//application entry point
public class Main {
    public static void main(String[] args) throws IOException {
        Menu.printMenu();
        House house = initHouse();
        Menu.menu(house);
    }
}
