package com.Fay.services;

import com.Fay.house.House;

import java.io.IOException;

import static com.Fay.services.Init.initHouse;


public class Main {
    public static void main(String[] args) throws IOException {
        Menu.printMenu();
        House house = initHouse();
        Menu.menu(house);
    }
}
