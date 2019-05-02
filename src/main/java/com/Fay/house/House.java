package com.Fay.house;

import java.util.ArrayList;
import java.util.List;

public class House {
    private static House INSTANCE;
    private List<Room> rooms;
    private int totalPower = 0;

    private House(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public static House getInstance(ArrayList<Room> rooms) {
        if (INSTANCE == null) {
            synchronized (House.class) {
                if (INSTANCE == null)
                    INSTANCE = new House(rooms);
            }
        }
        return INSTANCE;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public int getTotalPower() {
        return totalPower;
    }

    public void setTotalPower(int totalPower) {
        this.totalPower = totalPower;
    }
}

