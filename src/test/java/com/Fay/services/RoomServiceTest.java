package com.Fay.services;

import com.Fay.house.House;
import com.Fay.house.Room;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static com.Fay.services.HouseService.addRoom;
import static com.Fay.services.RoomService.*;
import static org.junit.Assert.assertNull;

public class RoomServiceTest {

    private static House house = House.getInstance(new ArrayList<Room>());

    @BeforeClass
    public static void setUp() throws Exception {
        addRoom(house, 1, "Library");
        addDevice(house, "Library", "PC", 12, "on");
    }

    @Test
    public void nullCheck() {
        assertNull("Room was null", getRoomByName(house, "Librry"));
    }

    @Test
    public void shouldReturnMissingRoomMessage() {
        addDevice(house, "roomName", "pc", 20, "on");
    }

    @Test
    public void shouldReturnOverflowMessage() {
        addDevice(house, "Library", "oven", 10, "off");
    }

    @Test
    public void shouldReturnNullDeviceMessage() {
        switchDevice(house, "Library", 0, "pc");
        addDevice(house, "Library", "type", 0, "on");
    }


    @Test
    public void shouldReturnNoRoomMessage() {
        switchDevice(house, "ex", 5, "pc");
    }

    @Test
    public void shouldReturnNoDeviceMessage() {
        switchDevice(house, "Library", 0, "type");
    }

    @Test
    public void shouldReturnNoneDeviceMessage() {
        deleteDevice(house, "Library", "PC Library 2");
    }

    @Test
    public void shouldReturnNullRoomMessage() {
        deleteDevice(house, "name", "PC Library 0");
    }

    @Test
    public void shouldReturnNullMessage() {
        roomStatus(house, "name");
    }
}