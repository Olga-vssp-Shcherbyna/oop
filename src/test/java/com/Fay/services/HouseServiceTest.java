package com.Fay.services;

import com.Fay.devices.Device;
import com.Fay.house.House;
import com.Fay.house.Room;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.Fay.services.HouseService.addRoom;
import static com.Fay.services.HouseService.deleteRoom;
import static com.Fay.services.RoomService.addDevice;
import static org.junit.Assert.*;

public class HouseServiceTest {

    private static House house = House.getInstance(new ArrayList<Room>());

    @BeforeClass
    public static void setUp() throws Exception {
        addRoom(house, 4, "Library");
        addDevice(house, "Library", "PC", 12, "on");
    }

    // addRoom tests
    @Test
    public void shouldReturnSocketBugMessage(){
        assertNull("Number of sockets is correct", addRoom(house, -5, "Lib"));
    }
    @Test
    public void shouldReturnNameBugMessage(){
        assertNull("Name is correct", addRoom(house, 5, "s5d"));
        assertNull("Name is correct", addRoom(house, 5, "Ss"));
    }


    //deleteRoom tests
    @Test
    public void shouldReturnMissingRoomMessage(){
        assertNull(deleteRoom(house,"Lib"));
    }
    @Test
    public void shouldReturnFalseWhenPowerDecreased(){
        int p1 = house.getTotalPower();
        deleteRoom(house, "Library");
        int p2 = house.getTotalPower();
        boolean result = p1>=p2;
        assertTrue("Power increased",result);
    }

    @Test
    public void shouldReturnFalseWhenRoomDeleted(){
        List<Room> r1 = house.getRooms();
        deleteRoom(house, "Library");
        List<Room> r2 = house.getRooms();
        boolean result = r2.contains(r1);
        assertFalse("Room was not deleted", result);
    }

    //findDevice tests

    @Test
    public void shouldReturnNullWhenDeviceDoesNotExist(){
        Device device = HouseService.findDevice(house, 0, 20, "off", "pc");
        assertNull("Device was not null", device);
    }

    @Test
    public void shouldReturnWrongRangeMessage(){
        Device device = HouseService.findDevice(house, -1, 0, "on", "oven");
        assertNull("Device was not null", device);
    }


    //getDeviceByPosition test

    @Test
    public void shouldReturnNullWhenPositionIsWrong(){
        assertNull("position was not wrong", HouseService.getDeviceByPosition(house, "pc library 5"));
    }
}