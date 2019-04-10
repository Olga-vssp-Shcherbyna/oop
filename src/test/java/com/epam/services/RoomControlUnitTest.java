package com.epam.services;

import com.epam.house.House;
import com.epam.house.Room;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static com.epam.services.HouseControlUnit.addRoom;
import static com.epam.services.HouseControlUnit.getDeviceByPosition;
import static com.epam.services.RoomControlUnit.*;
import static org.junit.Assert.*;

public class RoomControlUnitTest {

    private static House house = House.getInstance(new ArrayList<Room>());

    @BeforeClass
    public static void setUp() throws Exception {
        addRoom(house, 1, "Library");
        addDevice(house, "Library", "PC", 12, "on");
    }

    //getRoom test
    @Test
    public void nullCheck(){
        assertNull("Room was null", getRoomByName(house, "Librry"));
    }

    //addDevice test
    @Test
    public void shouldReturnMissingRoomMessage(){
        addDevice(house, "roomName", "pc", 20, "on");
    }
    @Test
    public void shouldReturnOverflowMessage(){
        addDevice(house, "Library", "oven", 10, "off");
    }
    @Test
    public void shouldReturnNullDeviceMessage(){
        switchDevice(house,"Library", 0, "pc");
        addDevice(house, "Library", "type", 0, "on");
    }


    //switchDevice tests
    @Test
    public void shouldReturnNoRoomMessage(){
        switchDevice(house, "ex", 5, "pc");
    }
    @Test
    public void shouldReturnNoDeviceMessage(){
        switchDevice(house,"Library", 0, "type");
    }

    //deleteDevice tests
    @Test
    public void shouldReturnNoneDeviceMessage(){
        deleteDevice(house, "Library", "PC Library 2");
    }
    @Test
    public void shouldReturnNullRoomMessage(){
        deleteDevice(house, "name", "PC Library 0");
    }

    //roomStatus test
    @Test
    public void shouldReturnNullMessage(){
        roomStatus(house, "name");
    }
}