package com.epam.factory;

import com.epam.devices.*;
import com.epam.services.RoomControlUnit;

public class Factory {

    private enum Type {
        PC, IRON, OVEN, RAZOR, KETTLE;
        public static boolean contains(String task){
            for (Type t : Type.values()){
                if (t.name().equals(task))
                    return true;
            }
            return false;
        }
    }

    public static Device getDevice(String type, int power, String state){
        Device device = null;
        type = type.toUpperCase();
        Type TYPE;
        if (Type.contains(type)) {
            TYPE = Type.valueOf(type);
            if (power > 0 && (state.equalsIgnoreCase("on") || state.equalsIgnoreCase("off"))) {
                switch (TYPE) {
                    case KETTLE:
                        device = new Kettle(power, state);
                        break;
                    case RAZOR:
                        device = new Razor(power, state);
                        break;
                    case OVEN:
                        device = new Oven(power, state);
                        break;
                    case IRON:
                        device = new Iron(power, state);
                        break;
                    case PC:
                        device = new PC(power, state);
                        break;
                }
            }
            else
                System.out.println("Wrong initialization parameters");
        }
        else System.out.println("Wrong type");
        return device;
    }

        public static boolean typeChecker(String type){
        return ("Iron".equalsIgnoreCase(type)||"Razor".equalsIgnoreCase(type)||"PC".equalsIgnoreCase(type)||
                "Kettle".equalsIgnoreCase(type)||"Oven".equalsIgnoreCase(type));
        }
}
