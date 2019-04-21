package com.epam.factory;

import org.junit.Test;

import static com.epam.factory.Factory.*;
import static org.junit.Assert.*;

public class FactoryTest {
    @Test
    public void shouldReturnWrongTypeMessage(){
        assertNull(getDevice("type", 1, true));
    }
    @Test
    public void initializationParametersTest(){
        assertNull(getDevice("pc", 0, false));
        assertNull(getDevice("pc", 20, true));
    }
    @Test
    public void typeCheckTest(){
        assertFalse(typeChecker("type"));
    }

}