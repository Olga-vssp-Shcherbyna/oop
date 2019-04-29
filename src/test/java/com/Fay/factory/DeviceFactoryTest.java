package com.Fay.factory;

import org.junit.Test;

import static com.Fay.factory.DeviceFactory.getDevice;
import static com.Fay.factory.DeviceFactory.typeChecker;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

public class DeviceFactoryTest {
    @Test
    public void shouldReturnWrongTypeMessage() {
        assertNull(getDevice("type", 1, true));
    }

    @Test
    public void initializationParametersTest() {
        assertNull(getDevice("pc", 0, false));
        assertNull(getDevice("pc", 20, true));
    }

    @Test
    public void typeCheckTest() {
        assertFalse(typeChecker("type"));
    }

}