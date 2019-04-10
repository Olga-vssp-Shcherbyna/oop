package com.epam.house;

import com.epam.devices.Device;
import java.util.ArrayList;
import java.util.Collections;

public class House{
        private ArrayList <Room> rooms;
        private int totalPower = 0;

        private static House INSTANCE;

        public static House getInstance(ArrayList<Room> rooms){
            if (INSTANCE==null)
                synchronized (House.class){
                if (INSTANCE==null)
                    INSTANCE = new House(rooms);
                }
            return INSTANCE;
            }


        //getter for list of rooms in house
        public ArrayList<Room> getRooms() {
            return rooms;
        }

        //getter for total power consumption
        public int getTotalPower() {
            return totalPower;
        }

        //setter for totalPower
        public void setTotalPower(int totalPower) {
            this.totalPower = totalPower;
        }

    private House(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

}

